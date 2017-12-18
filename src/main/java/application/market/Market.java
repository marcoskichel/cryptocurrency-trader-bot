package application.market;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "market")
public class Market {

    @Id
    private String id;

    @Indexed
    private String name;

    @Indexed
    private String exchangePairNameFullName;

    private String fullName;
    private String exchangePairName;

    private BigDecimal minTradeSize;

    @Indexed
    private Boolean active;

    @Indexed
    private Boolean trading;

    private BigDecimal volume;

    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal last;

    @Indexed
    private LocalDateTime timeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExchangePairName() {
        return exchangePairName;
    }

    public void setExchangePairName(String exchangePairName) {
        this.exchangePairName = exchangePairName;
    }

    public String getExchangePairNameFullName() {
        return exchangePairNameFullName;
    }

    public void setExchangePairNameFullName(String exchangePairNameFullName) {
        this.exchangePairNameFullName = exchangePairNameFullName;
    }

    public BigDecimal getMinTradeSize() {
        return minTradeSize;
    }

    public void setMinTradeSize(BigDecimal minTradeSize) {
        this.minTradeSize = minTradeSize;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
