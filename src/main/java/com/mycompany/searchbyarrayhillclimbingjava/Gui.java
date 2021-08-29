/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.searchbyarrayhillclimbingjava;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admin
 */
public class Gui extends javax.swing.JFrame {

    ArrayList<Point> pointList = new ArrayList();
    StringBuilder result = new StringBuilder();

    /**
     * Creates new form Gui
     */
    public Gui() {
        initComponents();
        setLocationRelativeTo(null);
        txtResult.setEditable(false);
        btnCheck.setEnabled(false);
        btnSaveChild.setEnabled(false);
        btnSave.setEnabled(false);
        cbDesPoint.setSelectedIndex(-1);
        updateGuiTableSetup();
        updateGuiTableChild();
        jPanel7.setPreferredSize(new Dimension(this.getSize().width * 3 / 7, HEIGHT));
        jPanel1.setPreferredSize(new Dimension(jPanel7.getSize().width * 2 / 5, HEIGHT));
        jPanel10.setPreferredSize(new Dimension(WIDTH, jPanel7.getSize().height * 1 / 10));
        //jPanel2.setPreferredSize(new Dimension(WIDTH, 15));
        jScrollPane4.setPreferredSize(new Dimension(WIDTH, 700));
    }

    private void reset() {
        spnSLPoint.setValue(0);
        pointList.removeAll(pointList);
        DefaultTableModel modelSetup = (DefaultTableModel) tbSetup.getModel();
        modelSetup.setRowCount(0);
        DefaultTableModel modelChild = (DefaultTableModel) tbChild.getModel();
        modelChild.setRowCount(0);
        txtResult.setText("");
        btnCheck.setEnabled(false);
        btnSaveChild.setEnabled(false);
        btnSave.setEnabled(false);
        cbDesPoint.setSelectedIndex(-1);
        result = new StringBuilder();
    }

    private void updateGuiTableSetup() {
        tbSetup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbSetup.setSelectionBackground(new Color(247, 111, 111));
        tbSetup.setSelectionForeground(Color.WHITE);
        tbSetup.setBackground(Color.WHITE);
        tbSetup.setForeground(Color.BLACK);
        tbSetup.getTableHeader().setForeground(Color.BLACK);
        tbSetup.setRowHeight(jPanel7.getHeight() / 15);
        tbSetup.getTableHeader().setFont(new Font("Times New Roman", 1, tbSetup.getRowHeight() / 2));
        tbSetup.setFont(new Font("Times New Roman", 1, tbSetup.getRowHeight() / 2));
        setCellsAlignment(tbSetup, SwingConstants.CENTER);
    }

    private void updateGuiTableChild() {
        tbChild.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tbChild.setSelectionBackground(new Color(247, 111, 111));
        tbChild.setSelectionForeground(Color.WHITE);
        tbChild.setBackground(Color.WHITE);
        tbChild.setForeground(Color.BLACK);
        tbChild.getTableHeader().setForeground(Color.BLACK);
        tbChild.setRowHeight(jPanel1.getHeight() / 15);
        tbChild.getTableHeader().setFont(new Font("Times New Roman", 1, tbChild.getRowHeight() / 2));
        tbChild.setFont(new Font("Times New Roman", 1, tbChild.getRowHeight() / 2));
        tbChild.getColumnModel().getColumn(0).setPreferredWidth(10);
        setCellsAlignment(tbChild, SwingConstants.CENTER);
    }

