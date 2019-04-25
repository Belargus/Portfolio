package PersonnelManager;

import java.awt.Image;
import java.io.File;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PersonnelManagerGUI extends javax.swing.JFrame {

    SQLHelper sql = new SQLHelper();
    private String path = null; //the path of the image to be uploaded to SQL
    private String savedPath = null;
    TaskGUI taskGUI;

    public PersonnelManagerGUI() {
        initComponents();
        tf_firstname.grabFocus();
        tf_startdate.setText(LocalDate.now().toString());
        tf_enddate.setText(LocalDate.now().toString());
        btn_female.setSelected(true);
        startSQL();
    }

    private void startSQL() {
        sql.firstTimeSetupSQL();
        sql.populateTable(sql.selectUsers(""), tbl_userregistry);
        sql.populateTable(sql.selectTasks(""), tbl_taskregistry);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrp_biosex = new javax.swing.ButtonGroup();
        panel_user = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_userid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_firstname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_lastname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_startdate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_enddate = new javax.swing.JTextField();
        lbl_image = new javax.swing.JLabel();
        btn_selectimage = new javax.swing.JButton();
        btn_insertuser = new javax.swing.JButton();
        btn_updateuser = new javax.swing.JButton();
        btn_deleteuser = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_female = new javax.swing.JRadioButton();
        btn_male = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        combox_jobtitle = new javax.swing.JComboBox<>();
        btn_clear = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_userregistry = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_taskregistry = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        tf_search = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_createtask = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management Portal");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("User ID:");

        tf_userid.setBackground(new java.awt.Color(204, 204, 204));
        tf_userid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_useridKeyReleased(evt);
            }
        });

        jLabel2.setText("First name:");

        tf_firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_firstnameActionPerformed(evt);
            }
        });

        jLabel3.setText("Last name:");

        tf_lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_lastnameActionPerformed(evt);
            }
        });

        jLabel4.setText("Job title:");

        jLabel5.setText("Start date:");

        jLabel6.setText("End date:");

        btn_selectimage.setText("Select image...");
        btn_selectimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selectimageActionPerformed(evt);
            }
        });

        btn_insertuser.setText("Insert");
        btn_insertuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertuserActionPerformed(evt);
            }
        });

        btn_updateuser.setText("Update");
        btn_updateuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateuserActionPerformed(evt);
            }
        });

        btn_deleteuser.setText("Delete");
        btn_deleteuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteuserActionPerformed(evt);
            }
        });

        jLabel7.setText("Biological sex:");

        btngrp_biosex.add(btn_female);
        btn_female.setText("Female");

        btngrp_biosex.add(btn_male);
        btn_male.setText("Male");
        btn_male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_maleActionPerformed(evt);
            }
        });

        combox_jobtitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Programmer", "Manager", "Intern", "Secretary", "Janitor", "Chef", "Electrician", "CEO", "Supervisor", "Professor", "Doctor (MD)", "Chemist", "Electrician", "Electrical Engineer", "Mechanical Engineer" }));

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_userLayout = new javax.swing.GroupLayout(panel_user);
        panel_user.setLayout(panel_userLayout);
        panel_userLayout.setHorizontalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_userLayout.createSequentialGroup()
                        .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(12, 12, 12)
                        .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_startdate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_lastname, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_userid, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_firstname)
                            .addComponent(tf_enddate)
                            .addComponent(combox_jobtitle, 0, 1, Short.MAX_VALUE)))
                    .addComponent(btn_updateuser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_insertuser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_deleteuser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_userLayout.createSequentialGroup()
                        .addComponent(btn_selectimage, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_userLayout.createSequentialGroup()
                        .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_userLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_male)
                                    .addComponent(btn_female)))
                            .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel_userLayout.setVerticalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_userid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(combox_jobtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_startdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tf_enddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btn_female))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_male)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_selectimage)
                    .addComponent(btn_clear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_insertuser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_updateuser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_deleteuser)
                .addGap(355, 355, 355))
        );

        tbl_userregistry.setAutoCreateRowSorter(true);
        tbl_userregistry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First name", "Last name", "Sex", "Job title", "Start date", "End date"
            }
        ));
        tbl_userregistry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_userregistryMouseReleased(evt);
            }
        });
        tbl_userregistry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_userregistryKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_userregistryKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_userregistry);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("User Registry", jPanel2);

        tbl_taskregistry.setAutoCreateRowSorter(true);
        tbl_taskregistry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task ID", "Task Details", "Task start date", "Task deadline", "Status", "Assigned User"
            }
        ));
        tbl_taskregistry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_taskregistryMouseReleased(evt);
            }
        });
        tbl_taskregistry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_taskregistryKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_taskregistry);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Task Registry", jPanel3);

        tf_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_searchActionPerformed(evt);
            }
        });
        tf_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_searchKeyReleased(evt);
            }
        });

        jLabel8.setText("Search for:");

        btn_createtask.setText("Create new task...");
        btn_createtask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createtaskActionPerformed(evt);
            }
        });

        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("[TESTING]Create random task");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("[TESTING]Create random user");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_createtask)
                .addGap(61, 61, 61)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8)
                .addComponent(btn_createtask)
                .addComponent(jButton1)
                .addComponent(jButton2))
        );

        jMenu1.setText("File");

        jMenuItem2.setText("Export to File...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Exit PersonnelManager");
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_user, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tf_firstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_firstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_firstnameActionPerformed

    private void tf_lastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_lastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_lastnameActionPerformed

    private void btn_maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_maleActionPerformed

    private void tf_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_searchKeyReleased
        sql.populateTable(sql.selectUsers(tf_search.getText()), tbl_userregistry);
        sql.populateTable(sql.selectTasks(tf_search.getText()), tbl_taskregistry);
    }//GEN-LAST:event_tf_searchKeyReleased

    private void btn_insertuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertuserActionPerformed
        if (tf_firstname.getText().equals("") || tf_lastname.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "The First name and Last name fields must be completed to continue.");
        } else {
            String biosex = "Female";
            if (btn_male.isSelected()) {
                biosex = "Male";
            }
            sql.insertUser(tf_firstname.getText(), tf_lastname.getText(), combox_jobtitle.getSelectedItem().toString(), tf_startdate.getText(), tf_enddate.getText(), biosex, path);
        }
        sql.populateTable(sql.selectUsers(tf_search.getText()), tbl_userregistry);
        sql.populateTable(sql.selectTasks(tf_search.getText()), tbl_taskregistry);
    }//GEN-LAST:event_btn_insertuserActionPerformed

    private void btn_updateuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateuserActionPerformed
        if (tf_userid.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "User id field cannot be blank.");
        } else {
            int userid = Integer.parseInt(tf_userid.getText());
            String biosex = "Female";
            if (btn_male.isSelected()) {
                biosex = "Male";
            }
            sql.updateUser(userid, tf_firstname.getText(), tf_lastname.getText(), combox_jobtitle.getSelectedItem().toString(), tf_startdate.getText(), tf_enddate.getText(), biosex, path);
        }
        sql.populateTable(sql.selectUsers(tf_search.getText()), tbl_userregistry);
        sql.populateTable(sql.selectTasks(tf_search.getText()), tbl_taskregistry);
    }//GEN-LAST:event_btn_updateuserActionPerformed

    private void btn_deleteuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteuserActionPerformed
        int userid = Integer.parseInt(tf_userid.getText());
        sql.deleteUser(userid);
        clearFields();
        tbl_userregistry.getSelectionModel().setSelectionInterval(0, 0);
        int rownum = tbl_userregistry.getSelectedRow();
        String useridString = tbl_userregistry.getValueAt(rownum, 0).toString();
        tf_userid.setText(useridString);
        tf_firstname.setText(tbl_userregistry.getValueAt(rownum, 1).toString());
        tf_lastname.setText(tbl_userregistry.getValueAt(rownum, 2).toString());
        combox_jobtitle.setSelectedItem(tbl_userregistry.getValueAt(rownum, 4).toString());
        tf_startdate.setText(tbl_userregistry.getValueAt(rownum, 5).toString());
        tf_enddate.setText(tbl_userregistry.getValueAt(rownum, 6).toString());
        String biosex = tbl_userregistry.getValueAt(rownum, 3).toString();
        if (biosex.equalsIgnoreCase("Male")) {
            btn_male.setSelected(true);
        } else {
            btn_female.setSelected(true);
        }
        sql.displayPicture(sql.selectUserPicture(Integer.parseInt(useridString)));
        sql.populateTable(sql.selectUsers(tf_search.getText()), tbl_userregistry);
        sql.populateTable(sql.selectTasks(tf_search.getText()), tbl_taskregistry);
    }//GEN-LAST:event_btn_deleteuserActionPerformed

    private void tbl_userregistryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_userregistryMouseReleased
        clearFields();
        int rownum = tbl_userregistry.getSelectedRow();
        String userid = tbl_userregistry.getValueAt(rownum, 0).toString();
        tf_userid.setText(userid);
        tf_firstname.setText(tbl_userregistry.getValueAt(rownum, 1).toString());
        tf_lastname.setText(tbl_userregistry.getValueAt(rownum, 2).toString());
        combox_jobtitle.setSelectedItem(tbl_userregistry.getValueAt(rownum, 4).toString());
        tf_startdate.setText(tbl_userregistry.getValueAt(rownum, 5).toString());
        tf_enddate.setText(tbl_userregistry.getValueAt(rownum, 6).toString());
        String biosex = tbl_userregistry.getValueAt(rownum, 3).toString();
        if (biosex.equalsIgnoreCase("Male")) {
            btn_male.setSelected(true);
        } else {
            btn_female.setSelected(true);
        }
        sql.displayPicture(sql.selectUserPicture(Integer.parseInt(userid)));
    }//GEN-LAST:event_tbl_userregistryMouseReleased

    private void btn_createtaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createtaskActionPerformed
        taskGUI = new TaskGUI();
        TaskGUI.tf_taskstartdate.setText(LocalDate.now().toString());
        TaskGUI.tf_taskdeadline.setText(LocalDate.now().toString());
        taskGUI.setLocationRelativeTo(this);
        taskGUI.setDefaultCloseOperation(2);
        taskGUI.setVisible(true);
    }//GEN-LAST:event_btn_createtaskActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sql.insertTestTask();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        sql.insertTestUser();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_taskregistryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_taskregistryMouseReleased
        int rowClicked = tbl_taskregistry.getSelectedRow();
        TaskGUI taskgui = new TaskGUI();
        taskgui.setDefaultCloseOperation(2);
        taskgui.setLocationRelativeTo(this);
        taskgui.setVisible(true);
        TaskGUI.tf_taskid.setText(tbl_taskregistry.getValueAt(rowClicked, 0).toString());
        TaskGUI.textarea_taskdetails.setText(tbl_taskregistry.getValueAt(rowClicked, 1).toString());
        TaskGUI.tf_taskstartdate.setText(tbl_taskregistry.getValueAt(rowClicked, 2).toString());
        TaskGUI.tf_taskdeadline.setText(tbl_taskregistry.getValueAt(rowClicked, 3).toString());
        TaskGUI.tf_status.setText(tbl_taskregistry.getValueAt(rowClicked, 4).toString());
        TaskGUI.tf_assigneduserid.setText(tbl_taskregistry.getValueAt(rowClicked, 5).toString());
    }//GEN-LAST:event_tbl_taskregistryMouseReleased

    private void btn_selectimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selectimageActionPerformed
        JFileChooser chooser = new JFileChooser();
        if (path == null && savedPath == null) {
            try {
                path = PersonnelManagerGUI.class.getClassLoader().getResource("Resources/Images").toURI().toString().replace("file:", "");
                savedPath = path;
            } catch (URISyntaxException ex) {
                Logger.getLogger(PersonnelManagerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (savedPath != null) {
            path = savedPath;
        }
        chooser.setCurrentDirectory(new File(path));
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            path = file.getAbsolutePath();
            savedPath = path;
            ImageIcon imageIcon = new ImageIcon(path);
            Image image = imageIcon.getImage();
            if (image.getWidth(this) > image.getHeight(this)) {
                image = image.getScaledInstance(lbl_image.getWidth(), image.getHeight(this) * lbl_image.getWidth() / image.getWidth(this), Image.SCALE_SMOOTH);
            } else {
                image = image.getScaledInstance(lbl_image.getHeight() * image.getWidth(this) / image.getHeight(this), lbl_image.getHeight(), Image.SCALE_SMOOTH);
            }
            imageIcon = new ImageIcon(image);
            lbl_image.setHorizontalAlignment(0);
            lbl_image.setIcon(imageIcon);
        } else {
            JOptionPane.showMessageDialog(null, "No file selected.");
        }
    }//GEN-LAST:event_btn_selectimageActionPerformed

    private void tf_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_searchActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        tf_userid.setText("");
        tf_firstname.setText("");
        tf_lastname.setText("");
        combox_jobtitle.setSelectedIndex(0);
        tf_startdate.setText(LocalDate.now().toString());
        tf_enddate.setText(LocalDate.now().toString());
        btn_female.setSelected(true);
        lbl_image.setIcon(null);
        path = null;
        tf_search.setText("");
        sql.populateTable(sql.selectUsers(tf_search.getText()), tbl_userregistry);
        sql.populateTable(sql.selectTasks(tf_search.getText()), tbl_taskregistry);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void tf_useridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_useridKeyReleased
        clearFields();
        if (!tf_userid.getText().equals("")) {
            Object[] obj = sql.selectUsers(Integer.parseInt(tf_userid.getText()));
            if (obj != null) {
                try {
                    tf_userid.setText(obj[0].toString());
                    tf_firstname.setText(obj[1].toString());
                    tf_lastname.setText(obj[2].toString());
                    combox_jobtitle.setSelectedItem(obj[4].toString());
                    tf_startdate.setText(obj[5].toString());
                    tf_enddate.setText(obj[6].toString());
                    if (obj[3].toString().equalsIgnoreCase("male")) {
                        btn_male.setSelected(true);
                    } else {
                        btn_female.setSelected(true);
                    }
                    if (obj[7] != null) {
                        ImageIcon imageIcon = (ImageIcon) obj[7];
                        Image image = imageIcon.getImage();
                        if (image.getWidth(this) > image.getHeight(this)) {
                            image = image.getScaledInstance(lbl_image.getWidth(), image.getHeight(this) * lbl_image.getWidth() / image.getWidth(this), Image.SCALE_SMOOTH);
                        } else {
                            image = image.getScaledInstance(lbl_image.getHeight() * image.getWidth(this) / image.getHeight(this), lbl_image.getHeight(), Image.SCALE_SMOOTH);
                        }
                        imageIcon = new ImageIcon(image);
                        lbl_image.setHorizontalAlignment(0);
                        lbl_image.setIcon(imageIcon);
                    }
                } catch (Exception e) {
                }
            } else {
                clearFields();
            }
        } else {
            clearFields();
        }
    }//GEN-LAST:event_tf_useridKeyReleased

    private void tbl_userregistryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_userregistryKeyReleased
    }//GEN-LAST:event_tbl_userregistryKeyReleased

    private void tbl_userregistryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_userregistryKeyPressed
        int rowSelected = tbl_userregistry.getSelectedRow();
        int numberRows = tbl_userregistry.getRowCount() - 1;
        if (evt.getKeyCode() == 38) {
            if (rowSelected == 0) {
            } else {
                rowSelected--;
            }
        } else if (evt.getKeyCode() == 40) {
            if (rowSelected == numberRows) {
            } else {
                rowSelected++;
            }
        }
        clearFields();
        String userid = tbl_userregistry.getValueAt(rowSelected, 0).toString();
        tf_userid.setText(userid);
        tf_firstname.setText(tbl_userregistry.getValueAt(rowSelected, 1).toString());
        tf_lastname.setText(tbl_userregistry.getValueAt(rowSelected, 2).toString());
        combox_jobtitle.setSelectedItem(tbl_userregistry.getValueAt(rowSelected, 4).toString());
        tf_startdate.setText(tbl_userregistry.getValueAt(rowSelected, 5).toString());
        tf_enddate.setText(tbl_userregistry.getValueAt(rowSelected, 6).toString());
        String biosex = tbl_userregistry.getValueAt(rowSelected, 3).toString();
        if (biosex.equalsIgnoreCase("Male")) {
            btn_male.setSelected(true);
        } else {
            btn_female.setSelected(true);
        }
        sql.displayPicture(sql.selectUserPicture(Integer.parseInt(userid)));
    }//GEN-LAST:event_tbl_userregistryKeyPressed

    private void tbl_taskregistryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_taskregistryKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_taskregistryKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (taskGUI.isShowing()) {
                taskGUI.dispose();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ExcelCreator ec = new ExcelCreator();
        ec.createExcelSheet(tbl_userregistry, tbl_taskregistry);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public void clearFields() {
        tf_firstname.setText("");
        tf_lastname.setText("");
        combox_jobtitle.setSelectedIndex(0);
        tf_startdate.setText(LocalDate.now().toString());
        tf_enddate.setText(LocalDate.now().toString());
        btn_female.setSelected(true);
        lbl_image.setIcon(null);
        path = null;
    }

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
            java.util.logging.Logger.getLogger(PersonnelManagerGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PersonnelManagerGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_createtask;
    private javax.swing.JButton btn_deleteuser;
    private javax.swing.JRadioButton btn_female;
    private javax.swing.JButton btn_insertuser;
    private javax.swing.JRadioButton btn_male;
    private javax.swing.JButton btn_selectimage;
    private javax.swing.JButton btn_updateuser;
    private javax.swing.ButtonGroup btngrp_biosex;
    private javax.swing.JComboBox<String> combox_jobtitle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JLabel lbl_image;
    private javax.swing.JPanel panel_user;
    public static javax.swing.JTable tbl_taskregistry;
    public static javax.swing.JTable tbl_userregistry;
    private javax.swing.JTextField tf_enddate;
    private javax.swing.JTextField tf_firstname;
    private javax.swing.JTextField tf_lastname;
    private javax.swing.JTextField tf_search;
    private javax.swing.JTextField tf_startdate;
    private javax.swing.JTextField tf_userid;
    // End of variables declaration//GEN-END:variables
}
