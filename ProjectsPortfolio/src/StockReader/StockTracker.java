package StockReader;

import java.time.LocalDate;

public class StockTracker {

    private String originalName; //DJI
    private String sqlName; //^DJI
    private String urlName; //%5EDJI
    private LocalDate earliestDate = null;
    private LocalDate latestDate = null;

    public StockTracker(String originalName, String sqlName, String urlName, LocalDate earliestDate, LocalDate latestDate) {
        this.originalName = originalName;
        this.sqlName = sqlName;
        this.urlName = urlName;
        this.earliestDate = earliestDate;
        this.latestDate = latestDate;
    }

    public StockTracker(String originalName, String sqlName, String urlName) {
        this.originalName = originalName;
        this.sqlName = sqlName;
        this.urlName = urlName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public LocalDate getEarliestDate() {
        return earliestDate;
    }

    public void setEarliestDate(LocalDate earliestDate) {
        this.earliestDate = earliestDate;
    }

    public LocalDate getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(LocalDate latestDate) {
        this.latestDate = latestDate;
    }

    @Override
    public String toString() {
        return "StockTracker{" + "originalName=" + originalName + ", sqlName=" + sqlName + ", urlName=" + urlName + ", earliestDate=" + earliestDate + ", latestDate=" + latestDate + '}';
    }

}
