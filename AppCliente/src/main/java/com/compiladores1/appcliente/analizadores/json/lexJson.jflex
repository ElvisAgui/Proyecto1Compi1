/*primer seccion codigo de usuario*/
//package ;
package com.compiladores1.appcliente.analizadores.json;
import com.compiladores1.appcliente.analizadores.Token;
import java.util.ArrayList;
import java_cup.runtime.*;
import com.compiladores1.appcliente.erros.Errors;

%%
/*segunda seccion configuracion*/
%class LexerJson
%public
%line
%column
%unicode
%cup
%state LITERALES


WhiteSpace = [\r|\n|\r\n|\s\t] | [\t\f]
PUNTO = "."
DOSPUNTO = ":"
COMA = ","
PUNTOCOMA = ";"
ENTERO = [0-9]+
DECIMAL = ([0-9]+[.]([0-9]+))
COMILLAS= "\""
CORCHETEA= "\["
CORCHETEC= "\]"
LLAVEA = "\{"
LLAVEC = "\}"
DIGONALB = "_"
LETRA = [a-zA-Z|ñ]
SCORE="score"
CLASES="clases"
VARIBALES="variables"
METODOS="metodos"
COMENTARIOS="comentarios"
FUNCION="funcion"
TIPO="tipo"
NOMBRE="nombre"
PARAMTETROS="parametros"
TEXTO="texto"
IDD = ((({LETRA}|{DIGONALB})({LETRA}|{ENTERO}|{DIGONALB})*({LETRA}|{ENTERO}))|{LETRA})

/*comodin %{ para agregar codigo java*/
%{
  
    private Symbol symbol(int type, String lexema) {
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
    }

    private ArrayList<Errors> errores = new ArrayList<>();

    private Symbol symbolReservado(String lexema) {
        String aux = lexema.toLowerCase();
        int type;
        switch(aux){
            case "score":
                type = sym.SCORE;
                break;
            case "clases":
                type = sym.CLASES;
                break;
            case "variables":
                type = sym.VARIBALES;
                break;    
            case "metodos":
                type = sym.METODOS;
                break;
            case "comentarios":
                type = sym.COMENTARIOS;
                break;
            case "funcion":
                type = sym.FUNCION;
                break;
            case "tipo":
                type = sym.TIPO;
                break;
            case "nombre":
                type = sym.NOMBRE;
                break;
            case "parametros":
                type = sym.PARAMTETROS;
                break;
            case "texto":
                type = sym.TEXTO;
                break;
            default:
                type = sym.IDD;
                break;
        }
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
    }

    public ArrayList<Errors> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<Errors> errores) {
        this.errores = errores;
    } 
    
%}

%eof{
   System.out.println("LLegue al final desde flex");
%eof}

%%
/* reglas lexicas */
<YYINITIAL> {
{WhiteSpace}                    {/*ignoramos*/}
{DOSPUNTO}                      { return symbol(sym.DOSPUNTO,yytext());}
{COMA}                          { return symbol(sym.COMA,yytext());}
{ENTERO}                        { return symbol(sym.ENTERO,yytext());}
{DECIMAL}                       { return symbol(sym.DECIMAL,yytext());}
{COMILLAS}                      { yybegin(LITERALES); return symbol(sym.COMILLAS,yytext());}
{CORCHETEA}                     { return symbol(sym.CORCHETEA,yytext());}
{CORCHETEC}                     { return symbol(sym.CORCHETEC,yytext());}
{LLAVEA}                        { return symbol(sym.LLAVEA,yytext());}
{LLAVEC}                        { return symbol(sym.LLAVEC,yytext());}
{SCORE}                         { return symbol(sym.SCORE,yytext());}
{CLASES}                        { return symbol(sym.CLASES,yytext());}
{VARIBALES}                     { return symbol(sym.VARIBALES,yytext());}
{METODOS}                       { return symbol(sym.METODOS,yytext());}
{COMENTARIOS}                   { return symbol(sym.COMENTARIOS,yytext());}
{FUNCION}                       { return symbol(sym.FUNCION,yytext());}
{TIPO}                          { return symbol(sym.TIPO,yytext());}
{NOMBRE}                        { return symbol(sym.NOMBRE,yytext());}
{PARAMTETROS}                   { return symbol(sym.PARAMTETROS,yytext());}
{TEXTO}                         { return symbol(sym.TEXTO,yytext());}
{IDD}                           { return symbolReservado(yytext());}
}

<LITERALES>{
{WhiteSpace} 	                {/*ignoramos*/} 
{COMILLAS}                      { yybegin(YYINITIAL); return symbol(sym.COMILLAS, yytext());}
{ENTERO}                        { return symbol(sym.ENTERO,yytext());}
{DECIMAL}                       { return symbol(sym.DECIMAL,yytext());}
{SCORE}                         { return symbol(sym.IDD,yytext());}
{CLASES}                        { return symbol(sym.IDD,yytext());}
{VARIBALES}                     { return symbol(sym.IDD,yytext());}
{COMA}                          { return symbol(sym.COMA,yytext());}
{METODOS}                       { return symbol(sym.IDD,yytext());}
{COMENTARIOS}                   { return symbol(sym.IDD,yytext());}
{FUNCION}                       { return symbol(sym.IDD,yytext());}
{TIPO}                          { return symbol(sym.IDD,yytext());}
{NOMBRE}                        { return symbol(sym.IDD,yytext());}
{PARAMTETROS}                   { return symbol(sym.IDD,yytext());}
{TEXTO}                         { return symbol(sym.IDD,yytext());}
{IDD}                           { return symbol(sym.IDD,yytext());}
[^]                             { return symbol(sym.ALGO,yytext());}
}

/* error fallback */
[^]                             {errores.add(new Errors(yytext(),yyline + 1,yycolumn + 1,"No existe en el lenguaje","Lexico"));}