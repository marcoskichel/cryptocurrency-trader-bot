package application.exchange;

import application.market.Market;
import application.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Qualifier("bittrex-exchange")
public class BittrexExchange implements Exchange {

    private static final Logger log = LoggerFactory.getLogger(BittrexExchange.class);

    @Override
    public String getName() {
        return "Bittrex";
    }

    @Override
    public List<Market> getAvailableMarkets() {
        return null;
    }

    @Override
    public BigDecimal getBalance(String currency) {
        return null;
    }

    @Override
    public Order buyAtAsk(Order order) {
        log.info("Trying to buy " + order.getMarket() + " at " + getName() + " for X");
        try {
            Thread.sleep(15000);
            return order;
        } catch (InterruptedException ex) {
            log.error("Cannot wait to confirm the order, confirming right now");
            return order;
        }
    }

    @Override
    public Order sellAtBid(Order order) {
        return null;
    }

    @Override
    public Order cancel(Order order) {
        return null;
    }

}
