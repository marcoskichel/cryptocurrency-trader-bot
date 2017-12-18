package application.exchange;

import application.market.Market;
import application.order.Order;

import java.math.BigDecimal;
import java.util.List;

public interface Exchange {

    String getName();
    List<Market> getAvailableMarkets();
    BigDecimal getBalance(String currency);

    Order buyAtAsk(Order order);
    Order sellAtBid(Order order);
    Order cancel(Order order);

}
