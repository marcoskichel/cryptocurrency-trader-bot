package application.exchange;

import application.market.Market;
import application.treasurer.Treasurer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("bittrex-exchange")
public class BittrexExchange implements Exchange {

    private final BeanFactory beanFactory;

    @Autowired
    public BittrexExchange(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public List<Market> getAvailableMarkets() {
        return null;
    }

    @Override
    public Treasurer getTreasurer() {
        return beanFactory.getBean("bittrex-exchange", Treasurer.class);
    }

}
