package application.portfolio;

import application.market.Market;

import java.util.List;

public class Portfolio {

    private final List<Market> markets;

    public Portfolio(List<Market> markets) {
        this.markets = markets;
    }

    public List<Market> getMarkets() {
        return markets;
    }

}
