
package com.compiladores1.appcliente.tableSimbol;

/**
 *
 * @author elvis_agui
 */
public class VariablesHtml {
    private String tipo;
    private String identificado;
    private String contenido=null;

    public VariablesHtml() {
    }

    public VariablesHtml(String tipo, String identificado, String contenido) {
        this.tipo = tipo;
        this.identificado = identificado;
        this.contenido = contenido;
    }

    public VariablesHtml(String tipo, String identificado) {
        this.tipo = tipo;
        this.identificado = identificado;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdentificado() {
        return identificado;
    }

    public void setIdentificado(String identificado) {
        this.identificado = identificado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
    
    
}
