package br.reconhecedor.gui;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class Apresentacao extends javax.swing.JFrame {

    public Apresentacao() {
        initComponents();
        textArea.setBorder(new NumberedBorder());
        this.setTitle("Reconhecedor de linguagem regular");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        btn_analisar.setIcon(new ImageIcon(this.getClass().getResource("icon/icon-analisar.png")));
        btn_equipe.setIcon(new ImageIcon(this.getClass().getResource("icon/icon-equipe.png")));
        btn_limpar.setIcon(new ImageIcon(this.getClass().getResource("icon/icon-limpar.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_analisar = new javax.swing.JButton();
        btn_equipe = new javax.swing.JButton();
        btn_limpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(800, 200));

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane3.setViewportView(textArea);

        getContentPane().add(jScrollPane3, java.awt.BorderLayout.PAGE_START);

        jScrollPane4.setPreferredSize(new java.awt.Dimension(800, 200));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linha", "Resultado", "Sequência", "Reconhecimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setMinWidth(50);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(0).setMaxWidth(50);
            tabela.getColumnModel().getColumn(1).setMinWidth(200);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabela.getColumnModel().getColumn(1).setMaxWidth(200);
            tabela.getColumnModel().getColumn(2).setMinWidth(200);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
            tabela.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        getContentPane().add(jScrollPane4, java.awt.BorderLayout.PAGE_END);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 45));
        jPanel1.setLayout(new java.awt.BorderLayout());

        btn_analisar.setText("Analisar");
        btn_analisar.setPreferredSize(new java.awt.Dimension(260, 30));
        jPanel1.add(btn_analisar, java.awt.BorderLayout.LINE_START);

        btn_equipe.setText("Equipe");
        btn_equipe.setPreferredSize(new java.awt.Dimension(260, 32));
        btn_equipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_equipeActionPerformed(evt);
            }
        });
        jPanel1.add(btn_equipe, java.awt.BorderLayout.LINE_END);

        btn_limpar.setText("Limpar");
        btn_limpar.setPreferredSize(new java.awt.Dimension(260, 32));
        btn_limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparActionPerformed(evt);
            }
        });
        jPanel1.add(btn_limpar, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparActionPerformed
        this.textArea.setText("");
        ((DefaultTableModel)this.tabela.getModel()).setRowCount(0);
    }//GEN-LAST:event_btn_limparActionPerformed

    private void btn_equipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_equipeActionPerformed
        new Equipe().setVisible(true);
    }//GEN-LAST:event_btn_equipeActionPerformed

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
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apresentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_analisar;
    private javax.swing.JButton btn_equipe;
    private javax.swing.JButton btn_limpar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tabela;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
