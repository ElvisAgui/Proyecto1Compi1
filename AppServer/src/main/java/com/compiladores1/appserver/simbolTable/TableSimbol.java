package com.compiladores1.appserver.simbolTable;

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

    public TableSimbol() {
    }

    /**
     * captura el nombre de la clase analizando
     * @param nombre 
     */
    public void capturarClase(String nombre) {
        if (!"".equals(nombre)) {
            
           this.clases.add(new Clase(nombre));
          
        }else{
            this.clases.add(new Clase("algo"));
        }
        
    }
    
    /**
     * captura el metodo con parametros
     * @param variables
     * @param nombre
     * @param tipo 
     */
    public void capturarMetodo(ArrayList<Variable> variables, String nombre, String tipo) {
        if (!"".equals(nombre) && !"".equals(tipo) && !variables.isEmpty()) {
            ArrayList<Variable> parametros = new ArrayList<>();
            for (Variable variable : variables) {
                parametros.add(variable);
            }
            this.metodos.add(new Metodo(nombre, tipo, parametros.size(), parametros));
            capturaVariable(variables, "Funcion " +nombre);
            if (!this.clases.isEmpty()) {
                this.clases.get(this.clases.size() - 1).getFunciones().add(nombre);
            }
        }
    }

    /**
     * captura el metodo sin parametros
     * @param nombre
     * @param tipo
     */
    public void capturarMetodo(String nombre, String tipo) {
        if (!"".equals(nombre) && !"".equals(tipo)) {
            this.metodos.add(new Metodo(nombre, tipo));
            if (!this.clases.isEmpty()) {
                this.clases.get(this.clases.size() - 1).getFunciones().add(nombre);
            }
        }

    }
    
    /**
     * captura las variabels definidas en los parametros del metodo y los asigna al arreglo global de variables
     * @param variables
     * @param nombre 
     */
    private void capturaVariable(ArrayList<Variable> variables, String nombre) {
        variables.forEach(variable -> {
            variable.setFuncionPadre(nombre);
            this.variables.add(new Variable(variable.getNombre(), variable.getTipo(), variable.getFuncionPadre()));
        });
    }
    
    /**
     * captura la varible al arreglo de variables
     * @param var
     * @param tipo 
     */
    public void capturarVariableIndividual(String var, String tipo) {
        if (!"".equals(var) && !"".equals(tipo)) {
            this.variables.add(new Variable(var, tipo));
        }
    }
    
    /**
     * asigna la funcion padre a la variable o varibles ingresadas, de tipo comun
     * @param esGlobal
     * @param numAactulizar 
     */
    public void actualizarVar(boolean esGlobal, int numAactulizar) {
        if (esGlobal && !this.variables.isEmpty() && !this.clases.isEmpty()) {
            int index = this.variables.size() - numAactulizar;
            while (this.variables.size() > index) {
                this.variables.get(index).setFuncionPadre("Clase "+this.clases.get(this.clases.size() - 1).getNombre());
                index++;
            }
        } else if (!esGlobal && !this.variables.isEmpty() && !this.metodos.isEmpty()) {
            int index = this.variables.size() - numAactulizar;
            while (this.variables.size() > index) {
                this.variables.get(index).setFuncionPadre("Funcion "+this.metodos.get(this.metodos.size() - 1).getNombre());
                index++;
            }
        }
    }

    /**
     * le asigna la funcion padre a la variable o varibles ingresadas asi tambien si son de tipo objeto
     * @param esGlobal
     * @param numAactulizar
     * @param tipo 
     */
    public void actualizarVar(boolean esGlobal, int numAactulizar, String tipo) {
        if (esGlobal && !this.variables.isEmpty() && !this.clases.isEmpty() && tipo != null) {
            int index = this.variables.size() - numAactulizar;
            while (this.variables.size() > index) {
                this.variables.get(index).setTipo(tipo);
                this.variables.get(index).setFuncionPadre("Clase "+this.clases.get(this.clases.size() - 1).getNombre());
                index++;
            }
        } else if (!esGlobal && !this.variables.isEmpty() && !this.metodos.isEmpty() && tipo != null) {
            int index = this.variables.size() - numAactulizar;
            while (this.variables.size() > index) {
                this.variables.get(index).setTipo(tipo);
                this.variables.get(index).setFuncionPadre("Funcion "+this.metodos.get(this.metodos.size() - 1).getNombre());
                index++;
            }
        }
    }

    public void tosting(){
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

}
