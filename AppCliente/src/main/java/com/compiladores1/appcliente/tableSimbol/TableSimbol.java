
package com.compiladores1.appcliente.tableSimbol;

import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class TableSimbol {
    
    private ArrayList<Metodo> metodos = new ArrayList<>();
    private ArrayList<Variable> variables = new ArrayList<>();
    private ArrayList<Clase> clases = new ArrayList<>();
    private ArrayList<String> comentarios = new ArrayList<>();
    private double score;
    
    public TableSimbol() {
    }
    
    
    
    public void capturaScore(String score){
        try {
            this.score = Double.parseDouble(score);
        } catch (NumberFormatException e) {
            System.out.println("ERROR EN PARSEO DEL SCORE");
            e.printStackTrace();
        }
    }
    
    
    public void caputraClase(String nombre){
        if (nombre != null) {
             this.clases.add(new Clase(nombre));
        }
    }
    
    public void capturaVar(String nombre, String tipo, String funcionP){
        this.variables.add(new Variable(nombre, tipo, funcionP));
    }
    
    public void capturarMetodo(String nombre, String tipo, String parametros){
        try {
            int numPar= Integer.parseInt(parametros);
             this.metodos.add(new Metodo(nombre, tipo, numPar));
        } catch (NumberFormatException e) {
             this.metodos.add(new Metodo(nombre, tipo, 0));
        }
    }
    
    
    public void capturarComentario(String comentario){
        this.comentarios.add(comentario);
    }
    

    public ArrayList<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(ArrayList<Metodo> metodos) {
        this.metodos = metodos;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public ArrayList<Clase> getClases() {
        return clases;
    }

    public void setClases(ArrayList<Clase> clases) {
        this.clases = clases;
    }

    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }

    
    public void tosting(){
        System.out.println("EL SCORE ES "+ score);
        System.out.println("Clases Registaradas");
        clases.forEach(clase -> {
            System.out.println(clase.toString());
        });
        System.out.println("Variables Registradas");
        variables.forEach(variable -> {
            System.out.println( variable.toString());
        });
        System.out.println("Metodos Registrados");
        metodos.forEach(metodo -> {
            System.out.println(metodo.toString());
        });
    }
    
}
