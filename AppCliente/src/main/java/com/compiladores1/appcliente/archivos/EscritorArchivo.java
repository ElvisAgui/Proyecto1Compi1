package com.compiladores1.appcliente.archivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author elvis_agui
 */
public class EscritorArchivo {
    
    private File fileDirectory;

    public EscritorArchivo() {
    
    }

    /**
     * gurada el json generado por el servidor
     * @param json
     * @param def 
     */
    public void guardarPrimerJson(String json, String def) {
        String path = "";
        JFileChooser choser = new JFileChooser();
        if (choser.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            File carpetaSelec = choser.getSelectedFile();
            escritorIndividual(carpetaSelec, def, ".def");
            escritorIndividual(carpetaSelec, json, ".json");
            path += carpetaSelec + ".def";
            path += "\n" + carpetaSelec + ".json";
            this.fileDirectory = new File(carpetaSelec.getAbsolutePath()+".copy");
            escritorIndividual(carpetaSelec, path, ".copy");
        }
        JOptionPane.showMessageDialog(null, "Archivos guradados con exito");
    }
    
    

    /**
     * escribe el rachivo como tal 
     * @param directorio
     * @param contenido
     * @param extension 
     */
    public void escritorIndividual(File directorio, String contenido, String extension) {
        try {
            FileOutputStream salida = new FileOutputStream(directorio + extension);
            byte[] byts = contenido.getBytes();
            salida.write(byts);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Upss! ocurrio un error en guardar archivo");

        }

    }

    /**
     * 
     * @param directorio
     * @param json
     * @param def 
     */
    public void guardarModificaciones(File directorio, String json, String def) {
        escritorIndividual(directorio, def, ".def");
        escritorIndividual(directorio, json, ".json");
        JOptionPane.showMessageDialog(null, "Archivo Guardado con exito");
    }

    public File getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(File fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    
    
}
