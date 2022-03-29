
package com.compiladores1.appcliente.tableSimbol;

/**
 *
 * @author elvis_agui
 */
public class Variable {
    private String nombre;
    private String tipo;
    private String funcionPadre = "";
    private boolean yaVerificada = false;

    public Variable() {
    }

    public Variable(String nombre, String tipo, String funcionPadre) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.funcionPadre = funcionPadre;
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

    public String getFuncionPadre() {
        return funcionPadre;
    }

    public void setFuncionPadre(String funcionPadre) {
        this.funcionPadre = funcionPadre;
    }

    public boolean isYaVerificada() {
        return yaVerificada;
    }

    public void setYaVerificada(boolean yaVerificada) {
        this.yaVerificada = yaVerificada;
    }

    @Override
    public String toString() {
        return "Variable{" + "nombre= " + nombre + ", tipo= " + tipo + ", funcionPadre= " + funcionPadre + '}';
    }
    
    
    
}
