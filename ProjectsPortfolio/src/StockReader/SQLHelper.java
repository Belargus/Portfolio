package StockReader;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SQLHelper {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public Connection connectToSQL() {
        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); //newInstance() is deprecated. Class.forName() unneeded as of JDBC 4.0
            connection = DriverManager.getConnection("jdbc:derby:NaxStockSQL;create=true");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLHelper().connectToSQL() " + e);
        }
        return connection;
    }

    public void disconnectSQL() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
        }
    }

    public SQLHelper getInstance() {
        return this;
    }

    /*=================================STOCKS MANAGEMENT=======================================*/
    //Creates a new table in SQL, but if the table already exists, then no table is created.
    private void createTable(String stockSymbol) {
        try {
            stockSymbol = URLDecoder.decode(stockSymbol, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("CREATE TABLE " + stockSymbol + "("
                    + "stock_date DATE PRIMARY KEY,"
                    + "open_price FLOAT NOT NULL,"
                    + "high_price FLOAT NOT NULL,"
                    + "low_price FLOAT NOT NULL,"
                    + "close_price FLOAT NOT NULL,"
                    + "adjclose_price FLOAT NOT NULL,"
                    + "volume_sold BIGINT NOT NULL"
                    + ")");
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        /*
        This seems dangerous because of SQL Injection but a safeguard is in place to only permit actual stock symbols (not made-up ones) to create tables. 
        If the user-supplied symbol doesn't return a crumb, then that means the webpage was not accessible and thus no CSV to download, preventing access to this method.
         */
    }

    //Grabs the stock data of a particular stock company and exports as a HashMap
    public Map<String, ArrayList<Stock>> selectStocks(String stockSymbol, LocalDate startDate, LocalDate endDate) {
        if (stockSymbol.equalsIgnoreCase("stock_tracker")) {
            //Prevents the stock_tracker SQL table from being selected.
        } else {
            try {
                stockSymbol = URLDecoder.decode(stockSymbol, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            Map<String, ArrayList<Stock>> stockMap = new HashMap<>();
            Stock stock;
            try {
                connection = connectToSQL();
                if (startDate == null || endDate == null) {
                    ps = connection.prepareStatement("SELECT * FROM " + stockSymbol);
                    rs = ps.executeQuery();
                } else {
                    ps = connection.prepareStatement("SELECT * FROM " + stockSymbol + " WHERE stock_date BETWEEN ? AND ?");
                    ps.setDate(1, Date.valueOf(startDate));
                    ps.setDate(2, Date.valueOf(endDate));
                    rs = ps.executeQuery();
                }
                ArrayList<Stock> stocks = new ArrayList();
                while (rs.next()) {
                    stock = new Stock(stockSymbol, LocalDate.parse(rs.getDate(1).toString()), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6), rs.getLong(7));
                    stocks.add(stock);
                }
                stockMap.put(stockSymbol, stocks);
                connection.close();
                ps.close();
                return stockMap;
            } catch (SQLException e) {
            }
        }
        return null;
    }

    //Grabs the stock data of ALL stored stock companies and returns as a HashMap
    public Map<String, ArrayList<Stock>> selectStocks() {
        Map<String, ArrayList<Stock>> stockMap = new HashMap<>();
        stockMap.clear();
        ArrayList<String> stockSymbolList = getTableNames();
        if (stockSymbolList != null) {
            try {
                Iterator iter = stockSymbolList.iterator();
                while (iter.hasNext()) {
                    stockMap.putAll(selectStocks((String) iter.next(), null, null));
                }
            } catch (Exception e) {
            }
        } else {
        }
        return stockMap;
    }

    //Grabs the stock data of ALL stored stock companies and returns as a HashMap
    public Map<String, ArrayList<Stock>> selectStocks(LocalDate startDate, LocalDate endDate) {
        Map<String, ArrayList<Stock>> stockMap = new HashMap<>();
        ArrayList<String> stockSymbolList = getTableNames();
        if (stockSymbolList != null) {
            try {
                Iterator iter = stockSymbolList.iterator();
                while (iter.hasNext()) {
                    stockMap.putAll(selectStocks((String) iter.next(), startDate, endDate));
                }
            } catch (Exception e) {
            }
        } else {
        }
        return stockMap;
    }

    //Grabs the latest stock data report for each stock 
    public ArrayList<Stock> selectLatestStocks() {
        ArrayList<Stock> arr = new ArrayList<>();
        ArrayList<String> names = selectTrackerSQLNames();
        Iterator iter = names.iterator();
        String stockName;
        try {
            connection = connectToSQL();
            while (iter.hasNext()) {
                stockName = (String) iter.next();
                ps = connection.prepareStatement(String.format("SELECT * FROM %s WHERE stock_date IN (SELECT MAX(stock_date) FROM %s)", stockName, stockName));
                rs = ps.executeQuery();
                while (rs.next()) {
                    arr.add(new Stock(stockName, LocalDate.parse(rs.getDate(1).toString()), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6), rs.getLong(7)));
                }
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
        }
        return arr;
    }

    //Inserts raw stock data into SQL db
    public void insertStock(String stockSymbol, LocalDate stockDate, Float openPrice, Float highPrice, Float lowPrice, Float closePrice, Float adjClosePrice, long volumeSold) {
        try {
            stockSymbol = URLDecoder.decode(stockSymbol, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO " + stockSymbol + "(stock_date, open_price, high_price, low_price, close_price, adjclose_price, volume_sold) VALUES (?,?,?,?,?,?,?)");
            ps.setDate(1, Date.valueOf(stockDate));
            ps.setFloat(2, openPrice);
            ps.setFloat(3, highPrice);
            ps.setFloat(4, lowPrice);
            ps.setFloat(5, closePrice);
            ps.setFloat(6, adjClosePrice);
            ps.setLong(7, volumeSold);
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
    }

    //Inserts a Stock object into SQL db
    public void insertStock(Stock stock) {
        String stockSymbol = null;
        try {
            stockSymbol = URLDecoder.decode(stock.getStockName(),"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO " + stockSymbol + "(stock_date, open_price, high_price, low_price, close_price, adjclose_price, volume_sold) VALUES (?,?,?,?,?,?,?)");
            ps.setDate(1, Date.valueOf(stock.getDate()));
            ps.setFloat(2, stock.getOpen());
            ps.setFloat(3, stock.getHigh());
            ps.setFloat(4, stock.getLow());
            ps.setFloat(5, stock.getClose());
            ps.setFloat(6, stock.getAdjClose());
            ps.setLong(7, stock.getVolume());
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
    }

    //Inserts an entire list of Stock objects at once into SQL db, creating tables as needed
    public void insertStockMap(Map<String, ArrayList<Stock>> stockMap) {
        if (stockMap != null) {
            for (Map.Entry<String, ArrayList<Stock>> entry : stockMap.entrySet()) {
                String sqlName = entry.getKey();
                deleteStock(sqlName); //Testing...
                createTable(sqlName); //Creates a new table, but what happens if the table already exists? Does it continue to inserting? Needs testing. Testing concluded. Seems to work just fine.
                for (Stock stock : entry.getValue()) {
                    insertStock(stock);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "The data from the CSV has been successfully uploaded to the database.");
        /*
        Concern here due to duplicate rows. The data from the previous weeks would correctly prevent 
        insertions of data from dates already stored, but the errors would like prevent the new data from being 
        stored. Need a way to skip the old data and insert only non-duplicates. That said... creating a table may 
        also cause this issue. NO NEED. TESTED AND IT WORKS. DUPLICATE TABLES/ROWS SKIPPED.
         */
    }

    public void updateStock() {
        //Placeholder. There is no need for this as the data is all inserted via files
    }

    public void deleteStock(String symbol) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("DROP TABLE " + symbol);
            ps.execute();
            connection.close();
            ps.close();
        } catch (Exception e) {
        }
    }

    public Float getAveragePrice(String stockSymbol, LocalDate startDate, LocalDate endDate) {
        Float avg = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT AVG(close_price) FROM " + stockSymbol + " WHERE stock_date BETWEEN ? AND ?");
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                avg = rs.getFloat(1);
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return avg;
    }

    public Float getMinPrice(String stockSymbol, LocalDate startDate, LocalDate endDate) {
        Float min = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT MIN(close_price) FROM " + stockSymbol + " WHERE stock_date BETWEEN ? AND ?");
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                min = rs.getFloat(1);
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return min;
    }

    public Float getMaxPrice(String stockSymbol, LocalDate startDate, LocalDate endDate) {
        Float max = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT MAX(close_price) FROM " + stockSymbol + " WHERE stock_date BETWEEN ? AND ?");
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getFloat(1);
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return max;
    }

    public Float[] getPrices(String stockSymbol, LocalDate startDate, LocalDate endDate) {
        Float[] prices = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT MIN(close_price), MAX(close_price), AVG(close_price) FROM " + stockSymbol + " WHERE stock_date BETWEEN ? AND ?");
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                prices = new Float[3];
                prices[0] = rs.getFloat(1);
                prices[1] = rs.getFloat(2);
                prices[2] = rs.getFloat(3);
            }
            connection.close();
            ps.close();
            return prices;
        } catch (SQLException e) {
        }
        return null;
    }

    public LocalDate getEarliestDate(String stockSymbol) {
        if (stockSymbol.charAt(0) == '^') {
            stockSymbol = stockSymbol.substring(1);
        }
        LocalDate earliestDate = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT MIN(stock_date) FROM " + stockSymbol);
            rs = ps.executeQuery();
            while (rs.next()) {
                rs.getDate(1).toString();
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return earliestDate;
    }

    public LocalDate getLatestDate(String stockSymbol) {
        if (stockSymbol.charAt(0) == '^') {
            stockSymbol = stockSymbol.substring(1);
        }
        LocalDate latestDate = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT MAX(stock_date) FROM " + stockSymbol);
            rs = ps.executeQuery();
            while (rs.next()) {
                rs.getDate(1).toString();
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return latestDate;
    }

    public LocalDate[] getDates(String stockSymbol) {
        if (stockSymbol.charAt(0) == '^') {
            stockSymbol = stockSymbol.substring(1);
        }
        LocalDate[] dates = new LocalDate[2];
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT MIN(stock_date), MAX(stock_date) FROM " + stockSymbol);
            rs = ps.executeQuery();
            while (rs.next()) {
                dates[0] = LocalDate.parse(rs.getDate(1).toString());
                dates[1] = LocalDate.parse(rs.getDate(2).toString());
            }
            connection.close();
            ps.close();
            return dates;
        } catch (SQLException e) {
        }
        return null;
    }

    public void purgeDatabase() {
        String confirmString = JOptionPane.showInputDialog(null, "Are you sure you want to delete the entire database? Type \"confirm\" into the textbox and click the 'OK' button.", "Purge Database Warning", 0);
        if (confirmString.equalsIgnoreCase("confirm")) {
            try {
                connection = connectToSQL();
                ps = connection.prepareStatement("DROP SCHEMA CSV");
                ps.execute();
                connection.close();
                ps.close();
            } catch (SQLException e) {
            }
        } else {
        }
    }

    //Grabs the stock symbols and inserts their names into the rows. May add stock data for each later (i.e. highs, lows, open, close, profit/loss margins)
    public void populateTable(JTable table, ArrayList<?> arr, String type) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            updateAllTrackerDates();
            Iterator iter;
            if (arr != null && type.equalsIgnoreCase("tracker")) {
                //Object, probably a StockTracker
                iter = arr.iterator();
                while (iter.hasNext()) {
                    StockTracker tracker = (StockTracker) iter.next();
                    Object[] obj = new Object[3];
                    obj[0] = tracker.getOriginalName();
                    LocalDate earliestDate = tracker.getEarliestDate();
                    LocalDate latestDate = tracker.getLatestDate();
                    if (earliestDate == null || latestDate == null) {
                        obj[1] = null;
                        obj[2] = null;
                    } else {
                        obj[1] = earliestDate;
                        obj[2] = latestDate;
                    }
                    model.addRow(obj);
                }
            } else if (arr != null && type.equalsIgnoreCase("stock")) {
                iter = arr.iterator();
                while (iter.hasNext()) {
                    Stock stock = (Stock) iter.next();
                    Object[] obj = new Object[8];
                    obj[0] = stock.getStockName();
                    obj[1] = stock.getDate().toString();
                    obj[2] = stock.getOpen();
                    obj[3] = stock.getHigh();
                    obj[4] = stock.getLow();
                    obj[5] = stock.getClose();
                    obj[6] = stock.getAdjClose();
                    obj[7] = stock.getVolume();
                    model.addRow(obj);
                }
            } else {
                //String
                iter = getTableNames().iterator();
                while (iter.hasNext()) {
                    Object[] obj = {(String) iter.next()};
                    model.addRow(obj);
                }
            }
        } catch (Exception e) {
        }
    }

    //Grabs all tables (stock symbols) and returns them in an ArrayList<String>
    public ArrayList<String> getTableNames() {
        ArrayList<String> stockSymbolList = new ArrayList<>();
        try {
            connection = connectToSQL();
            DatabaseMetaData metaData = connection.getMetaData();
            rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            while (rs.next()) {
                String stockSymbol = rs.getString(3);
                if (stockSymbol.equalsIgnoreCase("stock_tracker")) {
                    //Do nothing. Don't add it.
                } else {
                    stockSymbolList.add(stockSymbol);
                }
            }
            connection.close();
            stockSymbolList.sort(null);
        } catch (SQLException e) {
        }
        Collections.sort(stockSymbolList);
        return stockSymbolList;
    }

    /*==================================STOCK TRACKING======================================*/
 /*
    Table that stores the stock symbol, decoded and encoded names, earliest, and latest dates of 
    stocks for each stock. Purpose/reason for creation: when downloading page data, the stock 
    symbol needs to be modified to be in UTF-8 format otherwise the website will not recognize the 
    symbol. For instance, ^DJI needs to be encoded because of the '^' symbol. So after encoding, 
    it becomes %5EDJI. In MOST cases, the encoded and decoded symbols may be the same. So by 
    storing the encoded name, we can easily perform an update-all operation since the encoded 
    name is already stored and can be easily iterated as a list. The first/last dates are an 
    extra feature which adds a QoL feature for the user to easily see which stocks need updating 
    if manually updating. 
    
    Example(s):
        Full name: ^DJI
        URL: %5EDJI
            URL doesn't accept symbols like '^' so decoding is required.
        SQL: DJI
            SQL doesn't accept symbols like '^' or '%' as a table names so both are removed. 
    
        Full name: DCTH
        URL: DCTH
            No decoding required, URL can accept this as is. 
        SQL: DCTH
            No encoding
     */
    public void createStockTrackerTable() {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("CREATE TABLE stock_tracker("
                    + "original_name VARCHAR(10) UNIQUE NOT NULL, " //DJI
                    + "sql_name VARCHAR(10) UNIQUE NOT NULL, " //^DJI
                    + "url_name VARCHAR(10) UNIQUE NOT NULL, " //%5EDJI
                    + "earliest_date DATE, "
                    + "latest_date DATE)");
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public StockTracker selectTracker(String stockOriginalName) {
        StockTracker tracker = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT * FROM stock_tracker WHERE original_name = ?");
            ps.setString(1, stockOriginalName);
            rs = ps.executeQuery();
            while (rs.next()) {
                String originalName = rs.getString(1);
                String sqlName = rs.getString(2);
                String urlName = rs.getString(3);
                if (rs.getDate(4) == null) {
                    tracker = new StockTracker(originalName, sqlName, urlName);
                } else {
                    tracker = new StockTracker(originalName, sqlName, urlName, LocalDate.parse(rs.getDate(4).toString()), LocalDate.parse(rs.getDate(5).toString()));
                }
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return tracker;
    }

    public ArrayList<StockTracker> selectTracker() {
        ArrayList<StockTracker> arr = new ArrayList<>();
        StockTracker tracker = null;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT * FROM stock_tracker");
            rs = ps.executeQuery();
            while (rs.next()) {
                String originalName = rs.getString(1);
                String sqlName = rs.getString(2);
                String urlName = rs.getString(3);
                if (rs.getDate(4) == null) {
                    tracker = new StockTracker(originalName, sqlName, urlName);
                } else {
                    tracker = new StockTracker(originalName, sqlName, urlName, LocalDate.parse(rs.getDate(4).toString()), LocalDate.parse(rs.getDate(5).toString()));
                }
                arr.add(tracker);
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return arr;
    }

    public ArrayList<String> selectTrackerURLNames() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT url_name FROM stock_tracker");
            rs = ps.executeQuery();
            while (rs.next()) {
                String urlName = rs.getString(1);
                arr.add(urlName);
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return arr;
    }

    public ArrayList<String> selectTrackerOriginalNames() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT original_name FROM stock_tracker");
            rs = ps.executeQuery();
            while (rs.next()) {
                String urlName = rs.getString(1);
                arr.add(urlName);
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return arr;
    }

    public ArrayList<String> selectTrackerSQLNames() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT sql_name FROM stock_tracker");
            rs = ps.executeQuery();
            while (rs.next()) {
                String urlName = rs.getString(1);
                arr.add(urlName);
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
        return arr;
    }

    //To be used by the CSVOptionsGUI ?
    public void insertTracker(String fileName) //^DJI.csv
    {
        if (fileName.contains(".csv")) {
            fileName = fileName.substring(0, fileName.length() - 4);
        }
        //Original Name (the one you see if you open that stock's finance page) Ex. ^DJI
        String stockName = fileName;
        //SQL Name (Remove '^' from name if it has it) Ex. DJI
        String sqlName = stockName;
        if (stockName.charAt(0) == '^') {
            sqlName = stockName.substring(1);
        }
        try {
            //URL Name
            String urlName = URLEncoder.encode(stockName, "UTF-8");
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO stock_tracker (original_name, sql_name, url_name) VALUES (?,?,?)");
            ps.setString(1, stockName);
            ps.setString(2, sqlName);
            ps.setString(3, urlName);
            ps.execute();
            connection.close();
            ps.close();
        } catch (UnsupportedEncodingException | SQLException e) {
        }
    }

    /*
    May not ever be used. When newly creating a stock index, the earliest and latest dates are not given. 
    To add the dates, update is needed, not insert. Keep this method until absolutely certain it isn't needed.
     */
    public void insertTracker(String fileName, LocalDate earliestDate, LocalDate latestDate) //^DJI
    {
        if (fileName.contains(".csv")) {
            fileName = fileName.substring(0, fileName.length() - 4);
        }
        //Original Name (the one you see if you open that stock's finance page) Ex. ^DJI
        String stockName = fileName;
        //SQL Name (Remove '^' from name if it has it) Ex. DJI
        String sqlName = stockName;
        if (stockName.charAt(0) == '^') {
            sqlName = stockName.substring(1);
        }
        try {
            //URL Name
            String urlName = URLEncoder.encode(stockName, "UTF-8");
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO stock_tracker (original_name, sql_name, url_name, earliest_date, latest_date) VALUES (?,?,?,?,?)");
            ps.setString(1, stockName);
            ps.setString(2, sqlName);
            ps.setString(3, urlName);
            ps.setDate(4, Date.valueOf(earliestDate));
            ps.setDate(5, Date.valueOf(latestDate));
            ps.execute();
            connection.close();
            ps.close();
        } catch (UnsupportedEncodingException | SQLException e) {
        }
    }

    public void updateAllTrackerDates() {
        try {
            ArrayList<String> sqlNames = selectTrackerSQLNames();
            Iterator iter = sqlNames.iterator();
            while (iter.hasNext()) {
                String stockIndexName = (String) iter.next();
                LocalDate[] dates = getDates(stockIndexName);
                connection = connectToSQL();
                ps = connection.prepareStatement("UPDATE stock_tracker SET earliest_date = ?, latest_date = ? WHERE sql_name = ?");
                ps.setDate(1, Date.valueOf(dates[0]));
                ps.setDate(2, Date.valueOf(dates[1]));
                ps.setString(3, stockIndexName);
                ps.execute();
            }
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public void deleteTracker(String stockName) { //DJI
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("DELETE FROM stock_tracker WHERE original_name = ? OR sql_name = ? OR url_name = ?");
//            ps = connection.prepareStatement("DROP TABLE stock_tracker");
            ps.setString(1, stockName);
            ps.setString(2, stockName);
            ps.setString(3, stockName);
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public void deleteTracker() { //DJI
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("DROP TABLE stock_tracker");
            ps.execute();
            connection.close();
            ps.close();
            createStockTrackerTable();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        SQLHelper sql = new SQLHelper();
    }
}