    private void setCellsAlignment(JTable table, int alignment) {
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    private void HillClimbing(ArrayList<Point> list, String Dich) {
        Point Mo = list.get(0);
        ArrayList<String> Dong = new ArrayList();
        while (Mo != null) {
            Point S = new Point();
            S = Mo;
            Dong.add(S.getName());
            if (S.getName().equals(Dich)) {
                System.out.println(Dong);
                System.out.println("Thanh cong");
                result.append(Dong + "\n" + "ThanhCong");
                txtResult.setText(new String(result));
                return;
            }
            if (S.getChild() != null) {
                ArrayList<Point> L = new ArrayList();
                for (Point m : S.getChild()) {
                    for (int i = 0; i < Dong.size(); i++) {
                        if (m.getName().equals(Dong.get(i))) {
                            break;
                        } else if (m.getName().compareTo(Dong.get(i)) != 0 && i == Dong.size() - 1) {
                            L.add(m);
                        }
                    }
                }
                if (L.size() == 0) {
                    break;
                }
                int min = L.get(0).getValue();;
                int index = 0;
                for (int i = 1; i < L.size(); i++) {
                    if (L.get(i).getValue() < min) {
                        min = L.get(i).getValue();
                        index = i;
                    }
                }
                Point S1 = new Point();
                S1 = L.get(index);
                if (S1.getValue() <= S.getValue()) {
                    Mo = S1;
                } else {
                    Mo = null;
                }
            } else {
                Mo = null;
            }
        }
        System.out.println("Khong thanh cong");
        result.append(Dong + "\n");
        txtResult.setText(result + "\nKhong thanh cong");
    }

    private boolean validateTableSetupEmpty() {
        for (int i = 0; i < tbSetup.getRowCount(); i++) {
            if (String.valueOf(tbSetup.getValueAt(i, 0).toString()).isEmpty() || String.valueOf(tbSetup.getValueAt(i, 1).toString()).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean validateTableSetup() {
        for (int i = 0; i < tbSetup.getRowCount(); i++) {
            if (!checkRegularExpression("^[0-9]+$", String.valueOf(tbSetup.getValueAt(i, 1).toString()))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRegularExpression(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        spnSLPoint = new javax.swing.JSpinner();
        btnShow = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCheck = new javax.swing.JButton();
        btnSaveChild = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSetup = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbDesPoint = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbChild = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResult = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HILL CIMBING ALGORITHM");
        setPreferredSize(new java.awt.Dimension(1437, 683));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS));

        jPanel7.setPreferredSize(new java.awt.Dimension(485, 460));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Enter number Point");
        jPanel9.add(jLabel3);

        spnSLPoint.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        spnSLPoint.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnSLPoint.setPreferredSize(new java.awt.Dimension(100, 37));
        spnSLPoint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spnSLPointKeyPressed(evt);
            }
        });
        jPanel9.add(spnSLPoint);

        btnShow.setBackground(new java.awt.Color(255, 255, 255));
        btnShow.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnShow.setForeground(new java.awt.Color(51, 51, 51));
        btnShow.setText("Show Columns");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });
        jPanel9.add(btnShow);

        jPanel7.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel10.setBackground(new java.awt.Color(0, 204, 204));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        btnReset.setBackground(new java.awt.Color(255, 255, 255));
        btnReset.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnReset.setForeground(new java.awt.Color(51, 51, 51));
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jPanel10.add(btnReset, gridBagConstraints);

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSave.setForeground(new java.awt.Color(51, 51, 51));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jPanel10.add(btnSave, gridBagConstraints);

        btnCheck.setBackground(new java.awt.Color(255, 255, 255));
        btnCheck.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnCheck.setForeground(new java.awt.Color(51, 51, 51));
        btnCheck.setText("SHOW RESULT");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jPanel10.add(btnCheck, gridBagConstraints);

        btnSaveChild.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveChild.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSaveChild.setForeground(new java.awt.Color(51, 51, 51));
        btnSaveChild.setText("SAVE CHILDS");
        btnSaveChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChildActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jPanel10.add(btnSaveChild, gridBagConstraints);

        jPanel7.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        tbSetup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbSetup.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tbSetup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Value", "Childs"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSetup.setGridColor(new java.awt.Color(0, 0, 0));
        tbSetup.setShowGrid(true);
        jScrollPane1.setViewportView(tbSetup);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel7);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setLayout(new java.awt.GridLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choose DesPoint");
        jLabel1.setPreferredSize(new java.awt.Dimension(15, 14));
        jPanel2.add(jLabel1);

        jPanel2.add(cbDesPoint);

        jPanel1.add(jPanel2);

        jScrollPane4.setPreferredSize(new java.awt.Dimension(752, 402));

        tbChild.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbChild.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tbChild.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Child"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChild.setGridColor(new java.awt.Color(0, 0, 0));
        tbChild.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        tbChild.setPreferredSize(new java.awt.Dimension(850, 700));
        tbChild.setShowGrid(true);
        jScrollPane4.setViewportView(tbChild);

        jPanel1.add(jScrollPane4);

        getContentPane().add(jPanel1);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setPreferredSize(new java.awt.Dimension(300, 483));
        jPanel8.setLayout(new java.awt.BorderLayout());

        txtResult.setColumns(20);
        txtResult.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtResult.setLineWrap(true);
        txtResult.setRows(5);
        txtResult.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtResult);

