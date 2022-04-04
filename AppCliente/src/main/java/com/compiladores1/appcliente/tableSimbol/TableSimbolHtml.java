package com.compiladores1.appcliente.tableSimbol;

import com.compiladores1.appcliente.analizadores.Token;
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

    public void capturarVarible(String tipo, String identificador) {
        if (tipo != null && identificador != null) {
            if (!buscarVarible(identificador)) {
                this.variables.add(new VariablesHtml(tipo, identificador));
            } else {
                //marcar error semantico de repeticion
                System.out.println("Error Semantico Varible reptida: " + identificador);
            }
        }
    }

    public void capturarContenido(String contenido) {
        if (contenido != null && !variables.isEmpty()) {
            variables.get(variables.size() - 1).setContenido(contenido);
        }

    }

    public void capturarConteniYadeclarad(String contenido, boolean isString) {
        if (contenido != null && var != null) {
            if (isString) {
                try {
                    int num = Integer.parseInt(contenido);
                    if (reporError) {
                        System.out.println("error en asignar int a un string");
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
                        System.out.println("error en asignar String a un int");
                    }
                }
            }
        }
    }

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

    public String contenidoVariableString(Token identificador, boolean stringObligado) {
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
                            System.out.println("variable no inicaliada");
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
                            System.out.println("variable no inicaliada");
                        }
                        encontrado = true;
                        stringObligado = false;
                        break;
                    }
                }
            }

            if (!encontrado) {
                //error variable no existente
                System.out.println("Variable no encontrada " + identificador.getLexeme());
                stringObligado = false;
            }
            if (stringObligado) {
                System.out.println("Variable no es String " + identificador.getLexeme());
            }
        }

        return contenido;
    }

    public int contenidNumeroVariable(Token ideToken) {
        int num = 0;
        boolean encontrado = false;
        if (ideToken != null) {
            for (VariablesHtml variable : variables) {
                if (variable.getIdentificado().equalsIgnoreCase(ideToken.getLexeme())) {
                    if (!variable.getTipo().equalsIgnoreCase("integer")) {
                        System.out.println("no es integeer");
                    }
                    try {
                        num = Integer.parseInt(variable.getContenido());
                        encontrado = true;
                    } catch (NumberFormatException e) {
                        //error semantico variable no es tipo int
                        System.out.println("varible no es int: " + ideToken.getLexeme());
                        encontrado = true;
                        break;
                    }
                    break;
                }
            }
            if (!encontrado) {
                //no encontrada
                System.out.println("Variable no encontrada " + ideToken.getLexeme());

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

    public boolean tipoAsignacion(Token idenentificador) {
        boolean isString = true;
        var = varExiste(idenentificador);
        if (var != null) {
            if (var.getTipo().equalsIgnoreCase("Integer")) {
                isString = false;
            }
        } else {
            //variable no existe
            System.out.println("variable no existente ");
            this.reporError = false;
        }
        return isString;
    }

    public String accionSuma(boolean isString, String primero, String segundo, Token operador) {
        String resutl = "";
        if (isString) {
            try {
                int ss = Integer.parseInt(primero);
                if (reporError) {
                    System.out.println("erro de sintaxis ");
                }
            } catch (NumberFormatException e) {
            }
            resutl = primero + segundo;
        } else {
            int num1 = 0;
            int num2 = 0;
            try {
                System.out.println(primero+"comprobando numero 1");
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero1 ");
                }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 + num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero2 ");
                }
                resutl = "1";
            }

        }
        return resutl;
    }

    public String accionResta(boolean isString, String primero, String segundo, Token operador) {
        String resutl = "";
        if (isString) {
            if (reporError) {
                System.out.println("error de sintaxis en resta");
            }
        } else {
            int num1 = 0;
            int num2 = 0;
            try {
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero1 ");
                }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 - num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero2 ");
                }
                resutl = "1";
            }

        }
        return resutl;
    }

    public String accionMultiplicar(boolean isString, String primero, String segundo, Token operador) {
        String resutl = "";
        if (isString) {
            if (reporError) {
                System.out.println("error de sintaxis en resta");
            }
        } else {
            int num1 = 1;
            int num2 = 0;
            try {
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero1 ");
                }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 * num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero2 ");
                }
                resutl = "1";
            }

        }
        return resutl;
    }

    public String accionDividir(boolean isString, String primero, String segundo, Token operador) {
        String resutl = "";
        if (isString) {
            if (reporError) {
                System.out.println("error de sintaxis en resta");
            }
        } else {
            int num1 = 0;
            int num2 = 1;
            try {
                num1 = Integer.parseInt(primero);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero1 ");
                }
            }
            try {
                num2 = Integer.parseInt(segundo);
                int rsu = num1 / num2;
                resutl = String.valueOf(rsu);
            } catch (NumberFormatException e) {
                if (reporError) {
                    System.out.println("error sintaxis numero2 ");
                }
                resutl = "1";
            } catch (ArithmeticException ex) {
                if (reporError) {
                    System.out.println("error sintaxis dividir dentro de 0 XD ");
                }
                resutl = "1";
            }

        }
        return resutl;
    }

}
