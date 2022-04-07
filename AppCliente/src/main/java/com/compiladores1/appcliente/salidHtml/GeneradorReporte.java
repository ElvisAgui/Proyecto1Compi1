package com.compiladores1.appcliente.salidHtml;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elvis_agui
 */
public class GeneradorReporte {

    private JPanel panel;

    public GeneradorReporte(JPanel panel) {
        this.panel = panel;
    }

    /**
     * hace una llamada a los otros metodos que van agregando elementos al panel
     *
     * @param etiqueas
     */
    public void ouputElementos(ArrayList<Etiqueta> etiqueas) {
        for (Etiqueta etiquea : etiqueas) {
            switch (etiquea.getTipo()) {
                case H1:
                    JLabel etiquetah1 = new JLabel(etiquea.getContenido());
                    etiquetah1.setFont(new Font("Dialog", Font.BOLD, 24));
                    etiquetah1.setForeground(Color.BLACK);
                    etiquetah1.setBounds(60, 150, 100, 75);
                    panel.add(etiquetah1);
                    break;
                case H2:
                    JLabel etiquetah2 = new JLabel(etiquea.getContenido());
                    etiquetah2.setFont(new Font("Dialog", Font.BOLD, 18));
                    etiquetah2.setForeground(Color.BLACK);
                    etiquetah2.setBounds(60, 150, 100, 75);
                    panel.add(etiquetah2);
                    break;
                case BR:
                    JLabel etiquetaBr = new JLabel("         ");
                    etiquetaBr.setBounds(60, 20, 100, 75);
                    panel.add(etiquetaBr);
                    break;
                case TABLE:
                    elemntoTable(etiquea);
                    break;
                default:
                    break;
            }

        }

    }

    /**
     * Muetra los elementos ya se aun th,td,tr iterando por cada elemento encontrado
     * @param Tabla 
     */
    private void elemntoTable(Etiqueta Tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable();
        tabla.setEnabled(false);
        tabla.setBackground(Color.CYAN);
        tabla.setForeground(Color.BLACK);
        tabla.setFont(new Font("Dialog", Font.BOLD, 14));
        tabla.setModel(modelo);
        ArrayList<String> datosFila = new ArrayList<>();
        EtiquetaTable table = (EtiquetaTable) Tabla;
        for (int i = 0; i < table.getContenidoHijo().size(); i++) {
            if (table.getContenidoHijo().get(i).getTipo() == TipoEtiqueta.TH) {
                modelo.addColumn(table.getContenidoHijo().get(i).getContenido());
            }
            if (table.getContenidoHijo().get(i).getTipo() == TipoEtiqueta.TD) {
                datosFila.add(table.getContenidoHijo().get(i).getContenido());
            }
            if (table.getContenidoHijo().get(i).getTipo() == TipoEtiqueta.TR) {
                if (!datosFila.isEmpty()) {
                    String[] datos = new String[datosFila.size()];
                    for (int j = 0; j < datosFila.size(); j++) {
                        datos[j] = datosFila.get(j);
                    }
                    modelo.addRow(datos);
                    datosFila.clear();
                }
            }
        }
        JScrollPane sc = new JScrollPane(tabla);
        panel.add(sc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
