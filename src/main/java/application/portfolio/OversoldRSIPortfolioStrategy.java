package application.portfolio;

import application.market.Market;
import application.secrets.SecretsDictionary;
import application.trade.TradeRepository;
import application.trade.TradeStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

public class OversoldRSIPortfolioStrategy implements PortfolioStrategy {

    @Resource(name = "secrets")
    private Map<String, String> secrets;

    private final TradeRepository tradeRepository;

    @Autowired
    public OversoldRSIPortfolioStrategy(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public int portfolioSize() {
        final Integer portfolioMaxSize = Integer.valueOf(secrets.get(SecretsDictionary.PORTFOLIO_SIZE));
        final Integer activeTrades = tradeRepository.countByStatus(TradeStatus.ACTIVE).intValue();
        return portfolioMaxSize - activeTrades;
    }

    @Override
    public BigDecimal marketFitness(Market market) {
        return null;
    }
}
