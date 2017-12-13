package application.exchange;

import application.model.Market;

import java.util.List;

public interface Exchange {

    List<Market> getAvailableMarkets();

}
