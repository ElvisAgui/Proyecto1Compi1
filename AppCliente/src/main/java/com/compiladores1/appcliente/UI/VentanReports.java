package com.compiladores1.appcliente.UI;

import com.compiladores1.appcliente.analizadores.LexerJson;
import com.compiladores1.appcliente.analizadores.parser;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.JTextArea;

/**
 *
 * @author elvis_agui
 */
public class VentanReports extends javax.swing.JFrame {

    private final NumeroLinea numeroLineaJson;
    private final NumeroLinea numeroLineaDef;
    private parser parse;
    private LexerJson lexerJson;

    public VentanReports() {
        initComponents();
        this.numeroLineaJson = new NumeroLinea(editorjTextArea);
        lineasjScrollPane.setRowHeaderView(numeroLineaJson);
        this.numeroLineaDef = new NumeroLinea(defjTextArea);
        defjScrollPane.setRowHeaderView(this.numeroLineaDef);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jsonjPanel = new javax.swing.JPanel();
        lineasjScrollPane = new javax.swing.JScrollPane();
        editorjTextArea = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        defjScrollPane = new javax.swing.JScrollPane();
        defjTextArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menujMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        anlizarJsonjMenuItem = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REPORTES");
        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 0, 0));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));
        jPanel2.setForeground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Consola");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.setBackground(new java.awt.Color(0, 51, 153));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));

        jsonjPanel.setBackground(new java.awt.Color(0, 204, 204));
        jsonjPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        editorjTextArea.setBackground(new java.awt.Color(255, 255, 255));
        editorjTextArea.setColumns(20);
        editorjTextArea.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        editorjTextArea.setForeground(new java.awt.Color(0, 0, 0));
        editorjTextArea.setRows(5);
        lineasjScrollPane.setViewportView(editorjTextArea);

        javax.swing.GroupLayout jsonjPanelLayout = new javax.swing.GroupLayout(jsonjPanel);
        jsonjPanel.setLayout(jsonjPanelLayout);
        jsonjPanelLayout.setHorizontalGroup(
            jsonjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jsonjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineasjScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1223, Short.MAX_VALUE)
                .addContainerGap())
        );
        jsonjPanelLayout.setVerticalGroup(
            jsonjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jsonjPanelLayout.createSequentialGroup()
                .addComponent(lineasjScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Json", jsonjPanel);

        jPanel6.setBackground(new java.awt.Color(255, 102, 0));
        jPanel6.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel6.setForeground(new java.awt.Color(0, 153, 0));

        defjScrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        defjTextArea.setBackground(new java.awt.Color(255, 255, 255));
        defjTextArea.setColumns(20);
        defjTextArea.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        defjTextArea.setRows(5);
        defjScrollPane.setViewportView(defjTextArea);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(defjScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1225, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(defjScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Def", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jMenuBar1.setForeground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        menujMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menujMenu.setText("FILE");

        jMenuItem1.setText("Abrir Proyecto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menujMenu.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        menujMenu.add(jMenuItem2);

        jMenuBar1.add(menujMenu);

        jMenu2.setText("ANALIZAR");

        anlizarJsonjMenuItem.setText(".json");
        anlizarJsonjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anlizarJsonjMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(anlizarJsonjMenuItem);

        jMenuItem4.setText(".def");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Ver Reporte");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Otros");

        jMenuItem6.setText("Analizar Archivos Java");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void anlizarJsonjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anlizarJsonjMenuItemActionPerformed
        // TODO add your handling code here:
        Reader reader = new StringReader(editorjTextArea.getText());
        this.lexerJson = new LexerJson(reader);
        this.parse = new parser(lexerJson);
        try {
            this.parse.parse();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_anlizarJsonjMenuItemActionPerformed

    /*apartado para los getters y setters*/
    public JTextArea getDefjTextArea() {
        return defjTextArea;
    }

    public void setDefjTextArea(JTextArea defjTextArea) {
        this.defjTextArea = defjTextArea;
    }

    public JTextArea getEditorjTextArea() {
        return editorjTextArea;
    }

    public void setEditorjTextArea(JTextArea editorjTextArea) {
        this.editorjTextArea = editorjTextArea;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem anlizarJsonjMenuItem;
    private javax.swing.JScrollPane defjScrollPane;
    private javax.swing.JTextArea defjTextArea;
    private javax.swing.JTextArea editorjTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jsonjPanel;
    private javax.swing.JScrollPane lineasjScrollPane;
    private javax.swing.JMenu menujMenu;
    // End of variables declaration//GEN-END:variables
}