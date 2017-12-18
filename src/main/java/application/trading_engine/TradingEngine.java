package application.trading_engine;

import application.exchange.Exchange;
import application.market.Market;
import application.portfolio.Portfolio;
import application.trade.Trade;
import application.trade.TradeRepository;
import application.portfolio.PortfolioStrategy;
import application.trading_operator.TradingOperator;
import application.treasurer.Treasurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

import static application.secrets.SecretsDictionary.*;

@Component
@EnableScheduling
public class TradingEngine {

    private static final Logger log = LoggerFactory.getLogger(TradingEngine.class);

    private final BeanFactory beanFactory;

    @Resource
    private Map<String, String> secrets;

    private final TradeRepository tradeRepository;

    @Autowired
    public TradingEngine(BeanFactory beanFactory, TradeRepository tradeRepository) {
        this.beanFactory = beanFactory;
        this.tradeRepository = tradeRepository;
    }

    /**
     * Runs every 5 minutes
     */
    @Scheduled(fixedRate = 300000)
    public void scan() {
        log.info("Scan protocol started");
        final Portfolio recommendedPortfolio = getPortfolioStrategy().getRecommendedPortfolio(getExchange());
        if (!recommendedPortfolio.getMarkets().isEmpty()) {
            buyPortfolio(recommendedPortfolio);
        }
        log.info("Exiting scan protocol, restarting in 5 minutes");
    }

    private void buyPortfolio(Portfolio recommendedPortfolio) {
        final BigDecimal tradeValue = calculateTradeValue(recommendedPortfolio);
        recommendedPortfolio.getMarkets().forEach(market -> buy(market, tradeValue));
    }

    private void buy(Market market, BigDecimal tradeValue) {
        Trade trade = new Trade();
        trade.setBoughtValue(tradeValue);
        trade.setMarketName(market.getName());
        log.info("Buying " + market.getName() + " at " + getExchange().getName() + " for " + market.getAsk() + "BTC");
        trade = getTradingOperator().buyAtAsk(trade);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            log.error("Cannot wait to confirm the order, confirming right now");
        }
        tradeRepository.save(trade);
    }

    private BigDecimal calculateTradeValue(Portfolio recommendedPortfolio) {
        final BigDecimal bitcoinBalance = getTreasurer().getBitcoinBalance();
        final BigDecimal maxTradeValue = BigDecimal.valueOf(Double.valueOf(secrets.get(MAX_TRADE_VALUE)));
        final BigDecimal tradesSumValue = maxTradeValue.multiply(BigDecimal.valueOf(recommendedPortfolio.getMarkets().size()));
        if (tradesSumValue.compareTo(bitcoinBalance) > 0) {
            return bitcoinBalance.divide(BigDecimal.valueOf(recommendedPortfolio.getMarkets().size()), BigDecimal.ROUND_CEILING);
        }
        return bitcoinBalance.compareTo(maxTradeValue) > 0 ? maxTradeValue : bitcoinBalance;
    }

    private Exchange getExchange() {
        return beanFactory.getBean(secrets.get(EXCHANGE_NAME), Exchange.class);
    }

    private PortfolioStrategy getPortfolioStrategy() {
        return beanFactory.getBean(secrets.get(PORTFOLIO_STRATEGY_NAME), PortfolioStrategy.class);
    }

    private Treasurer getTreasurer() {
        return getExchange().getTreasurer();
    }

    private TradingOperator getTradingOperator() {
        return getExchange().getTradingOperator();
    }

}