        jPanel8.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel8);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spnSLPointKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnSLPointKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_spnSLPointKeyPressed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        if (false == checkRegularExpression("^[0-9]+$", String.valueOf(spnSLPoint.getValue()))) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đúng định dạng số lượng Point", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            DefaultTableModel model = (DefaultTableModel) tbSetup.getModel();
            model.setRowCount((int) spnSLPoint.getValue());
            btnSave.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Thông báo", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (!validateTableSetupEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validateTableSetup()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập định dạng thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            DefaultTableModel modelChild = (DefaultTableModel) tbChild.getModel();
            modelChild.setRowCount(0);
            pointList.removeAll(pointList);
            for (int i = 0; i < tbSetup.getRowCount(); i++) {
                Point point = new Point(tbSetup.getValueAt(i, 0).toString(), Integer.valueOf(tbSetup.getValueAt(i, 1).toString()));
                pointList.add(point);
            }
            for (int i = 0; i < tbSetup.getRowCount(); i++) {
                Object dataRow[] = new Object[2];
                dataRow[0] = i;
                dataRow[1] = pointList.get(i).getName();
                modelChild.addRow(dataRow);
            }
            DefaultComboBoxModel cb_Model = (DefaultComboBoxModel) cbDesPoint.getModel();
            cb_Model.removeAllElements();
            for (Point point : pointList) {
                cb_Model.addElement(point.getName());
            }
            tbSetup.setRowSelectionInterval(0, 0);
            cbDesPoint.setSelectedIndex(-1);
            btnSaveChild.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Thông báo", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChildActionPerformed
        // TODO add your handling code here:
        try {
            ArrayList<Point> childList = new ArrayList();
            for (int i = 0; i < tbChild.getSelectedRows().length; i++) {
                childList.add(pointList.get(tbChild.getSelectedRows()[i]));
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < childList.size(); i++) {
                sb.append(childList.get(i).getName());
                sb.append(",");
            }
            pointList.get(tbSetup.getSelectedRow()).setChild(childList);
            tbSetup.setValueAt(sb, tbSetup.getSelectedRow(), 2);
            tbChild.clearSelection();
            int index = tbSetup.getSelectedRow();
            if (index < tbSetup.getRowCount() - 1) {
                tbSetup.setRowSelectionInterval(index + 1, index + 1);
            }
            btnCheck.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Thông báo", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnSaveChildActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:
        if (cbDesPoint.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn đích", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            for (Point point : pointList) {
                System.out.println(point);
                result.append(point + "\n");
            }
            HillClimbing(pointList, cbDesPoint.getSelectedItem().toString());
            result = new StringBuilder();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Thông báo", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveChild;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox<String> cbDesPoint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner spnSLPoint;
    private javax.swing.JTable tbChild;
    private javax.swing.JTable tbSetup;
    private javax.swing.JTextArea txtResult;
    // End of variables declaration//GEN-END:variables
}
