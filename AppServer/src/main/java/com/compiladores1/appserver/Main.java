package com.compiladores1.appserver;

import com.compiladores1.appserver.conexion.SocketServidor;
import com.compiladores1.appserver.console.Consola;
import com.compiladores1.appserver.prueba.Ventana;

/**
 *
 * @author elvis_agui
 */
public class Main {

    public static void main(String[] args) {
//        Ventana vtn = new Ventana();
//        vtn.setVisible(true);
        Consola consola = new Consola();
        consola.setVisible(true);
        SocketServidor servidor = new SocketServidor();
        servidor.setTextArea(consola.getConsolaErrorsJTextArea());
        servidor.IniciarServer();
    }

}
