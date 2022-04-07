package com.compiladores1.appcliente.conexion;

import com.compiladores1.appcliente.UI.VentanReports;
import com.compiladores1.appcliente.archivos.EscritorArchivo;
import com.compiladores1.files.FileProyects;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author elvis_agui
 */
public class SocketCliente {

    private Socket actual;
    private Boolean error = false;
    
    /**
     * inicaliza la conexion, para hacer peticiones
     */
    public void inicalizarConexion() {
        final String HOST = "localhost";
        final int PUERTO = 7000;
        DataInputStream input;
        DataOutputStream ouput;

        try {
            Socket sc = new Socket(HOST, PUERTO);
            actual = sc;
        } catch (Exception ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * hace la peticion al servidor enviando los files que serviran de analisis
     * @param file1
     * @param file2 
     */
    public void enviarPeticion(ArrayList file1, ArrayList file2) {
        try {
            if (file1 != null && file2 != null) {
                FileProyects listaProyectos = new FileProyects();
                listaProyectos.setProyecto1(file1);
                listaProyectos.setProyecto2(file2);
                this.inicalizarConexion();
                DataOutputStream ouput2 = new DataOutputStream(actual.getOutputStream());
                DataInputStream input = new DataInputStream(actual.getInputStream());
                ouput2.writeUTF(OptionConexion.PROYECTOS.toString());
                ObjectOutputStream oss = new ObjectOutputStream(actual.getOutputStream());
                oss.writeObject(listaProyectos);
                OptionConexion aux = OptionConexion.valueOf(input.readUTF());
                recibirPeticion(aux);
            } else {
                JOptionPane.showMessageDialog(null, "Primero Selecciona los Proyecto a Analizar, Seas mamon xd");
            }

        } catch (Exception ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * recepciona la respuesta del servidor enviada por el servidor 
     * @param opcionRecpcion 
     */
    public void recibirPeticion(OptionConexion opcionRecpcion) {
        switch (opcionRecpcion) {
            case ERRORES:
                recibirErrores();
                break;
            case JSON:
                recibirJson();
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * se captura que hay errores en el archivo de los proyectos
     */
    public void recibirErrores() {
        try {
            DataInputStream input = new DataInputStream(actual.getInputStream());
            String mensaje = input.readUTF();
            JOptionPane.showMessageDialog(null, "OH!, tienes errores de Sintaxis, Verifica tus proyectos y Arreglalos, NOTA: revisa la consola del SERVIDOR");
            error = true;
        } catch (Exception ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * se recibe el json generado por el server
     */
    public void recibirJson() {
        try {
            error = false;
            DataInputStream input = new DataInputStream(actual.getInputStream());
            String mensaje = input.readUTF();
            EscritorArchivo escritor = new EscritorArchivo();
            escritor.guardarPrimerJson(mensaje, "");
            VentanReports ventanaRepor = new VentanReports();
            ventanaRepor.setDirectorio(escritor.getFileDirectory());
            ventanaRepor.getEditorjTextArea().setText(mensaje);
            ventanaRepor.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

}
