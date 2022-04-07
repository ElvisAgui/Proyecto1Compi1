package com.compiladores1.appcliente.tableSimbol;

import com.compiladores1.appcliente.analizadores.Token;
import com.compiladores1.appcliente.erros.Errors;
import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class TableSimbolHtml {

    private ArrayList<VariablesHtml> variables = new ArrayList<>();
    private boolean reporError = true;
    private VariablesHtml var = null;

    public TableSimbolHtml() {
    }

    public TableSimbolHtml(ArrayList<VariablesHtml> variables) {
        this.variables = variables;
    }

    public boolean isReporError() {
        return reporError;
    }

    public void setReporError(boolean reporError) {
        this.reporError = reporError;
    }

    /**
     * captura el nombre de la variable sin asiganarle el contenido
     * @param tipo
     * @param token
     * @param errores 
     */
    public void capturarVarible(String tipo, Token token, ArrayList<Errors> errores) {
        if (tipo != null && token != null) {
            if (!buscarVarible(token.getLexeme())) {
                this.variables.add(new VariablesHtml(tipo, token.getLexeme()));
            } else {
                String descripcion = "Variable Repetida - "+token.getLexeme();
                errores.add(new Errors(token.getLine(), token.getColumn() + 1, descripcion, "Semantico", true));
            }
        }
    }

    /**
     * captura el contenido de las variables
     * @param contenido 
     */
    public void capturarContenido(String contenido) {
        if (contenido != null && !variables.isEmpty()) {
            variables.get(variables.size() - 1).setContenido(contenido);
        }

    }

    /**
     * captura el contendo de la variables ya declaradas
     * @param contenido
     * @param isString 
     * @param errores 
     */
    public void capturarConteniYadeclarad(String contenido, boolean isString,ArrayList<Errors> errores) {
        if (contenido != null && var != null) {
            if (isString) {
                try {
                    int num = Integer.parseInt(contenido);
                    if (reporError) {
                        String descripcion = "Error en asignar un valor entero a una varible String";
                        errores.add(new Errors(0, 0, descripcion, "Semantico", true));
                    }
                    var.setContenido(contenido);
                    var = null;
                } catch (NumberFormatException e) {
                    var.setContenido(contenido);
                    var = null;
                }
            } else {
                try {
                    int num = Integer.parseInt(contenido);
                    var.setContenido(contenido);
                    var = null;
                } catch (NumberFormatException e) {
                    var.setContenido(contenido);
                    var = null;
                    if (reporError) {
                       String descripcion = "Error en asignar un valor Cadena a una varible Integer";
                        errores.add(new Errors(0, 0, descripcion, "Semantico", true));
                    }
                }
            }
        }
    }
    
    /**
     * busca la variable con el identificador
     * @param identificador
     * @return 
     */
    public boolean buscarVarible(String identificador) {
        boolean encontrada = false;
        for (VariablesHtml variable : variables) {
            if (variable.getIdentificado().equalsIgnoreCase(identificador)) {
                encontrada = true;
                break;
            }
        }

        return encontrada;
    }

    /**
     * funcion encaragad de obtener el contenido de una varible verificando si es string o no 
     * @param identificador
     * @param stringObligado
     * @param errores
     * @return 
     */
    public String contenidoVariableString(Token identificador, boolean stringObligado,ArrayList<Errors> errores) {
        String contenido = "0";
        boolean encontrado = false;
        if (identificador != null) {
            if (stringObligado) {
                for (VariablesHtml variable : variables) {
                    if (variable.getIdentificado().equalsIgnoreCase(identificador.getLexeme())) {
                        if (variable.getContenido() != null) {
                            if (variable.getTipo().equalsIgnoreCase("String")) {
                                contenido = variable.getContenido();
                                stringObligado = false;
                            }
                        } else {
                            String descripcion = "Variable No inicalizada - "+identificador.getLexeme();
                            errores.add(new Errors(identificador.getLine(), identificador.getColumn() + 1, descripcion, "Semantico", true));
                        }
                        encontrado = true;
                        break;
                    }
                }
            } else {
                for (VariablesHtml variable : variables) {
                    if (variable.getIdentificado().equalsIgnoreCase(identificador.getLexeme())) {
                        if (variable.getContenido() != null) {
                            contenido = variable.getContenido();
                        } else {
                            String descripcion = "Variable No inicalizada - "+identificador.getLexeme();
                            errores.add(new Errors(identificador.getLine(), identificador.getColumn() + 1, descripcion, "Semantico", true));
                        }
                        encontrado = true;
                        stringObligado = false;
                        break;
                    }
                }
            }

            if (!encontrado) {
                //error variable no existente
                String descripcion = "Variable no encontrada - "+identificador.getLexeme();
                errores.add(new Errors(identificador.getLine(), identificador.getColumn() + 1, descripcion, "Semantico", true));
                stringObligado = false;
            }
            if (stringObligado) {
                String descripcion = "Variable no es String - "+identificador.getLexeme();
                errores.add(new Errors(identificador.getLine(), identificador.getColumn() + 1, descripcion, "Semantico", true));
            }
        }

        return contenido;
    }

    /**
     * 
     * @param ideToken
     * @param errores
     * @return 
     */
    public int contenidNumeroVariable(Token ideToken,ArrayList<Errors> errores) {
        int num = 0;
        boolean encontrado = false;
        if (ideToken != null) {
            for (VariablesHtml variable : variables) {
                if (variable.getIdentificado().equalsIgnoreCase(ideToken.getLexeme())) {
                    if (!variable.getTipo().equalsIgnoreCase("integer")) {
                        String descripcion = "Variable No es Integer - "+ideToken.getLexeme();
                        errores.add(new Errors(ideToken.getLine(), ideToken.getColumn() + 1, descripcion, "Semantico", true));
                    }
                    try {
                        num = Integer.parseInt(variable.getContenido());
                        encontrado = true;
                    } catch (NumberFormatException e) {
                        String descripcion = "Variable No es Integer - "+ideToken.getLexeme();
                        errores.add(new Errors(ideToken.getLine(), ideToken.getColumn() + 1, descripcion, "Semantico", true));
                        encontrado = true;
                        break;
                    }
                    break;
                }
            }
            if (!encontrado) {
                //no encontrada
                String descripcion = "Variable No encontrada - "+ideToken.getLexeme();
                errores.add(new Errors(ideToken.getLine(), ideToken.getColumn() + 1, descripcion, "Semantico", true));
            }
        }
        return num;
    }

    public VariablesHtml varExiste(Token identificador) {
        VariablesHtml existe = null;
        if (identificador != null) {
            for (VariablesHtml variable : variables) {
                if (identificador.getLexeme().equalsIgnoreCase(variable.getIdentificado())) {
                    existe = variable;
                    break;
                }
            }
        }
        return existe;
    }

    public boolean tipoAsignacion(Token idenentificador,ArrayList<Errors> errores) {
        boolean isString = true;
        var = varExiste(idenentificador);
        if (var != null) {
            if (var.getTipo().equalsIgnoreCase("Integer")) {
                isString = false;
            }
        } else {
            //variable no existe
            String descripcion = "Variable No encontrada - "+idenentificador.getLexeme();
            errores.add(new Errors(idenentificador.getLine(), idenentificador.getColumn() + 1, descripcion, "Semantico", true));
            
            this.reporError = false;
        }
        return isString;
    }

    /**
     * suma o concaaten dependiendo de la situacion 
     * @param isString
     * @param primero
     * @param segundo
     * @param operador
     * @param errores
     * @return 
     */
    public String accionSuma(boolean isString, String primero, String segundo, Token operador,ArrayList<Errors> errores) {
        String resutl = "";
        if (isString) {
            try {
                int ss = Integer.parseInt(primero);
                if (reporError) {
                    String descripcion = "Error de asignacion(casteo) - "+primero;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true));                }
            } catch (Exception e) {
            }
            resutl = primero + segundo;
        } else {
            int num1 = 0;
            int num2 = 0;
            try {
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de asignacion (casteo) - "+primero;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
                }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 + num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de asignacion - "+segundo;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
                }
                resutl = "1";
            }

        }
        return resutl;
    }

    
    /**
     * acciones de la resta
     * @param isString
     * @param primero
     * @param segundo
     * @param operador
     * @param errores
     * @return 
     */
    public String accionResta(boolean isString, String primero, String segundo, Token operador,ArrayList<Errors> errores) {
        String resutl = "";
        if (isString) {
            if (reporError) {
                String descripcion = "Error de Sintaxis Resta invalida - "+primero;
                errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
            }
        } else {
            int num1 = 0;
            int num2 = 0;
            try {
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de Sintaxis Resta invalida - "+primero;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
                }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 - num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de Sintaxis Resta invalida - "+segundo;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
                }
                resutl = "1";
            }

        }
        return resutl;
    }

    /**
     * acciones de multiplicar
     * @param isString
     * @param primero
     * @param segundo
     * @param operador
     * @param errores
     * @return 
     */
    public String accionMultiplicar(boolean isString, String primero, String segundo, Token operador,ArrayList<Errors> errores) {
        String resutl = "";
        if (isString) {
            if (reporError) {
                 String descripcion = "Error de Sintaxis Multiplicacion invalida - "+primero;
                 errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
            }
        } else {
            int num1 = 1;
            int num2 = 0;
            try {
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de Sintaxis Multiplicacion invalida - "+primero;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
                }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 * num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de Sintaxis Multiplicacion invalida - "+segundo;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
            }
                resutl = "1";
            }

        }
        return resutl;
    }

    
    
    public String accionDividir(boolean isString, String primero, String segundo, Token operador,ArrayList<Errors> errores) {
        String resutl = "";
        if (isString) {
            if (reporError) {
                String descripcion = "Error de Sintaxis Division invalida - "+primero;
                errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
            }
        } else {
            int num1 = 0;
            int num2 = 1;
            try {
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de Sintaxis Division invalida - "+primero;
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
                 }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 / num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    String descripcion = "Error de Sintaxis Division invalida - "+segundo;
                errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
            }
                resutl = "1";
            } catch (ArithmeticException ex) {
                if (reporError) {
                    String descripcion = "Error de Mancos Dividir entre 0 XD - ";
                    errores.add(new Errors(operador.getLine(), operador.getColumn() + 1, descripcion, "Semantico", true)); 
                }
                resutl = "1";
            }

        }
        return resutl;
    }

}
