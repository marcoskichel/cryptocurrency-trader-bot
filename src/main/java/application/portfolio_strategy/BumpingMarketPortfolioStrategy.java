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

    public static final BigDecimal MAX_ALLOWED_CANDLE_TAIL = BigDecimal.valueOf(0.1);
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
        final boolean hasShortTail = hasShortTail(market, halfHourOldSnapshot, MAX_ALLOWED_CANDLE_TAIL);
        if (isGreenCandle && hasShortTail) {
            //close the deal
        }
        return BigDecimal.ZERO;
    }

    private boolean hasShortTail(Market actualMarket, Market oldMarket, BigDecimal maxAllowedTailPercentage) {
        final BigDecimal tail = candle.tailRawValue(actualMarket);
        final BigDecimal marketVariation = candle.getVariation(actualMarket, oldMarket);
        final BigDecimal tailPercent = marketVariation.multiply(maxAllowedTailPercentage);
        return tail.compareTo(tailPercent) < 0;
    }

    private Market fetchMarketHalfHourOldSnapshot(Market market) {
        final LocalDateTime start = market.getTimeStamp().minusMinutes(35);
        final LocalDateTime end = start.plusMinutes(5);
        return marketRepository.findByNameLikeAndTimeStampBetween(market.getName(), start, end);
    }
}
