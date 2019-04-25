package StockReader;

import java.time.LocalDate;

public class Stock {

    private String stockName;
    private LocalDate date;
    private Float open;
    private Float high;
    private Float low;
    private Float close;
    private Float adjClose;
    private Long volume;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Stock(String stockName, LocalDate date, Float open, Float high, Float low, Float close, Float adjClose, Long volume) {
        this.stockName = stockName;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getOpen() {
        return open;
    }

    public void setOpen(Float open) {
        this.open = open;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Float getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Float adjClose) {
        this.adjClose = adjClose;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Date: " + date.toString() + "\tOpen: " + open + "\tHigh: " + high + "\tLow: " + low + "\tClose: " + close + "\tADJ Close: " + adjClose + "\tVolume: " + volume;
    }

}
