package com.compiladores1.appcliente.analizadores;
import java.util.ArrayList;
import com.compiladores1.appcliente.tableSimbol.*;
import java_cup.runtime.*;



%%
/*segunda seccion configuracion*/
%class LexerHtml
%public
%line
%column
%unicode
%cup
%state COMENTARIO

/*delcaracion para los tokens*/
WhiteSpace = [\r|\n|\r\n|\s\t] | [\t\f]
ENTERO = [0-9]+
DECIMAL = ([0-9]+[.]([0-9]+))
LETRA = [a-zA-Z|ñ]
HTMABRE="<html>"
HTMCIERRE="</html>"
H1ABRE="<h1>"
H1CIERRE="</h1>"
H2ABRE="<h2>"
H2CIERRE="</h2>"
TABLAABRE="<table>"
TABLACIERRE="</table>"
TRABRE="<tr>"
TRCIERRE="</tr>"
THABRE="<th>"
THCIERRE="</th>"
TDABRE="<td>"
TDCIERRE="</td>"
SALTO="<br>"
FORABRE="<for"
FORCIERRE="</for>"
PUNTO = "."
DOSPUNTO = ":" 
HASTA="hasta"
MAYOR=">"
MENOR="<"
POSABERTURA = (({MENOR})({LETRA})+({MAYOR})?)
POSCERRADURA = (({MENOR})({DIVISION})({LETRA})+({MAYOR}))
COMA = ","
PUNTOCOMA = ";"
ENTERO = [0-9]+
IDD = ((({LETRA}|{DIGONALB})({LETRA}|{ENTERO}|{DIGONALB})*({LETRA}))|{LETRA})
COMODIN="$$"
PARENTESISA ="\("
PARENTESISC ="\)"
IGUAL="="
MENOS = "-"
MAS = "+"
POR = "*"
DIVISION = "/"
COMILLAS= "\""
CORCHETEA= "\["
CORCHETEC= "\]"
INICIO_COMENTARIO_MULTILINEA = "</"
FIN_COMENTARIO_MULTILINEA = "/>"
INTEGER="integer"
STRING="string"

/*comodin %{ para agregar codigo java*/
%{
  
    private Symbol symbol(int type, String lexema) {
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
    }
    private Symbol symbolReservado(String lexema) {
        String aux = lexema.toLowerCase();
        int type;
        switch(aux){
            case "<html>":
                type = sym.HTMABRE;
                break;
            case "</html>":
                type = sym.HTMCIERRE;
                break;
            case "<h1>":
                type = sym.H1ABRE;
                break;    
            case "</h1>":
                type = sym.H1CIERRE;
                break;
            case "<h2>":
                type = sym.H2CIERRE;
                break;
            case "</h2>":
                type = sym.H2CIERRE;
                break;
            case "<table>":
                type = sym.TABLAABRE;
                break;
            case "</table>":
                type = sym.TABLACIERRE;
                break;
            case "<tr>":
                type = sym.TRABRE;
                break;
            case "</tr>":
                type = sym.TRCIERRE;
                break;
            case "<th>":
                type = sym.THABRE;
                break;
            case "</th>":
                type = sym.THCIERRE;
                break;
            case "<td>":
                type = sym.TDABRE;
                break;
            case "</td>":
                type = sym.TDCIERRE;
                break;
            case "<br>":
                type = sym.SALTO;
                break;
            case "<for":
                type = sym.FORABRE;
                break;
            case "</for>":
                type = sym.FORCIERRE;
                break;
            case "<br>":
                type = sym.SALTO;
                break;
            case "<for":
                type = sym.FORABRE;
                break;
            case "integer":
                type = sym.INTEGER;
                break;
            case "string":
                type = sym.STRING;
                break;
            case "result":
                type = sym.RESULT;
                break; 
            case "score":
                type = sym.SCORE;
                break;   
            case "variables":
                type = sym.VARIABLES;
                break;
            case "clases":
                type = sym.CLASES;
                break;
            case "nombre":
                type = sym.NOMBRE;
                break;
            case "tipo":
                type = sym.TIPO;
                break;
            case "funcion":
                type = sym.FUNCION;
                break;
            case "metodos":
                type = sym.METODOS;
                break;
            case "comentarios":
                type = sym.COMENTARIOS;
                break;
            case "texto":
                type = sym.TEXTO;
                break;
            case "parametros":
                type = sym.PARAMTETROS;
                break;
            default:
                type = sym.IDD;
                break;
        }
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
    }
    
%}

