
package com.compiladores1.appcliente.erros;

import java.io.Serializable;

/**
 *
 * @author elvis_agui
 */
public class Errors implements Serializable{

    private String lexeman;
    private int fila;
    private int columna;
    private String descripcion;
    private String tipo;

    public Errors(){
        
    }

    public Errors(String lexeman, int fila, int columna, String descripcion, String tipo) {
        this.lexeman = lexeman;
        this.fila = fila;
        this.columna = columna;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

}
