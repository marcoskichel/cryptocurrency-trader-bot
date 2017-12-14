package application.candle;

import application.model.Market;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Candle {

    public boolean isGreen(final Market actualMarket, final Market oldMarket) {
        final BigDecimal variation = getVariationBetweenMarkets(actualMarket, oldMarket);
        return variation.signum() > 0;
    }

    public BigDecimal getVariationBetweenMarkets(final Market actualMarket, final Market halfHourOldSnapshot) {
        return halfHourOldSnapshot.getLast().subtract(actualMarket.getLast());
    }

    public BigDecimal getTopTailRawValue(final Market market) {
        return market.getHigh().subtract(market.getLast());
    }

    public BigDecimal getTopTailPercentValue(final Market actualMarket, final Market oldMarket) {
        final BigDecimal topTailRawValue = getTopTailRawValue(actualMarket);
        final BigDecimal variation = getVariationBetweenMarkets(actualMarket, oldMarket);
        return topTailRawValue.divide(variation);
    }

}
