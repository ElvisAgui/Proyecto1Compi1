package com.compiladores1.appcliente.tableSimbol;



/**
 *
 * @author elvis_agui
 */
public class Metodo {
    private String nombre;
    private String tipo;
    private int numParametros = 0;

    public Metodo() {
    }

    public Metodo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    
    public Metodo(String nombre, String tipo, int numParametros) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.numParametros = numParametros;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumParametros() {
        return numParametros;
    }

    public void setNumParametros(int numParametros) {
        this.numParametros = numParametros;
    }

    @Override
    public String toString() {
        return "Metodo{" + "nombre=" + nombre + ", tipo=" + tipo + ", numParametros=" + numParametros + '}';
    }

   
    

   

}
