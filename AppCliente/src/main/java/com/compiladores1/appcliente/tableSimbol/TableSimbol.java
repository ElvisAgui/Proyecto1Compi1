package com.compiladores1.appcliente.tableSimbol;

import com.compiladores1.appcliente.analizadores.Token;
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
    private double score = 0.0;

    public TableSimbol() {
    }

    public void capturaScore(String score) {
        try {
            this.score = Double.parseDouble(score);
        } catch (NumberFormatException e) {
            System.out.println("ERROR EN PARSEO DEL SCORE");
            e.printStackTrace();
        }
    }

    public void caputraClase(String nombre) {
        if (nombre != null) {
            this.clases.add(new Clase(nombre));
        }
    }

    public void capturaVar(String nombre, String tipo, String funcionP) {
        this.variables.add(new Variable(nombre, tipo, funcionP));
    }

    public void capturarMetodo(String nombre, String tipo, String parametros) {
        try {
            int numPar = Integer.parseInt(parametros);
            this.metodos.add(new Metodo(nombre, tipo, numPar));
        } catch (NumberFormatException e) {
            this.metodos.add(new Metodo(nombre, tipo, 0));
        }
    }

    public String recuperarNombreClass(int index, Token token) {
        String nomClase = "";
        if (index < clases.size()) {
            nomClase = clases.get(index).getNombre();
        } else {
            System.out.println("Null pinter Exception xd fila: " + token.getLine() + 1);
        }
        return nomClase;
    }

    public String recuperacionNomVar(int index, Token token) {
        String nomVar = "";
        if (index < variables.size()) {
            nomVar = variables.get(index).getNombre();
        } else {
            System.out.println("Null pinter Exception xd columna: " + token.getColumn() + 1);
        }
        return nomVar;
    }

    public String recuperacionTipoVar(int index, Token token) {
        String nomVar = "";
        if (index < variables.size()) {
            nomVar = variables.get(index).getTipo();
        } else {
            System.out.println("Null pinter Exception xd columna: " + token.getColumn() + 1);
        }
        return nomVar;
    }

    public String recupearacionFuncionPadreVAr(int index, Token token) {
        String funcionPadre = "";
        if (index < variables.size()) {
            funcionPadre = variables.get(index).getFuncionPadre();
        } else {
            System.out.println("null pointer exelption variables funcionpadre");
        }
        return funcionPadre;
    }

    public String recuperacionNombreMetodo(int index, Token token) {
        String nombre = "";
        if (index < metodos.size()) {
            nombre = metodos.get(index).getNombre();
        } else {
            System.out.println("null pointer exception en metodos nombre");
        }
        return nombre;
    }

    public String recuperacionTipoMetodo(int index, Token token) {
        String nombre = "";
        if (index < metodos.size()) {
            nombre = metodos.get(index).getTipo();
        } else {
            System.out.println("null pointer exception en metodos tipo");
        }
        return nombre;
    }

    public String recuperarTextoComentario(int index, Token token) {
        String texto = "";
        if (index < comentarios.size()) {
            texto = comentarios.get(index);
        } else {
            System.out.println("null pointer exception en metodos tipo");
        }
        return texto;
    }

    public int recuperarParametrosMetodos(int index, Token token) {
        int parametros = 0;
        if (index < metodos.size()) {
            parametros = metodos.get(index).getNumParametros();
        } else {
            System.out.println("null pointer exception en metodos parametros");
        }

        return parametros;
    }
    
    
    

    public void capturarComentario(String comentario) {
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void tosting() {
        System.out.println("EL SCORE ES " + score);
        System.out.println("Clases Registaradas");
        clases.forEach(clase -> {
            System.out.println(clase.toString());
        });
        System.out.println("Variables Registradas");
        variables.forEach(variable -> {
            System.out.println(variable.toString());
        });
        System.out.println("Metodos Registrados");
        metodos.forEach(metodo -> {
            System.out.println(metodo.toString());
        });
    }

}
