package application.exchange;

import application.model.Market;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("bittrex-exchange")
public class BittrexExchange implements Exchange {

    @Override
    public List<Market> getAvailableMarkets() {
        return null;
    }

}
