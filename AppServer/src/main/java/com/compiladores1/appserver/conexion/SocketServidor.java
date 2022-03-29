package com.compiladores1.appserver.conexion;

import com.compiladores1.files.FileProyects;
import com.compiladores1.appserver.errores.Errors;
import com.compiladores1.appserver.generadorProyect.AnalizadorProyects;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author elvis_agui
 */
public class SocketServidor {

    private Socket actual;
    private ServerSocket servidor = null;
    private Socket oyente = null;
    private DataInputStream input;
    private JTextArea textAreaConsole;
    private AnalizadorProyects analizador;
    private DataOutputStream ouput;

    public void IniciarServer() {
        final int PUERTO = 7000;
        try {
            servidor = new ServerSocket(PUERTO);
            textAreaConsole.append("El Servidor esta en Linea y en espera de Proyectos para analizar!!! :)");
            while (true) {
                oyente = servidor.accept();
                textAreaConsole.append("\n" + "Procesando la peticion del Cliente!");
                actual = oyente;
                input = new DataInputStream(oyente.getInputStream());
                OptionConexion aux = OptionConexion.valueOf(input.readUTF());
                recibirPeticion(aux);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void recibirPeticion(OptionConexion optionPeticion) throws IOException {
        switch (optionPeticion) {
            case PROYECTOS:
                textAreaConsole.append("\n" + "Recibiendo los proyectos, para luego analizarlos");
                recibirProyectos();
                break;
            case PETICION:
                System.out.println("Peticion simple algo esta conectando");
                break;
            default:
                break;

        }
    }

    public void recibirProyectos() {
        ObjectInputStream inputO;
        try {
            inputO = new ObjectInputStream(actual.getInputStream());
            FileProyects archivos = (FileProyects) inputO.readObject();
            imprimir(archivos);
        } catch (Exception ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void imprimir(FileProyects archivos) {
        textAreaConsole.append("\n" + "Procesando Proyectos------(analizando)....!!");
        this.analizador = new AnalizadorProyects();
        this.analizador.analizarProyecto(archivos);
        enviarResultado(this.analizador.getJson(), this.analizador.getErroresGenerales());

    }

    public void enviarResultado(String json, ArrayList<Errors> errores) {
        try {
            DataOutputStream ouput = new DataOutputStream(actual.getOutputStream());
            if (!errores.isEmpty()) {
                textAreaConsole.setText("\n-------------------------------ERRORES ENCONTRADOS EN LOS PROYECTOS----------------------------------------\n");
                textAreaConsole.append("--------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                Errors.imprimirErrores(errores, textAreaConsole);
                ouput.writeUTF(OptionConexion.ERRORES.toString());
                ouput.writeUTF("Errores ");
            } else {
                textAreaConsole.append("\n" + "Analisis Correcto Generando Json!!");
                textAreaConsole.setText("");
                textAreaConsole.append("SERVIDOR EN LINEA!!");
                ouput.writeUTF(OptionConexion.JSON.toString());
                ouput.writeUTF(json);

            }
        } catch (Exception ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    public void setTextArea(JTextArea texta) {
        this.textAreaConsole = texta;
    }
}
