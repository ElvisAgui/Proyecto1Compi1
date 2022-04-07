package com.compiladores1.appcliente.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author elvis_agui
 */
public class LectorArchivos {

    private String textJson = "";
    private String textDef = "";
    private File fileDirectory;
    private boolean isOk = false;
    
    public LectorArchivos(){
        
    }

    /**
     * encargado de abrir el archivo .copy donde esta la direccion del def y del json
     */
    public void recuperacionCopy() {
        JFileChooser abrir = new JFileChooser();
        FileNameExtensionFilter fi = new FileNameExtensionFilter("copy", "copy");
        abrir.setFileFilter(fi);
        if (abrir.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            File archivo = abrir.getSelectedFile();
            this.fileDirectory = abrir.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("copy")) {
                    leerFichero(archivo);
                    isOk = true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede leer este archivo, verificar que se extendio copy");
                    isOk = false;
                }
            }

        } else {
            //no se realizo ninguan accion 
        }

    }

    /**
     * lee cada fichero encontrado en el copy (json y def)
     * @param archivo 
     */
    public void leerFichero(File archivo) {
        FileReader fr = null;
        try {
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                File nuevo = new File(linea);
                if (nuevo.getName().endsWith("json")) {
                    this.textJson = capturarContenido(nuevo);
                } else if (nuevo.getName().endsWith("def")) {
                    this.textDef = capturarContenido(nuevo);
                }
            }
            fr.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ups!! algo ha ocurrido con la lectura");
            isOk = false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la lectura del fichero verifique sus ficheros");
            isOk = false;
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
            }
        }

    }

    /**
     * captura el contenido de las linea en un string 
     * @param archivo
     * @return 
     */
    public String capturarContenido(File archivo) {
        String docuemento = "";
        try {
            FileInputStream entrada = new FileInputStream(archivo);
            int asc;
            while ((asc = entrada.read()) != -1) {
                char caracter = (char) asc;
                docuemento += caracter;

            }
        } catch (FileNotFoundException ex) {
            isOk = false;
            JOptionPane.showMessageDialog(null, "Ups!! ha ocurrido un error en la lectura de tu archivo");
        } catch (IOException ex) {
            isOk = false;
            JOptionPane.showMessageDialog(null, "Error en la lectura de tu archivo");
        }
        return docuemento;
    }

    public String getJson() {
        return textJson;
    }

    public String getDef() {
        return textDef;
    }

    public File getDirectorio() {
        return fileDirectory;
    }

    public boolean isCorrecto() {
        return isOk;
    }

    public void setCorrecto(boolean correcto) {
        this.isOk = correcto;
    }
}
