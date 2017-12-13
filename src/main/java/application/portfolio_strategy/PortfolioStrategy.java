package application.portfolio_strategy;

import application.exchange.Exchange;
import application.model.Market;
import application.model.Portfolio;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public interface PortfolioStrategy {

    int portfolioSize();
    BigDecimal marketFitness(final Market market);

    default Portfolio getRecommendedPortfolio(final Exchange exchange) {
        List<Market> availableMarkets = exchange.getAvailableMarkets();
        availableMarkets = sortMarketsAccordingToFittness(availableMarkets);
        final List<Market> suggestedMarkets = availableMarkets.subList(0, portfolioSize() - 1);
        return new Portfolio(suggestedMarkets);
    }

    default List<Market> sortMarketsAccordingToFittness(List<Market> availableMarkets) {
        availableMarkets.sort(Comparator.comparing(this::marketFitness));
        return availableMarkets;
    }

}
