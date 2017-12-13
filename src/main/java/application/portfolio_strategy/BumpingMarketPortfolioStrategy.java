package application.portfolio_strategy;

import application.exchange.Exchange;
import application.model.Market;
import application.model.MarketRepository;
import application.model.Portfolio;
import application.secrets.SecretsDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("bumping-market-strategy")
public class BumpingMarketPortfolioStrategy implements PortfolioStrategy {

    private final MarketRepository marketRepository;

    @Resource(name = "secrets")
    private Map<String, String> secrets;

    @Autowired
    public BumpingMarketPortfolioStrategy(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    @Override
    public int portfolioSize() {
        return Integer.valueOf(secrets.get(SecretsDictionary.PORTFOLIO_SIZE));
    }

    @Override
    public BigDecimal marketFitness(Market market) {
        final Market snapshot = fetchMarketHalfHourOldSnapshot(market);
        final BigDecimal difference = snapshot.getLast().subtract(market.getLast());
        final boolean isRisingMarket = difference.signum() > 0;
        if (isRisingMarket) {
            //TODO CHECK PERCENGE OF GROWTH
        }
        return BigDecimal.ZERO;
    }

    private Market fetchMarketHalfHourOldSnapshot(Market market) {
        final LocalDateTime start = market.getTimeStamp().minusMinutes(35);
        final LocalDateTime end = start.plusMinutes(5);
        return marketRepository.findByNameLikeAndTimeStampBetween(market.getName(), start, end);
    }
}
