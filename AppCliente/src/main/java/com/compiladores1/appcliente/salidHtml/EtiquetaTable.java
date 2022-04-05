
package com.compiladores1.appcliente.salidHtml;

import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class EtiquetaTable extends Etiqueta{
    
    private ArrayList<EtiquetaSimple>  contenidoHijo = new ArrayList<>();

    public EtiquetaTable(){
        
    }
    
    public EtiquetaTable(ArrayList<EtiquetaSimple> contenidohijo) {
        this.contenidoHijo = contenidohijo;
    }

    public EtiquetaTable(ArrayList<EtiquetaSimple> contenidoHijo, String contenido, TipoEtiqueta tipo) {
        super(contenido, tipo);
        this.contenidoHijo = contenidoHijo;
    }

    public ArrayList<EtiquetaSimple> getContenidoHijo() {
        return contenidoHijo;
    }

    public void setContenidoHijo(ArrayList<EtiquetaSimple> contenidoHijo) {
        this.contenidoHijo = contenidoHijo;
    }

    
    
    
    
    
}
