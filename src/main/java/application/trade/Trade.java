package application.trade;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "trade")
public class Trade {

    @Id
    private String id;

    @Indexed
    private String marketName;

    @Indexed
    private TradeStatus status;

    private BigDecimal boughtValue;
    private BigDecimal soldValue;

    private BigDecimal paidPrice;
    private BigDecimal soldPrice;

    private BigDecimal profit;

    private LocalDateTime start;
    private LocalDateTime end;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Trade() {
        status = TradeStatus.BUYING;
    }

    public BigDecimal getBoughtValue() {
        return boughtValue;
    }

    public void setBoughtValue(BigDecimal boughtValue) {
        this.boughtValue = boughtValue;
    }

    public BigDecimal getSoldValue() {
        return soldValue;
    }

    public void setSoldValue(BigDecimal soldValue) {
        this.soldValue = soldValue;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public TradeStatus getStatus() {
        return status;
    }

    public void setStatus(TradeStatus status) {
        this.status = status;
    }
}
