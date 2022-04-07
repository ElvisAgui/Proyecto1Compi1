package com.compiladores1.appcliente.tableSimbol;

import com.compiladores1.appcliente.analizadores.Token;
import com.compiladores1.appcliente.erros.Errors;
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

    /**
     * captura el score y lo combierte a dobuble
     * @param score 
     */
    public void capturaScore(String score) {
        try {
            this.score = Double.parseDouble(score);
        } catch (NumberFormatException e) {
            System.out.println("ERROR EN PARSEO DEL SCORE");
        }
    }

    /**
     * captura clase con su respetivo identificador
     * @param nombre 
     */
    public void caputraClase(String nombre) {
        if (nombre != null) {
            this.clases.add(new Clase(nombre));
        }
    }

    /**
     * captura la vaible con su nombre, funcion padre y tipo lo guarda en la tabla
     * @param nombre
     * @param tipo
     * @param funcionP 
     */
    public void capturaVar(String nombre, String tipo, String funcionP) {
        this.variables.add(new Variable(nombre, tipo, funcionP));
    }

    /**
     * captura los metodo encontrados y verifica que los parametros sena enteros 
     * @param nombre
     * @param tipo
     * @param parametros 
     */
    public void capturarMetodo(String nombre, String tipo, String parametros) {
        try {
            int numPar = Integer.parseInt(parametros);
            this.metodos.add(new Metodo(nombre, tipo, numPar));
        } catch (NumberFormatException e) {
            this.metodos.add(new Metodo(nombre, tipo, 0));
        }
    }

    /**
     * obtiene el nombre del arrglo de clases, valida el error semantico
     * @param index
     * @param token
     * @param errores
     * @return 
     */
    public String recuperarNombreClass(int index, Token token, ArrayList<Errors> errores) {
        String nomClase = "";
        if (index < clases.size()) {
            nomClase = clases.get(index).getNombre();
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global Nombre del arreglo Clase, con indice erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));
        }
        return nomClase;
    }

    /**
     * funcion encargada de obtener el nombre de la vaiable en el indecie n
     * @param index
     * @param token
     * @param errores
     * @return 
     */
    public String recuperacionNomVar(int index, Token token,ArrayList<Errors> errores) {
        String nomVar = "";
        if (index < variables.size()) {
            nomVar = variables.get(index).getNombre();
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global Nombre del arreglo variables, con indice erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));
        }
        return nomVar;
    }

    /**
     * funcion encargada de obtener el tipo de variable en el indice n
     * @param index
     * @param token
     * @param errores
     * @return 
     */
    public String recuperacionTipoVar(int index, Token token,ArrayList<Errors> errores) {
        String nomVar = "";
        if (index < variables.size()) {
            nomVar = variables.get(index).getTipo();
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global tipo del arreglo variable, con indece erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));       
        }
        return nomVar;
    }

    /**
     * 
     * @param index
     * @param token
     * @param errores
     * @return 
     */
    public String recupearacionFuncionPadreVAr(int index, Token token,ArrayList<Errors> errores) {
        String funcionPadre = "";
        if (index < variables.size()) {
            funcionPadre = variables.get(index).getFuncionPadre();
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global funcionPadre del arreglo variable, con indece erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));       
        }
        return funcionPadre;
    }

    public String recuperacionNombreMetodo(int index, Token token,ArrayList<Errors> errores) {
        String nombre = "";
        if (index < metodos.size()) {
            nombre = metodos.get(index).getNombre();
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global Nombre del arreglo Metodos, con indece erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));       
        }
        return nombre;
    }

    public String recuperacionTipoMetodo(int index, Token token,ArrayList<Errors> errores) {
        String nombre = "";
        if (index < metodos.size()) {
            nombre = metodos.get(index).getTipo();
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global Tipo del arreglo Metodos, con indece erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));       
        }
        return nombre;
    }

    public String recuperarTextoComentario(int index, Token token, ArrayList<Errors> errores) {
        String texto = "";
        if (index < comentarios.size()) {
            texto = comentarios.get(index);
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global Texto del arreglo Comentarios, con indece erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));       
        }
        return texto;
    }

    public int recuperarParametrosMetodos(int index, Token token,ArrayList<Errors> errores) {
        int parametros = 0;
        if (index < metodos.size()) {
            parametros = metodos.get(index).getNumParametros();
        } else {
            String descripcion = "Null pinter Exception, intenta acceder a la varible global Parametros del arreglo Metodos, con indece erroneo";
            errores.add(new Errors(token.getLine(), token.getColumn()+1, descripcion, "Semantico", true));       
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
