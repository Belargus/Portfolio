package StockReader;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
Opens FileExplorer to locate the CSV files.
Stores selected files in 'selectedFiles' HashMap<String filename, File file>.
Parses the files, extracts data, stores in Stock[] array. 
Parse the filename into 'stockSymbol' by removing the extension and any special characters by using URLDecoder. 
Stores the Stock array in 'stockMap' HashMap<String stockSymbol, ArrayList<Stock>>. 
Export the stockMap.
 */
public class CSVParser {

    Map<String, File> selectedFiles;
    Map<String, ArrayList<Stock>> stockMap; //for some reason, the data stored in the stockMap isn't being exported along with the Map
    private String fileDirectory = null;

    public CSVParser() {
        selectedFiles = new HashMap<>();
        stockMap = new HashMap<>();
        new SQLHelper().createStockTrackerTable();
    }

    public CSVParser getInstance() {
        return this;
    }

    //Open JFileChooser to allow user to select the CSV files. Then store the selected files in selectedFiles. 
    public void locateCSVs() {
        stockMap.clear();
        selectedFiles.clear();
        JFileChooser chooser = new JFileChooser();
        Action details = chooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        chooser.setPreferredSize(new Dimension(400,400));
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        String directory = CSVParser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (directory.contains(".jar")) {
            String[] parts = directory.split("/");
            String jarName = parts[parts.length - 1];
            directory = directory.replace(jarName, "");
        }
        chooser.setCurrentDirectory(new File(directory + "CSVs"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        chooser.setAcceptAllFileFilterUsed(true);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            if (files.length > 0) {
                selectedFiles.clear();
                for (int i = 0; i < files.length; i++) {
                    String path = files[i].getAbsolutePath();
                    String filename = files[i].getName();
                    selectedFiles.put(filename, files[i]);
                }
            }
            convertToStock();
        } else {
        }
    }

    //Decodes the filename into a stockSymbol and converts selectedFiles CSVs into ArrayList<Stock>. Stores both in stockMap
    private void convertToStock() {
        if (selectedFiles.size() > 0) {
            for (Map.Entry<String, File> entry : selectedFiles.entrySet()) {
                String stockSymbol = null;
                try {
                    stockSymbol = URLDecoder.decode(entry.getKey(), "UTF-8"); //Decodes filename into stockSymbol (i.e. '%5EDJI' becomes '^DJI')
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(CSVParser.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (stockSymbol == null) {
                    break;
                }
                new SQLHelper().insertTracker(stockSymbol); //Updates stock_tracker database with the name of the newly added stock index
                if (stockSymbol.charAt(0) == '^') {
                    stockSymbol = stockSymbol.substring(1, stockSymbol.length() - 4); //removes the '^', if there is one.
                } else {
                    stockSymbol = stockSymbol.substring(0, stockSymbol.length() - 4);
                }
                ArrayList<Stock> stocks = new ArrayList<>();
                Stock stock;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(entry.getValue()));
                    while (reader.readLine() != null) {
                        String[] line = reader.readLine().split(",");
                        //Error returned. Likely an issue being caused by a comma at the beginning or end of the data. 
                        LocalDate date = LocalDate.parse(line[0]);
                        Float open = Float.parseFloat(line[1]);
                        Float high = Float.parseFloat(line[2]);
                        Float low = Float.parseFloat(line[3]);
                        Float close = Float.parseFloat(line[4]);
                        Float adjClose = Float.parseFloat(line[5]);
                        long volume = Long.parseLong(line[6]);
                        stock = new Stock(stockSymbol, date, open, high, low, close, adjClose, volume);
                        stocks.add(stock);
                    }
                } catch (IOException | NumberFormatException | NullPointerException e) {
                }
                stockMap.put(stockSymbol, stocks);
            }
        } else {
        }
        insertStockMapIntoSQL();
    }

    /*
    To be paired with the CSVDownloader().updateAllCSVs() method. When updateAllCSVs() downloads 
    the latest CSVs of the stocks in the stock_tracker table (triggered by the Update All button in the CSVOptionsGUI), 
    this method will take CSVs of that directory, parse them, and update each Stock's table in the SQL database. 
     */
    public void convertDirectoryToStock(String[] arr) {
        Map<String, ArrayList<Stock>> map = new HashMap<>();
        ArrayList<Stock> stockList = new ArrayList<>();
        String fileDirectory = CSVDownloader.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "CSVs";
        Stock stock;
        String stockSymbol = null;
        if (arr != null && arr.length > 0) {
            try {
                File[] files = new File(fileDirectory).listFiles();
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getName();
                    stockSymbol = fileName.substring(0, fileName.length() - 4);
                    BufferedReader reader = new BufferedReader(new FileReader(files[i]));
                    while (reader.readLine() != null) {
                        try {
                            String[] line = reader.readLine().split(",");
                            LocalDate date = LocalDate.parse(line[0]);
                            Float open = Float.parseFloat(line[1]);
                            Float high = Float.parseFloat(line[2]);
                            Float low = Float.parseFloat(line[3]);
                            Float close = Float.parseFloat(line[4]);
                            Float adjClose = Float.parseFloat(line[5]);
                            long volume = Long.parseLong(line[6]);
                            stock = new Stock(stockSymbol, date, open, high, low, close, adjClose, volume);
                            stockList.add(stock);
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (IOException | NumberFormatException | NullPointerException e) {
            }
            map.put(stockSymbol, stockList);
            new SQLHelper().insertStockMap(map);
        }
    }

    public void getCSVsFolderPath() {
        if (fileDirectory == null) {
            fileDirectory = CSVParser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            if (fileDirectory.contains(".jar")) {
                String[] parts = fileDirectory.split("/");
                String jarName = parts[parts.length - 1];
                fileDirectory = fileDirectory.replace(jarName, "");
            }
        }
        File folder = new File(fileDirectory, "CSVs");
        folder.mkdir();
    }

//    public void printStockMap() {
//        for (Map.Entry<String, ArrayList<Stock>> entry : stockMap.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//    }
//
//    public void printStockMap(Map<String, ArrayList<Stock>> map) {
//        for (Map.Entry<String, ArrayList<Stock>> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//    }

    public void insertStockMapIntoSQL() {
        SQLHelper sql = new SQLHelper();
        sql.insertStockMap(stockMap);
    }

    public Map<String, ArrayList<Stock>> returnStockMap() {
        return stockMap;
    }

    public static void main(String[] args) {
        CSVParser csvp = new CSVParser();
        csvp.locateCSVs();
    }
}
