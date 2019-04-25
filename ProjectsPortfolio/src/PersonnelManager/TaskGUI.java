package PersonnelManager;

import javax.swing.JOptionPane;

public class TaskGUI extends javax.swing.JFrame {

    SQLHelper sql = new SQLHelper();

    public TaskGUI() {
        initComponents();
        textarea_taskdetails.grabFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea_taskdetails = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_assigneduserid = new javax.swing.JTextField();
        tf_status = new javax.swing.JTextField();
        tf_taskdeadline = new javax.swing.JTextField();
        tf_taskstartdate = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btn_inserttask = new javax.swing.JButton();
        btn_updatetask = new javax.swing.JButton();
        btn_deletetask = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tf_taskid = new javax.swing.JTextField();

        jButton3.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modify task...");

        jLabel1.setText("Task details:");

        textarea_taskdetails.setColumns(20);
        textarea_taskdetails.setRows(5);
        jScrollPane1.setViewportView(textarea_taskdetails);

        jLabel2.setText("Task start date:");

        jLabel3.setText("Task deadline:");

        jLabel4.setText("Status:");

        jLabel5.setText("Assigned user (ID):");

        tf_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_statusActionPerformed(evt);
            }
        });

        tf_taskdeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_taskdeadlineActionPerformed(evt);
            }
        });

        btn_inserttask.setText("Insert");
        btn_inserttask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inserttaskActionPerformed(evt);
            }
        });

        btn_updatetask.setText("Update");
        btn_updatetask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updatetaskActionPerformed(evt);
            }
        });

        btn_deletetask.setText("Delete");
        btn_deletetask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletetaskActionPerformed(evt);
            }
        });

        jLabel6.setText("Task ID:");

        tf_taskid.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(btn_updatetask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_deletetask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_taskstartdate)
                                    .addComponent(tf_taskdeadline)
                                    .addComponent(tf_status)
                                    .addComponent(tf_assigneduserid, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                            .addComponent(btn_inserttask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_taskid)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tf_taskid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_taskstartdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_taskdeadline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_assigneduserid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_inserttask)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_updatetask)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_deletetask)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_taskdeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_taskdeadlineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_taskdeadlineActionPerformed

    private void tf_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_statusActionPerformed

    private void btn_inserttaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inserttaskActionPerformed
        if (textarea_taskdetails.getText().equals("") || tf_taskstartdate.getText().equals("") || tf_taskdeadline.getText().equals("") || tf_status.getText().equals("") || tf_assigneduserid.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "All fields must be completed to create this task.");
        } else {
            sql.insertTask(textarea_taskdetails.getText(), tf_taskstartdate.getText(), tf_taskdeadline.getText(), tf_status.getText(), Integer.parseInt(tf_assigneduserid.getText()));
            dispose();
        }
    }//GEN-LAST:event_btn_inserttaskActionPerformed

    private void btn_deletetaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletetaskActionPerformed
        if (!tf_taskid.getText().equals("")) {
            sql.deleteTask(Integer.parseInt(tf_taskid.getText()));
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Unable to delete unless the Task ID field is filled with a valid ID.");
        }
    }//GEN-LAST:event_btn_deletetaskActionPerformed

    private void btn_updatetaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updatetaskActionPerformed
        if (!tf_taskid.getText().equals("") && !textarea_taskdetails.getText().equals("") && !tf_taskstartdate.getText().equals("") && !tf_taskdeadline.getText().equals("") && !tf_status.getText().equals("") && !tf_assigneduserid.getText().equals("")) {
            sql.updateTask(Integer.parseInt(tf_taskid.getText()), textarea_taskdetails.getText(), tf_taskstartdate.getText(), tf_taskdeadline.getText(), tf_status.getText(), Integer.parseInt(tf_assigneduserid.getText()));
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "All fields must be completed to update this task.");
        }
    }//GEN-LAST:event_btn_updatetaskActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TaskGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_deletetask;
    private javax.swing.JButton btn_inserttask;
    private javax.swing.JButton btn_updatetask;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextArea textarea_taskdetails;
    public static javax.swing.JTextField tf_assigneduserid;
    public static javax.swing.JTextField tf_status;
    public static javax.swing.JTextField tf_taskdeadline;
    public static javax.swing.JTextField tf_taskid;
    public static javax.swing.JTextField tf_taskstartdate;
    // End of variables declaration//GEN-END:variables
}
