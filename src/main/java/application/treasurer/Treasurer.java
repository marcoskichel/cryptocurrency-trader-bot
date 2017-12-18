package application.treasurer;

import java.math.BigDecimal;

public interface Treasurer {

    BigDecimal getBitcoinBalance();
    BigDecimal getFiatBalance();

}
