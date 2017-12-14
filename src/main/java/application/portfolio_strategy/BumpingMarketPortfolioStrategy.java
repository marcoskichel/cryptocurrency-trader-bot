package application.portfolio_strategy;

import application.candle.Candle;
import application.model.Market;
import application.model.MarketRepository;
import application.secrets.SecretsDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Qualifier("bumping-market-strategy")
public class BumpingMarketPortfolioStrategy implements PortfolioStrategy {

    private final MarketRepository marketRepository;
    private final Candle candle;

    @Resource(name = "secrets")
    private Map<String, String> secrets;

    @Autowired
    public BumpingMarketPortfolioStrategy(MarketRepository marketRepository, Candle candle) {
        this.marketRepository = marketRepository;
        this.candle = candle;
    }

    @Override
    public int portfolioSize() {
        return Integer.valueOf(secrets.get(SecretsDictionary.PORTFOLIO_SIZE));
    }

    @Override
    public BigDecimal marketFitness(Market market) {
        final Market halfHourOldSnapshot = fetchMarketHalfHourOldSnapshot(market);
        final boolean isGreenCandle = candle.isGreen(market, halfHourOldSnapshot);
        if (isGreenCandle) {
            final BigDecimal tailPercent = candle.getTopTailPercentValue(market, halfHourOldSnapshot);
            final BigDecimal variation = candle.getVariationBetweenMarkets(market, halfHourOldSnapshot);
            return tailPercent.subtract(variation);
        }
        return BigDecimal.valueOf(Long.MAX_VALUE);
    }

    private Market fetchMarketHalfHourOldSnapshot(Market market) {
        final LocalDateTime start = market.getTimeStamp().minusMinutes(35);
        final LocalDateTime end = start.plusMinutes(5);
        return marketRepository.findByNameLikeAndTimeStampBetween(market.getName(), start, end);
    }
}
