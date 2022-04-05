package com.compiladores1.appcliente.salidHtml;

import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class AccionesSalida {

    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private String contenidoTem = "";
    private EtiquetaTable tem = new EtiquetaTable();

    public AccionesSalida() {

    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getContenidoTem() {
        return contenidoTem;
    }

    public void setContenidoTem(String contenidoTem) {
        this.contenidoTem += contenidoTem;
    }

    public void limpiarContenido() {
        this.contenidoTem = "";
    }

    public void capturaH1() {
        this.etiquetas.add(new EtiquetaSimple(contenidoTem, TipoEtiqueta.H1));
        limpiarContenido();
    }

    public void capturaH2() {
        this.etiquetas.add(new EtiquetaSimple(contenidoTem, TipoEtiqueta.H2));
        limpiarContenido();
    }

    public void capturaBr() {
        this.etiquetas.add(new EtiquetaSimple(contenidoTem, TipoEtiqueta.BR));
        limpiarContenido();
    }

    public void capturarTH() {
        this.tem.getContenidoHijo().add(new EtiquetaSimple(contenidoTem, TipoEtiqueta.TH));
        limpiarContenido();
    }

    public void capturarTD() {
        this.tem.getContenidoHijo().add(new EtiquetaSimple(contenidoTem, TipoEtiqueta.TD));
        limpiarContenido();
    }
    public void capturarTR() {
        this.tem.getContenidoHijo().add(new EtiquetaSimple(contenidoTem, TipoEtiqueta.TR));
        limpiarContenido();
    }
    
    public void caputararTable(){
        this.etiquetas.add(new EtiquetaTable((ArrayList<EtiquetaSimple>) tem.getContenidoHijo().clone(), contenidoTem, TipoEtiqueta.TABLE));
        tem.getContenidoHijo().clear();
        limpiarContenido();
    }
    
    
    public void tostring(){
        for (Etiqueta etiqueta : etiquetas) {
            System.out.println("---------------------");
            if (etiqueta instanceof EtiquetaSimple) {
                EtiquetaSimple etiq = (EtiquetaSimple) etiqueta;
                System.out.println(etiq.toString());
            }else{
                EtiquetaTable aux = (EtiquetaTable) etiqueta;
                for (EtiquetaSimple etiquetaSimple : aux.getContenidoHijo()) {
                    EtiquetaSimple etiq = (EtiquetaSimple) etiquetaSimple;
                    System.out.println(etiq.toString());
                }
            }
        }
    }

}
