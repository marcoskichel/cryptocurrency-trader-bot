package application.exchange;

import application.market.Market;
import application.trading_operator.TradingOperator;
import application.treasurer.Treasurer;

import java.util.List;

public interface Exchange {

    List<Market> getAvailableMarkets();
    Treasurer getTreasurer();
    TradingOperator getTradingOperator();
    String getName();
}
