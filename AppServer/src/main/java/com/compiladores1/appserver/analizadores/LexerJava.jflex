/*primer seccion codigo de usuario*/
//package ;
package com.compiladores1.appserver.analizadores;

import java_cup.runtime.*;

%%
/*segunda seccion configuracion*/
%class LexerJava
%public
%line
%column
%unicode
%cup
%state CADE


WhiteSpace = [\r|\n|\r\n|\s\t] | [\t\f]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
LETRA = [a-zA-Z|ñ]
DECIMAL = ([0-9]+[.]([0-9]+))
ENTERO = [0-9]+
LLAVEA = "\{"
LLAVEC = "\}"
PARENTESISA ="\("
PARENTESISC ="\)"
PUNTO = "."
COMA = ","
PUNTOCOMA = ";"
DOPUNTO = ":"
COMILLAS= "\""
MENOS = "-"
MAS = "+"
ENTERON = ({MENOS}{ENTERO})
DECIMALN = ({MENOS}{DECIMAL})
POR = "*"
DIVISION = "/"
IGUAL="="
NEGATION = "!"
EQUALS=({IGUAL}{IGUAL})
OR="||"
AND="&&"
MENORQ="<"
MAYORQ=">"
MAYOROI=">="
MENOROI="<="
NOTEQUALS="!="
DIGONALB = "_"
IMPORT="import"
PRIVATE = "private"
PUBLIC = "public"
PROTECTED = "protected"
FINAL = "final"
CLASS="class"
INT = "int"
BOOLEAN = "boolean"
STRING = "String"
CHAR = "char"
DOUBLE = "double"
IF = "if"
ELSE = "else"
FOR = "for"
WHILE = "while"
DO = "do"
SWITCH = "switch"
BREAK="break"
RETURN = "return"
NUEVO="new"
DEFAULT = "default"
CASO = "case"
TRUE = "true"
FLASE = "false"
IDD = (({LETRA}|{DIGONALB})({LETRA}|{ENTERO}|{DIGONALB})*)
CARACTER = "'"[^]"'"
//CADENA = ({COMILLAS}{IDD}{COMILLAS})
COMMET = ({TraditionalComment} | {EndOfLineComment} | {DocumentationComment})
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
/*comodin %{ para agregar codigo java*/
%{
  
    private Symbol symbol(int type, String lexema) {
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
    }
    
	String cadena ="";

    
%}

/*accion al finlizar el texto*/
%eof{
   System.out.println("LLegue al final desde flex");
%eof}


%%
/* reglas lexicas */
<YYINITIAL> {
{WhiteSpace} 	{/* ignoramos */}
{CARACTER}                  { return symbol(sym.CARACTER,yytext()); }
{ENTERON}                   { return symbol(sym.ENTERON,yytext());}
{DECIMALN}                  { return symbol(sym.DECIMALN,yytext());}
{DECIMAL}                   { return symbol(sym.DECIMAL,yytext());}
{ENTERO}                    { return symbol(sym.ENTERO,yytext());}
{LLAVEA}                    { return symbol(sym.LLAVEA,yytext());}
{LLAVEC}                    { return symbol(sym.LLAVEC,yytext());}
{PARENTESISA}               { return symbol(sym.PARENTESISA,yytext());}
{PARENTESISC}               { return symbol(sym.PARENTESISC,yytext());}
{PUNTO}                     { return symbol(sym.PUNTO,yytext());}
{COMA}                      { return symbol(sym.COMA,yytext());}
{PUNTOCOMA}                 { return symbol(sym.PUNTOCOMA,yytext());}
{DEFAULT}                   { return symbol(sym.DEFAULT,yytext());}
{DOPUNTO}                   { return symbol(sym.DOPUNTO,yytext());}
{MENOS}                     { return symbol(sym.MENOS,yytext());}
{MAS}                       { return symbol(sym.MAS,yytext());}
{POR}                       { return symbol(sym.POR,yytext());}
{NEGATION}                  { return symbol(sym.NEGATION,yytext());}
{DIVISION}                  { return symbol(sym.DIVISION,yytext());}
{IGUAL}                     { return symbol(sym.IGUAL,yytext());}
{EQUALS}                    { return symbol(sym.EQUALS,yytext());}
{AND}                       { return symbol(sym.AND,yytext());}
{OR}                        { return symbol(sym.OR,yytext());}
{MENORQ}                    { return symbol(sym.MENORQ,yytext());}
{MAYORQ}                    { return symbol(sym.MAYORQ,yytext());}
{MAYOROI}                   { return symbol(sym.MAYOROI,yytext());}
{MENOROI}                   { return symbol(sym.MENOROI,yytext());}
{CASO}                      { return symbol(sym.CASO,yytext());}
{NOTEQUALS}                 { return symbol(sym.NOTEQUALS,yytext());}
{IMPORT}                    { return symbol(sym.IMPORT,yytext());}
{PRIVATE}                   { return symbol(sym.PRIVATE,yytext());}
{PUBLIC}                    { return symbol(sym.PUBLIC,yytext());}
{PROTECTED}                 { return symbol(sym.PROTECTED,yytext());}
{CLASS}                     { return symbol(sym.CLASS,yytext());}
{INT}                       { return symbol(sym.INT,yytext());}
{BOOLEAN}                   { return symbol(sym.BOOLEAN,yytext());}
{TRUE}                      { return symbol(sym.TRUE,yytext());}
//{CADENA}                    { return symbol(sym.CADENA,yytext());}
{STRING}                    { return symbol(sym.STRING,yytext());}
{CHAR}                      { return symbol(sym.CHAR,yytext());}
{CARACTER}                  { return symbol(sym.CARACTER,yytext());}
{DOUBLE}                    { return symbol(sym.DOUBLE,yytext());}
{IF}                        { return symbol(sym.IF,yytext());}
{ELSE}                      { return symbol(sym.ELSE,yytext());}
{FOR}                       { return symbol(sym.FOR,yytext());}
{WHILE}                     { return symbol(sym.WHILE,yytext());}
{DO}                        { return symbol(sym.DO,yytext());}
{SWITCH}                    { return symbol(sym.SWITCH,yytext());}
{BREAK}                     { return symbol(sym.BREAK,yytext());}
{RETURN}                    { return symbol(sym.RETURN,yytext());}
{NUEVO}                     { return symbol(sym.NUEVO,yytext());}
{COMMET}                    { /*return symbol(sym.COMMET,yytext());*/}
{FLASE}                     { return symbol(sym.FALSE,yytext());}
{FINAL}                     { return symbol(sym.FINAL,yytext());}
{IDD}                       { return symbol(sym.IDD,yytext());}
{COMILLAS} {System.out.println("Lex \" "+yytext()); yybegin(CADE);}

}
<CADE>{
{COMILLAS} {System.out.println("STRING :"+cadena); cadena=""; yybegin(YYINITIAL); return symbol(sym.CADENA, cadena);}
[^] {cadena+=yytext();}

}

[^] {System.out.println("error lexico "+ yytext());}

