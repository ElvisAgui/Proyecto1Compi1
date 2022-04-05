
package com.compiladores1.appcliente.salidHtml;

/**
 *
 * @author elvis_agui
 */
public class EtiquetaSimple extends Etiqueta {

    public EtiquetaSimple() {
    }

    public EtiquetaSimple(String contenido, TipoEtiqueta tipo) {
        super(contenido, tipo);
    }

   @Override
    public String toString() {
        return "Etiqueta{" + "contenido=" + contenido + ", tipo=" + tipo + '}';
    }
    
    
}
