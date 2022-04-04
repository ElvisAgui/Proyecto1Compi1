package com.compiladores1.appserver.errores;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author elvis_agui
 */
public class Errors implements Serializable {

    private String lexeman;
    private int fila;
    private int columna;
    private String descripcion;
    private String tipo;
    private String nomClase;
    private boolean isProyecto1;

    public Errors() {

    }

    public Errors(String lexeman, int fila, int columna, String descripcion, String tipo, String nombreClase, boolean isProyecto1) {
        this.lexeman = lexeman;
        this.fila = fila;
        this.columna = columna;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.nomClase = nombreClase;
        this.isProyecto1 = isProyecto1;
    }

    public static void imprimirErrores(ArrayList<Errors> errores, JTextArea textAreaConsole) {
        boolean titulo1 = false;
        boolean titulo2 = false;
        for (int i = 0; i < errores.size(); i++) {
            if (errores.get(i).isIsProyecto1() && !titulo1) {
                textAreaConsole.append("\n-------------------------------------------------------------------------------ERRORES DEL PROYECTO 1---------------------------------------------------------------------\n");
                titulo1 = true;
            }
            if (!errores.get(i).isIsProyecto1() && !titulo2) {
                textAreaConsole.append("\n-------------------------------------------------------------------------------ERRORES DEL PROYECTO 2----------------------------------------------------------------------\n");
                titulo2 = true;
            }
            System.out.println("Error : " + errores.get(i).getDescripcion());
            textAreaConsole.append("\n" + errores.get(i).descripcionError());
        }
    }

    public String descripcionError() {
        String description = "Error en la clase: " + this.nomClase + " con el  Token: ";
        description += "\""+this.lexeman+ "\"" + "  |---|  LINEA---> " + this.fila + "  |---|  COLUMNA---> " + this.columna + "  |---|  TIPO---> " + this.tipo + "  |---|  DESCRIPCION---> " + this.descripcion;
        return description;
    }

    public String getLexeman() {
        return lexeman;
    }

    public void setLexeman(String lexeman) {
        this.lexeman = lexeman;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomClase() {
        return nomClase;
    }

    public void setNomClase(String nomClase) {
        this.nomClase = nomClase;
    }

    public boolean isIsProyecto1() {
        return isProyecto1;
    }

    public void setIsProyecto1(boolean isProyecto1) {
        this.isProyecto1 = isProyecto1;
    }

}