%eof{
   System.out.println("LLegue al final desde flex");
%eof}


%%
/* reglas lexicas */
<YYINITIAL> {
{WhiteSpace} 	{/* ignoramos */}
{INICIO_COMENTARIO_MULTILINEA}          {yybegin(COMENTARIO);}
{HTMABRE}                               {return symbol(sym.HTMABRE,yytext());}
{ENTERO}                                {return symbol(sym.ENTERO,yytext());}
{DECIMAL}                               {return symbol(sym.DECIMAL,yytext());}
{HTMCIERRE}                             {return symbol(sym.HTMCIERRE,yytext());}
{H1ABRE}                                {return symbol(sym.H1ABRE,yytext());}
{H1CIERRE}                              {return symbol(sym.H1CIERRE,yytext());}
{H2ABRE}                                {return symbol(sym.H2ABRE,yytext());}
{H2CIERRE}                              {return symbol(sym.H2CIERRE,yytext());}
{TABLAABRE}                             {return symbol(sym.TABLAABRE,yytext());}
{TABLACIERRE}                           {return symbol(sym.TABLACIERRE,yytext());}
{TRABRE}                                {return symbol(sym.TRABRE,yytext());}
{TRCIERRE}                              {return symbol(sym.TRCIERRE,yytext());}
{THABRE}                                {return symbol(sym.THABRE,yytext());}
{THCIERRE}                              {return symbol(sym.THCIERRE,yytext());}
{TDABRE}                                {return symbol(sym.TDABRE,yytext());}
{TDCIERRE}                              {return symbol(sym.TDCIERRE,yytext());}
{SALTO}                                 {return symbol(sym.SALTO,yytext());}
{FORABRE}                               {return symbol(sym.FORABRE,yytext());}
{FORCIERRE}                             {return symbol(sym.FORCIERRE,yytext());}
{PUNTO}                                 {return symbol(sym.PUNTO,yytext());}
{DOSPUNTO}                              {return symbol(sym.DOSPUNTO,yytext());}
{HASTA}                                 {return symbol(sym.HASTA,yytext());}
{MAYOR}                                 {return symbol(sym.MAYOR,yytext());}
{MENOR}                                 {return symbol(sym.MENOR,yytext());}
{POSABERTURA}                           {return symbolReservado(yytext());}
{POSCERRADURA}                          {return symbolReservado(yytext());}
{COMA}                                  {return symbol(sym.COMA,yytext());}
{PUNTOCOMA}                             {return symbol(sym.PUNTOCOMA,yytext());}
{IDD}                                   {return symbolReservado(yytext());}
{COMODIN}                               {return symbol(sym.COMODIN,yytext());}
{PARENTESISA}                           {return symbol(sym.PARENTESISA,yytext());}
{PARENTESISC}                           {return symbol(sym.PARENTESISC,yytext());}
{IGUAL}                                 {return symbol(sym.IGUAL,yytext());}
{MENOS}                                 {return symbol(sym.MENOS,yytext());}
{MAS}                                   {return symbol(sym.MAS,yytext());}
{POR}                                   {return symbol(sym.POR,yytext());}
{DIVISION}                              {return symbol(sym.DIVISION,yytext());}
{COMILLAS}                              {return symbol(sym.COMILLAS,yytext());}
{CORCHETEA}                             {return symbol(sym.CORCHETEA,yytext());}
{CORCHETEC}                             {return symbol(sym.CORCHETEC,yytext());}
{INTEGER}                               {return symbol(sym.INTEGER,yytext());}
{STRING}                                {return symbol(sym.STRING,yytext());}
}



<COMENTARIO>{
{FIN_COMENTARIO_MULTILINEA} {yybegin(YYINITIAL);}
[^] {;}
}


/* error fallback */
[^]                                     {System.out.println("Error simbolo: "+yytext());}