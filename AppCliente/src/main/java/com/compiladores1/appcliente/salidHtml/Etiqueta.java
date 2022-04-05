
package com.compiladores1.appcliente.salidHtml;

/**
 *
 * @author elvis_agui
 */
public class Etiqueta {
    protected String contenido = "";
    protected TipoEtiqueta tipo;

    public Etiqueta() {
    }

    public Etiqueta(String contenido, TipoEtiqueta tipo) {
        this.contenido = contenido;
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public TipoEtiqueta getTipo() {
        return tipo;
    }

    public void setTipo(TipoEtiqueta tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "contenido=" + contenido + ", tipo=" + tipo + '}';
    }
    
    
    
}
