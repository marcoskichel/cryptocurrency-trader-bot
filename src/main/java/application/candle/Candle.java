package application.candle;

import application.model.Market;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Candle {

    public boolean isGreen(Market actualMarket, Market oldMarket) {
        final BigDecimal variation = getVariation(actualMarket, oldMarket);
        return variation.signum() > 0;
    }

    public BigDecimal getVariation(Market actualMarket, Market halfHourOldSnapshot) {
        return halfHourOldSnapshot.getLast().subtract(actualMarket.getLast());
    }

    public BigDecimal tailRawValue(Market market) {
        return market.getHigh().subtract(market.getLast());
    }

}
