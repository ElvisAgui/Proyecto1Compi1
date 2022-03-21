package com.compiladores1.appserver.generadorProyect;

import com.compiladores1.appserver.simbolTable.Clase;
import com.compiladores1.appserver.simbolTable.Metodo;
import com.compiladores1.appserver.simbolTable.TableSimbol;
import com.compiladores1.appserver.simbolTable.Variable;

/**
 *
 * @author elvis_agui
 */
public class GeneradoJson {

    private TableSimbol proyecto;
    private double score;

    public GeneradoJson() {
    }

    public GeneradoJson(TableSimbol proyecto, double Score) {
        this.proyecto = proyecto;
        this.score = Score;
    }

    public String generarJson() {
        proyecto.tosting();
        String json = "{\n";
        json += "\t" + "Score:\"" + score + "\",\n\t";
        json += jsonClases();
        json += jsonVariables();
        json += jsonMetodos();
        json += jsonComentarios();
        return json;
    }

    private String jsonClases() {
        String json = "";
        if (!this.proyecto.getClases().isEmpty()) {
            int ultimPos = this.proyecto.getClases().size() - 1;
            int iterador = 0;
            json += "Clases:[\n\t\t      ";
            for (Clase clase : this.proyecto.getClases()) {
                if (ultimPos == iterador) {
                    json += "{Nombre:\"" + clase.getNombre() + "\"}\n\t       ],\n\t";
                } else {
                    json += "{Nombre:\"" + clase.getNombre() + "\"},\n\t\t      ";
                    iterador++;
                }
            }
        } else {
            json += "Clases:[\n\t\t   \n\t       ],\n\t";
        }
        return json;
    }

    private String jsonVariables() {
        String json = "";
        if (!this.proyecto.getVariables().isEmpty()) {
            int ultimPos = this.proyecto.getVariables().size() - 1;
            int iterador = 0;
            json += "Variables:[\n\t\t      ";
            for (Variable variable : this.proyecto.getVariables()) {
                if (ultimPos == iterador) {
                    json += "{Nombre:\"" + variable.getNombre() + "\",Tipo:\"" + variable.getTipo() + "\",Funcion:\"" + variable.getFuncionPadre() + "\"}\n\t          ],\n\t";
                } else {
                    json += "{Nombre:\"" + variable.getNombre() + "\",Tipo:\"" + variable.getTipo() + "\",Funcion:\"" + variable.getFuncionPadre() + "\"},\n\t\t      ";
                    iterador++;
                }
            }
        } else {
            json += "Variables:[\n\t\t  \n\t          ],\n\t";
        }
        return json;
    }

    private String jsonMetodos() {
        String json = "";
        if (!this.proyecto.getMetodos().isEmpty()) {
            int ultimPos = this.proyecto.getMetodos().size() - 1;
            int iterador = 0;
            json += "Metodos:[\n\t\t      ";
            for (Metodo metodo : this.proyecto.getMetodos()) {
                if (ultimPos == iterador) {
                    json += "{Nombre:\"" + metodo.getNombre() + "\",Tipo:\"" + metodo.getTipo() + "\",Parametros:\"" + metodo.getNumParametros() + "\"}\n\t        ],\n\t";
                } else {
                    json += "{Nombre:\"" + metodo.getNombre() + "\",Tipo:\"" + metodo.getTipo() + "\",Parametros:\"" + metodo.getNumParametros() + "\"},\n\t\t      ";
                    iterador++;
                }
            }
        } else {
            json += "Metodos:[\n\t\t  \n\t        ],\n\t";
        }
        return json;
    }

    private String jsonComentarios() {
        String json = "";
        if (!this.proyecto.getComentarios().isEmpty()) {
            int ultimPos = this.proyecto.getMetodos().size() - 1;
            int iterador = 0;
            json += "Metodos:[\n\t\t      ";
            for (String comentario : this.proyecto.getComentarios()) {
                if (iterador == ultimPos) {
                    json += "{Texto:\"" + comentario + "\"}            ]\n}";
                } else {
                    json += "{Texto:\"" + comentario + "\"},\n\t\t      ";
                    iterador++;
                }
            }
        } else {
            json += "Comentarios:[\n\t\t  \n\t            ]\n}";
        }
        return json;
    }

    public TableSimbol getProyecto() {
        return proyecto;
    }

    public void setProyecto(TableSimbol proyecto) {
        this.proyecto = proyecto;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double Score) {
        this.score = Score;
    }

}
