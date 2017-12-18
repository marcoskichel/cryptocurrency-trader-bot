package application.trading_operator;

import application.trade.Trade;

public interface TradingOperator {

    Trade buyAtAsk(Trade trade);
    Trade sellAtBid(Trade trade);
    Trade cancel(Trade trade);

}
