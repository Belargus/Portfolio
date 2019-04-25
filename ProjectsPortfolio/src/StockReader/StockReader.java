package StockReader;

import java.awt.Color;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.SwingWorker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/*
import org.jfree.chart.axis.DateAxis;
import java.util.Collections;
import java.util.HashMap;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
 */

 /*
Get stockMap data.
Create tabs and render charts with the data for each stock. 
 */
public class StockReader extends SwingWorker<String, Object> {

    private Map<String, ArrayList<Stock>> treeMap = new TreeMap<>(); //TreeMap automatically sorts

    SQLHelper sql = new SQLHelper().getInstance();

    public StockReader getInstance() {
        return this;
    }

    public void getDataFromSQL() {
        try {
            treeMap.putAll(sql.selectStocks());
        } catch (Exception e) {
        }
    }

//    public void printStockMap() {
//        if (treeMap.size() > 0) {
//            for (Map.Entry<String, ArrayList<Stock>> entry : treeMap.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }
//        } else {
//            System.out.println("map is empty.");
//        }
//    }

    public void createTabsDisplayCharts(Map<String, ArrayList<Stock>> stockMap) {
        if (stockMap != null) {
            treeMap.putAll(stockMap);
        }
//        StockReaderGUI.main_panel.removeAll();
        Iterator iter;
        Stock stock;
        for (Map.Entry<String, ArrayList<Stock>> entry : treeMap.entrySet()) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries openPrices = new TimeSeries(entry.getKey() + " Open Prices", Day.class);
            TimeSeries closePrices = new TimeSeries(entry.getKey() + " Closing prices", Day.class);
            iter = entry.getValue().iterator();
            while (iter.hasNext()) {
                stock = (Stock) iter.next();
                LocalDate date = stock.getDate();
                openPrices.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()), stock.getOpen());
                closePrices.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()), stock.getClose());
            }
            dataset.addSeries(closePrices);
            dataset.addSeries(openPrices);
            JFreeChart jfc = ChartFactory.createTimeSeriesChart(entry.getKey(), "Dates", "Values", dataset);
            jfc.setBackgroundPaint(new Color(214, 217, 223));
            ChartPanel chartPanel = new ChartPanel(jfc);
            StockReaderGUI.main_panel.addTab(entry.getKey(), chartPanel);

            XYPlot plot = (XYPlot) jfc.getPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Color.GRAY);
            plot.setRangeGridlinePaint(Color.GRAY);
            //            XYItemRenderer renderer = plot.getRenderer();
            //            if (renderer instanceof XYLineAndShapeRenderer) {
            //                ((XYLineAndShapeRenderer) renderer).setBaseShapesVisible(true);
            //            }
            //
            //            DateAxis axis = (DateAxis) plot.getDomainAxis();
            //            axis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));
        }
    }

    @Override
    protected String doInBackground() throws Exception {
//        StockReaderGUI.lbl_srg_progress.setVisible(true);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
}
