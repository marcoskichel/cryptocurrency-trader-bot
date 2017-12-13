package application.trader;

import application.exchange.Exchange;
import application.model.Portfolio;
import application.portfolio_strategy.PortfolioStrategy;
import application.secrets.SecretsDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

import static application.secrets.SecretsDictionary.*;

@Component
@EnableScheduling
public class Trader {

    private static final Logger log = LoggerFactory.getLogger(Trader.class);

    private final BeanFactory beanFactory;

    @Resource
    private Map<String, String> secrets;

    @Autowired
    public Trader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * Runs every 5 minutes
     */
    @Scheduled(fixedRate = 300000)
    public void trade() {
        log.info("Entering trading protocol");
        //TODO check balance and decide when to invest
        final Portfolio recommendedPortfolio = getPortfolioStrategy().getRecommendedPortfolio(getExchange());
        log.info("Exiting trading protocol, reentering in 5 minutes");
    }

    private Exchange getExchange() {
        return beanFactory.getBean(secrets.get(EXCHANGE_NAME), Exchange.class);
    }

    private PortfolioStrategy getPortfolioStrategy() {
        return beanFactory.getBean(secrets.get(PORTFOLIO_STRATEGY_NAME), PortfolioStrategy.class);
    }

}
