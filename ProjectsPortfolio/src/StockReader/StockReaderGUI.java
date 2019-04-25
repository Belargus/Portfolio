package StockReader;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StockReaderGUI extends javax.swing.JFrame {

    ArrayList<File> arr = new ArrayList();
    StockReader sr = new StockReader().getInstance();
    SQLHelper sql = new SQLHelper().getInstance();
    CSVDownloader csvd = new CSVDownloader().getInstance();
    CSVOptionsGUI csvo;

    public StockReaderGUI() {
        initComponents();
        ArrayList<Stock> arrLatestStocks = sql.selectLatestStocks();
        sql.populateTable(tbl_gui, arrLatestStocks, "stock");
    }
    
    public StockReaderGUI(String s)
    {
        
    }

    public StockReaderGUI getInstance() {
        return this;
    }

    private void clearUserDefinedFields() {
        tf_user_firstdate.setText("");
        tf_user_lastdate.setText("");
        tf_user_lowestprice.setText("");
        tf_user_peakprice.setText("");
        tf_user_avgprice.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        tf_avgprice1 = new javax.swing.JTextField();
        testlabel3 = new javax.swing.JLabel();
        label_peak1 = new javax.swing.JLabel();
        tf_peakprice1 = new javax.swing.JTextField();
        tf_lowestprice1 = new javax.swing.JTextField();
        label_lowestprice1 = new javax.swing.JLabel();
        main_panel = new javax.swing.JTabbedPane();
        pnl_combined = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_gui = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_displaycharts = new javax.swing.JButton();
        testlabel2 = new javax.swing.JLabel();
        tf_hist_avgprice = new javax.swing.JTextField();
        label_peak = new javax.swing.JLabel();
        tf_hist_peakprice = new javax.swing.JTextField();
        label_lowestprice = new javax.swing.JLabel();
        tf_hist_lowestprice = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_hist_firstdate = new javax.swing.JTextField();
        tf_hist_lastdate = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        tf_user_avgprice = new javax.swing.JTextField();
        label_peak2 = new javax.swing.JLabel();
        tf_user_peakprice = new javax.swing.JTextField();
        label_lowestprice2 = new javax.swing.JLabel();
        tf_user_firstdate = new javax.swing.JTextField();
        tf_user_lowestprice = new javax.swing.JTextField();
        tf_user_lastdate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        testlabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        srg_menuitem_selectfiles = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Historical Data");

        jLabel6.setText("Data from:");

        jTextField3.setBackground(java.awt.SystemColor.controlHighlight);

        jTextField4.setBackground(java.awt.SystemColor.controlHighlight);

        tf_avgprice1.setBackground(java.awt.SystemColor.controlHighlight);

        testlabel3.setText("Average Price: ");

        label_peak1.setText("Peak Price: ");

        tf_peakprice1.setBackground(java.awt.SystemColor.controlHighlight);

        tf_lowestprice1.setBackground(java.awt.SystemColor.controlHighlight);

        label_lowestprice1.setText("Lowest Price: ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stock Reader");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        main_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main_panelMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                main_panelMouseReleased(evt);
            }
        });

        tbl_gui.setAutoCreateRowSorter(true);
        tbl_gui.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock", "Date", "Open", "High", "Low", "Close", "AdjClose", "Volume"
            }
        ));
        jScrollPane1.setViewportView(tbl_gui);

        javax.swing.GroupLayout pnl_combinedLayout = new javax.swing.GroupLayout(pnl_combined);
        pnl_combined.setLayout(pnl_combinedLayout);
        pnl_combinedLayout.setHorizontalGroup(
            pnl_combinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_combinedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_combinedLayout.setVerticalGroup(
            pnl_combinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_combinedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.addTab("Stock Summary", pnl_combined);

        btn_displaycharts.setText("Refresh/Display Charts");
        btn_displaycharts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_displaychartsActionPerformed(evt);
            }
        });

        testlabel2.setText("Average Price: ");

        tf_hist_avgprice.setEditable(false);
        tf_hist_avgprice.setBackground(java.awt.SystemColor.controlHighlight);

        label_peak.setText("Peak Price: ");

        tf_hist_peakprice.setEditable(false);
        tf_hist_peakprice.setBackground(java.awt.SystemColor.controlHighlight);

        label_lowestprice.setText("Lowest Price: ");

        tf_hist_lowestprice.setEditable(false);
        tf_hist_lowestprice.setBackground(java.awt.SystemColor.controlHighlight);

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jLabel3.setText("Data from:");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Historical Data");

        tf_hist_firstdate.setEditable(false);
        tf_hist_firstdate.setBackground(java.awt.SystemColor.controlHighlight);

        tf_hist_lastdate.setEditable(false);
        tf_hist_lastdate.setBackground(java.awt.SystemColor.controlHighlight);
        tf_hist_lastdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_hist_lastdateActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("User-Defined Date Range");

        tf_user_avgprice.setEditable(false);
        tf_user_avgprice.setBackground(java.awt.SystemColor.controlHighlight);

        label_peak2.setText("Peak Price: ");

        tf_user_peakprice.setEditable(false);
        tf_user_peakprice.setBackground(java.awt.SystemColor.controlHighlight);

        label_lowestprice2.setText("Lowest Price: ");

        tf_user_lowestprice.setEditable(false);
        tf_user_lowestprice.setBackground(java.awt.SystemColor.controlHighlight);

        tf_user_lastdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_user_lastdateActionPerformed(evt);
            }
        });

        jLabel8.setText("Data from:");

        testlabel4.setText("Average Price: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_displaycharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_update))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label_peak, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(testlabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel3)
                                    .addComponent(label_lowestprice))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_hist_lowestprice)
                                    .addComponent(tf_hist_avgprice)
                                    .addComponent(tf_hist_peakprice)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tf_hist_firstdate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                        .addComponent(tf_hist_lastdate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator1)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label_peak2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(testlabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel8)
                                    .addComponent(label_lowestprice2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_user_lowestprice)
                                    .addComponent(tf_user_avgprice)
                                    .addComponent(tf_user_peakprice)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tf_user_firstdate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tf_user_lastdate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_hist_firstdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_hist_lastdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(testlabel2)
                    .addComponent(tf_hist_avgprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_peak)
                    .addComponent(tf_hist_peakprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_lowestprice)
                    .addComponent(tf_hist_lowestprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tf_user_firstdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_user_lastdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(testlabel4)
                    .addComponent(tf_user_avgprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_peak2)
                    .addComponent(tf_user_peakprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_lowestprice2)
                    .addComponent(tf_user_lowestprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_displaycharts))
        );

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        srg_menuitem_selectfiles.setText("Select CSV File...");
        srg_menuitem_selectfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srg_menuitem_selectfilesActionPerformed(evt);
            }
        });
        jMenu1.add(srg_menuitem_selectfiles);

        jMenuItem1.setText("Exit StockReader");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(main_panel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(main_panel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void srg_menuitem_selectfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srg_menuitem_selectfilesActionPerformed
        csvo = new CSVOptionsGUI();
        csvo.setLocationRelativeTo(null);
        csvo.setDefaultCloseOperation(2);
        csvo.setVisible(true);
    }//GEN-LAST:event_srg_menuitem_selectfilesActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void btn_displaychartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_displaychartsActionPerformed
        setCursor(WAIT_CURSOR);
        int tabCount = StockReaderGUI.main_panel.getTabCount() - 1;
        for (int i = tabCount; i > 0; i--) {
            StockReaderGUI.main_panel.removeTabAt(i);
        }
        sr = new StockReader();
        clearUserDefinedFields();
        sr.getDataFromSQL();
        sr.createTabsDisplayCharts(null);
        try {
            String stockSymbol = main_panel.getTitleAt(main_panel.getSelectedIndex());
            if (!stockSymbol.equalsIgnoreCase("Stock Summary")) {
                //Historical Data
                LocalDate[] dates = sql.getDates(stockSymbol);
                tf_hist_firstdate.setText(dates[0].toString()); //The very first stock date
                tf_hist_lastdate.setText(dates[1].toString()); //The very last stock date

                Float[] prices = sql.getPrices(stockSymbol, dates[0], dates[1]); //min, max, avg
                tf_hist_lowestprice.setText(prices[0].toString());
                tf_hist_peakprice.setText(prices[1].toString());
                tf_hist_avgprice.setText(prices[2].toString());

                //User-defined range
                if (!tf_user_firstdate.getText().equalsIgnoreCase("") && !tf_user_lastdate.getText().equalsIgnoreCase("")) {
                    LocalDate startDate = LocalDate.parse(tf_user_firstdate.getText());
                    LocalDate endDate = LocalDate.parse(tf_user_lastdate.getText());
                    Float[] rangedPrices = sql.getPrices(stockSymbol, startDate, endDate);
                    tf_user_lowestprice.setText(rangedPrices[0].toString());
                    tf_user_peakprice.setText(rangedPrices[1].toString());
                    tf_user_avgprice.setText(rangedPrices[2].toString());
                }
            }
        } catch (Exception e) {
        }
        setCursor(null);
    }//GEN-LAST:event_btn_displaychartsActionPerformed

    private void main_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_panelMouseClicked

    }//GEN-LAST:event_main_panelMouseClicked

    private void main_panelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_panelMouseReleased
        String tabName = main_panel.getTitleAt(main_panel.getSelectedIndex());
        if (!tabName.equalsIgnoreCase("Stock Summary")) {
            //Historical
            LocalDate[] dates = sql.getDates(tabName);
            tf_hist_firstdate.setText(dates[0].toString());
            tf_hist_lastdate.setText(dates[1].toString());

            Float[] prices = sql.getPrices(tabName, dates[0], dates[1]);
            tf_hist_lowestprice.setText(prices[0].toString());
            tf_hist_peakprice.setText(prices[1].toString());
            tf_hist_avgprice.setText(prices[2].toString());

            //User-defined range
            if (!tf_user_firstdate.getText().equals("") || !tf_user_lastdate.getText().equals("")) {
                LocalDate startDate = LocalDate.parse(tf_user_firstdate.getText());
                LocalDate endDate = LocalDate.parse(tf_user_lastdate.getText());
                Float[] rangedPrices = sql.getPrices(tabName, startDate, endDate);
                tf_user_lowestprice.setText(rangedPrices[0].toString());
                tf_user_peakprice.setText(rangedPrices[1].toString());
                tf_user_avgprice.setText(rangedPrices[2].toString());
            }
        }
    }//GEN-LAST:event_main_panelMouseReleased


    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        //update the map to reflect the data from between the specified dates
        setCursor(WAIT_CURSOR);
        String tabName = main_panel.getTitleAt(main_panel.getSelectedIndex());
        if (!tabName.equalsIgnoreCase("Stock Summary")) {
            if (!tf_user_firstdate.getText().equalsIgnoreCase("") && !tf_user_lastdate.getText().equalsIgnoreCase("")) {
                LocalDate startDate = LocalDate.parse(tf_user_firstdate.getText());
                LocalDate endDate = LocalDate.parse(tf_user_lastdate.getText());
                Float[] prices = sql.getPrices(tabName, startDate, endDate);

                tf_user_avgprice.setText(prices[2].toString());
                tf_user_lowestprice.setText(prices[0].toString());
                tf_user_peakprice.setText(prices[1].toString());
                int tabCount = StockReaderGUI.main_panel.getTabCount() - 1;
                for (int i = tabCount; i > 0; i--) {
                    StockReaderGUI.main_panel.removeTabAt(i);
                }
                sr = new StockReader();
                sr.createTabsDisplayCharts(new SQLHelper().selectStocks(startDate, endDate));
            }
        }
        setCursor(null);
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tf_hist_lastdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_hist_lastdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_hist_lastdateActionPerformed

    private void tf_user_lastdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_user_lastdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_user_lastdateActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (csvo.isShowing()) {
                csvo.dispose();
            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockReaderGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new StockReaderGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_displaycharts;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel label_lowestprice;
    private javax.swing.JLabel label_lowestprice1;
    private javax.swing.JLabel label_lowestprice2;
    private javax.swing.JLabel label_peak;
    private javax.swing.JLabel label_peak1;
    private javax.swing.JLabel label_peak2;
    public static javax.swing.JTabbedPane main_panel;
    public static javax.swing.JPanel pnl_combined;
    private javax.swing.JMenuItem srg_menuitem_selectfiles;
    public static javax.swing.JTable tbl_gui;
    public static javax.swing.JLabel testlabel2;
    public static javax.swing.JLabel testlabel3;
    public static javax.swing.JLabel testlabel4;
    private javax.swing.JTextField tf_avgprice1;
    private javax.swing.JTextField tf_hist_avgprice;
    private javax.swing.JTextField tf_hist_firstdate;
    private javax.swing.JTextField tf_hist_lastdate;
    private javax.swing.JTextField tf_hist_lowestprice;
    private javax.swing.JTextField tf_hist_peakprice;
    private javax.swing.JTextField tf_lowestprice1;
    private javax.swing.JTextField tf_peakprice1;
    private javax.swing.JTextField tf_user_avgprice;
    private javax.swing.JTextField tf_user_firstdate;
    private javax.swing.JTextField tf_user_lastdate;
    private javax.swing.JTextField tf_user_lowestprice;
    private javax.swing.JTextField tf_user_peakprice;
    // End of variables declaration//GEN-END:variables
}
