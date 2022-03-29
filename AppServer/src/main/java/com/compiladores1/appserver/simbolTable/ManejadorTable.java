package com.compiladores1.appserver.simbolTable;

import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class ManejadorTable {

    private TableSimbol proyect1;
    private TableSimbol proyecto2;
    private TableSimbol proyecto = new TableSimbol();
    private double cantidadComentRepit = 0;
    private double cantidadClasesRepit = 0;
    private double cantidadMetodRepit = 0;
    private double cantidadVariableRepit = 0;
    private double Score = 0;

    public ManejadorTable() {
    }

    public ManejadorTable(TableSimbol proyect1, TableSimbol proyecto2) {
        this.proyect1 = proyect1;
        this.proyecto2 = proyecto2;
    }

    public void realizarAcciones() {
        comentarioRepit();
        clasesRepit();
        metdosRepit();
        varRepit();
        calculoScore();
    }

    /**
     * calcula el score total
     */
    private void calculoScore() {
        if (this.proyect1.getComentarios().size() > 0 && this.proyecto2.getComentarios().size() > 0) {
            this.Score += (cantidadComentRepit / (this.proyect1.getComentarios().size() + this.proyecto2.getComentarios().size())) * 0.25;
        }
        if (this.proyect1.getClases().size() > 0 && this.proyecto2.getClases().size() > 0) {
            this.Score += (cantidadClasesRepit / (this.proyect1.getClases().size() + this.proyecto2.getClases().size())) * 0.25;
        }
        if (this.proyect1.getVariables().size() > 0 && this.proyecto2.getVariables().size() > 0) {
            this.Score += (cantidadVariableRepit / (this.proyect1.getVariables().size() + this.proyecto2.getVariables().size())) * 0.25;
        }
        if (this.proyect1.getMetodos().size() > 0 && this.proyecto2.getMetodos().size() > 0) {
            this.Score += (cantidadMetodRepit / (this.proyect1.getMetodos().size() + this.proyecto2.getMetodos().size())) * 0.25;
        }

    }

    /**
     * funcion para verificar los comentarios repetidos del proyecto 1 con el
     * proyecto2
     */
    private void comentarioRepit() {
        if (!proyect1.getComentarios().isEmpty() && !proyecto2.getComentarios().isEmpty()) {
            proyect1.getComentarios().stream().filter(comentario -> (proyecto2.getComentarios().contains(comentario))).forEachOrdered(comentario -> {
                if (!this.proyecto.getComentarios().contains(comentario)) {
                    agregarRepetidos(comentario);
                }
                cantidadComentRepit++;
                this.proyecto.getComentarios().add(comentario);
            });
        }
    }

    /**
     * metodo que aumenta la cantidad de comentarios si aun no esta agregada la
     * repitencia
     *
     * @param coment
     */
    private void agregarRepetidos(String coment) {
        proyecto2.getComentarios().stream().filter(comentario -> (coment.equals(comentario))).forEachOrdered(_item -> {
            cantidadComentRepit++;
        });
    }

    /**
     * funcion principal para la verificacion de clases repetidas del proyecto 1
     * con el proyecto 2
     */
    private void clasesRepit() {
        if (!this.proyect1.getClases().isEmpty() && !this.proyecto2.getClases().isEmpty()) {
            for (Clase clase : this.proyect1.getClases()) {
                comparacionClases(clase);
            }
        }
    }

    /**
     * metodo que compara una unica clase del proyecto 1 con todas las del
     * proyecto 2
     *
     * @param clase
     */
    private void comparacionClases(Clase clase) {
        for (Clase clase1 : this.proyecto2.getClases()) {
            if (clase.getNombre().equals(clase1.getNombre()) && comparacionFuncionesClase(clase, clase1)) {
                if (!classYaAgregada(clase)) {
                    aumentarClasRetip(clase);
                }
                this.cantidadClasesRepit++;
                this.proyecto.getClases().add(clase);
                break;
            }
        }
    }

    /**
     * verfica si la clase ya fue agregada al proyecto de repitencia
     *
     * @param clase
     * @return
     */
    private boolean classYaAgregada(Clase clase) {
        boolean yaAgregada = false;
        for (Clase clase1 : proyecto.getClases()) {
            if (clase.getNombre().equals(clase1.getNombre()) && comparacionFuncionesClase(clase, clase1)) {
                yaAgregada = true;
                break;
            }
        }
        return yaAgregada;
    }

    /**
     * contaviliza cuantas veces se repite la clase en el proyecto2, para
     * calculo de score
     *
     * @param clase
     */
    private void aumentarClasRetip(Clase clase) {
        for (Clase clase1 : proyecto2.getClases()) {
            if (clase.getNombre().equals(clase1.getNombre()) && comparacionFuncionesClase(clase, clase1)) {
                cantidadClasesRepit++;
            }
        }
    }

    /**
     * verifica si las funciones de la clase son iguales
     *
     * @param clase
     * @param clase1
     * @return
     */
    private boolean comparacionFuncionesClase(Clase clase, Clase clase1) {
        boolean iguales = true;
        if (clase.getFunciones().size() == clase1.getFunciones().size()) {
            if (!clase.getFunciones().isEmpty()) {
                ArrayList<String> funciones = (ArrayList) clase1.getFunciones().clone();
                for (String funcione : clase.getFunciones()) {
                    funciones.remove(funcione);
                }
                if (!funciones.isEmpty()) {
                    iguales = false;
                }
            }
        } else {
            iguales = false;
        }

        return iguales;
    }

    /**
     * funcion que verifica los metodo repitidos del proyecto uno con los del
     * proyecto2
     */
    private void metdosRepit() {
        if (!this.proyect1.getMetodos().isEmpty() && !this.proyecto2.getMetodos().isEmpty()) {
            for (int i = 0; i < this.proyect1.getMetodos().size(); i++) {
                Metodo metodo = this.proyect1.getMetodos().get(i);
                comprobarMetodo(metodo);
            }

        }
    }

    /**
     * compara un metod en especifico del proyecto 1 con todo los del proyecto2
     *
     * @param metodo
     */
    private void comprobarMetodo(Metodo metodo) {
        for (int i = 0; i < this.proyecto2.getMetodos().size(); i++) {
            Metodo metodo1 = this.proyecto2.getMetodos().get(i);
            if (metodo.getNombre().equals(metodo1.getNombre()) && comporbacionParametros(metodo, metodo1) && metodo.getTipo().equals(metodo1.getTipo())) {
                if (!metodYaAgregado(metodo)) {
                    aumentarRepitMetodo(metodo);
                }
                cantidadMetodRepit++;
                this.proyecto.getMetodos().add(metodo);
                break;
            }
        }

    }

    /**
     * contaviliza todas ocurrencias del metodo en el proyecto 2
     *
     * @param metodo
     */
    private void aumentarRepitMetodo(Metodo metodo) {
        for (int i = 0; i < this.proyecto2.getMetodos().size(); i++) {
            Metodo metodo1 = this.proyecto2.getMetodos().get(i);
            if (metodo.getNombre().equals(metodo1.getNombre()) && comporbacionParametros(metodo, metodo1) && metodo.getTipo().equals(metodo1.getTipo())) {
                cantidadMetodRepit++;
            }
        }
    }

    /**
     * Verifica si el metodo ya fue tomado encuenta antes para no contar las
     * ocurrencia del segundo proyecto
     *
     * @param metodo
     * @return
     */
    private boolean metodYaAgregado(Metodo metodo) {
        boolean yaAgregado = false;
        for (Metodo metodo1 : proyecto.getMetodos()) {
            if (metodo.getNombre().equals(metodo1.getNombre()) && comporbacionParametros(metodo, metodo1) && metodo.getTipo().equals(metodo1.getTipo())) {
                yaAgregado = true;
                break;
            }
        }
        return yaAgregado;
    }

    /**
     * verifica que todo los parametros de los metodos a comparar sean iguales
     *
     * @param metodo
     * @param metodo1
     * @return
     */
    private boolean comporbacionParametros(Metodo metodo, Metodo metodo1) {
        boolean iguales = true;
        if (metodo.getNumParametros() == metodo1.getNumParametros()) {
            if (metodo.getNumParametros() != 0) {
                noVerrific(metodo1.getVariables());
                for (Variable variable : metodo.getVariables()) {
                    for (Variable variable1 : metodo1.getVariables()) {
                        if (varEsIgual(variable, variable1) && !variable1.isYaVerificada()) {
                            variable1.setYaVerificada(true);
                            variable.setYaVerificada(true);
                            break;
                        }
                    }
                    if (!variable.isYaVerificada()) {
                        iguales = false;
                        break;
                    }
                }

            }
        } else {
            iguales = false;
        }
        return iguales;
    }

    private void noVerrific(ArrayList<Variable> variables) {
        for (Variable variable : variables) {
            variable.setYaVerificada(false);
        }
    }

    /**
     * funcion que compureba la variable repetida, iterando las del proyecto 1
     */
    private void varRepit() {
        if (!this.proyect1.getVariables().isEmpty() && !this.proyecto2.getVariables().isEmpty()) {
            for (Variable variable : proyect1.getVariables()) {
                comprovarVariable(variable);
            }
        }
    }

    /**
     * comprueba una unica variable del proyecto 1 con todas las de proyecto 2
     *
     * @param variable
     */
    private void comprovarVariable(Variable variable) {
        for (Variable variable1 : proyecto2.getVariables()) {
            if (varEsIgual(variable, variable1)) {
                if (!varYaAgreada(variable)) {
                    aumentarVarRepit(variable, true);
                } else {
                    aumentarVarRepit(variable, false);
                }
                cantidadVariableRepit++;
                this.proyecto.getVariables().add(variable);
                break;
            }
        }
    }

    /**
     * aumenta la canidad de repeticiones, y agrega las funciones padre de la
     * varibles del proyecto2
     *
     * @param variable
     * @param aumetarContador
     */
    private void aumentarVarRepit(Variable variable, boolean aumetarContador) {
        for (Variable variable1 : proyecto2.getVariables()) {
            if (varEsIgual(variable, variable1)) {
                if (aumetarContador) {
                    cantidadVariableRepit++;
                }
                variable.funcionPadre(variable1.getFuncionPadre());
            }
        }
    }

    /**
     * compureba si las ocurrencias de la variable ya fueron agregadas con
     * anterioridad
     *
     * @param variable
     * @return
     */
    private boolean varYaAgreada(Variable variable) {
        boolean yaAgregada = false;
        for (Variable variable1 : proyecto.getVariables()) {
            if (varEsIgual(variable, variable1)) {
                yaAgregada = true;
                break;
            }
        }
        return yaAgregada;
    }

    private boolean varEsIgual(Variable variable, Variable variable1) {
        return variable.getNombre().equals(variable1.getNombre()) && variable.getTipo().equals(variable1.getTipo());
    }

    public TableSimbol getProyecto() {
        return proyecto;
    }

    public void setProyecto(TableSimbol proyecto) {
        this.proyecto = proyecto;
    }

    public TableSimbol getProyect1() {
        return proyect1;
    }

    public void setProyect1(TableSimbol proyect1) {
        this.proyect1 = proyect1;
    }

    public TableSimbol getProyecto2() {
        return proyecto2;
    }

    public void setProyecto2(TableSimbol proyecto2) {
        this.proyecto2 = proyecto2;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

}
