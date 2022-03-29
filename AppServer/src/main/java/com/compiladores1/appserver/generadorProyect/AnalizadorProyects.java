package com.compiladores1.appserver.generadorProyect;

import com.compiladores1.appserver.analizadores.LexerJava;
import com.compiladores1.appserver.analizadores.parser;
import com.compiladores1.files.FileProyects;
import com.compiladores1.appserver.errores.Errors;
import com.compiladores1.appserver.simbolTable.ManejadorTable;
import com.compiladores1.appserver.simbolTable.TableSimbol;
import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class AnalizadorProyects {

    private ArrayList<Errors> ErrorsProyect1 = new ArrayList<>();
    private TableSimbol tablaProyecto1 = new TableSimbol();
    private ArrayList<Errors> ErrorsProyect2 = new ArrayList<>();
    private TableSimbol tablaProyecto2 = new TableSimbol();
    private ManejadorTable manejador;
    private String json = "";
    private LexerJava lexer;
    private parser parser;

    /**
     * analiza el proyecto 1 luego analiza el proyecto 2 y los compara para las repitencias
     * @param archivos contiene 2 arreglos de files donde estan cada proyecto
     */
    public void analizarProyecto(FileProyects archivos) {
        for (int i = 0; i < archivos.getProyecto1().size(); i++) {
            String texto = abrirArchivo(archivos.getProyecto1().get(i));
            analizarArchivo(texto, ErrorsProyect1, tablaProyecto1, true);

        }
        for (int i = 0; i < archivos.getProyecto2().size(); i++) {
            String texto = abrirArchivo(archivos.getProyecto2().get(i));
            analizarArchivo(texto, ErrorsProyect2, tablaProyecto2, false);

        }
        this.manejador = new ManejadorTable(tablaProyecto1, tablaProyecto2);
        manejador.realizarAcciones();
        json = generarJSON(manejador.getProyecto(), manejador.getScore());

    }
    
    /**
     * crea una instancia del generador del json, y realiza el json
     * @param tabla
     * @param score
     * @return 
     */
    public String generarJSON(TableSimbol tabla, double score) {
        GeneradoJson json = new GeneradoJson(tabla, score);
        String jsonGenerado = json.generarJson();
        return jsonGenerado;
    }

    /**
     * analiza cada archivo .java uno por uno 
     * @param texto el texto del archivo (codigo fuente)
     * @param errors lista de errores donde se almacena cada error encontrado
     * @param tabla tabla del proyecto, para guardar los datos necesarios
     */
    public void analizarArchivo(String texto, ArrayList<Errors> errors, TableSimbol tabla, boolean isProyec1) {
        Reader reader = new StringReader(texto);
        this.lexer = new LexerJava(reader);
        this.lexer.setErrores(errors);
        this.lexer.setTabla(tabla);
        this.lexer.setIsProyecto1(isProyec1);
        this.parser = new parser(lexer);
        try {
            this.parser.setTabla(tabla);
            this.parser.setErrores(errors);
            this.parser.setIsProyecto1(isProyec1);
            this.parser.parse();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }
    
    /**
     * Lee el contenido del archivo que vienen en cada file del arreglo de files
     * @param archivo
     * @return retorna el contenido (codigo fuente)
     */
    public String abrirArchivo(File archivo) {
        String documento = "";
        try {
            FileInputStream entrada = new FileInputStream(archivo);
            int ascc;
            while ((ascc = entrada.read()) != -1) {
                char caracter = (char) ascc;
                documento += caracter;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documento;
    }

    public ArrayList<Errors> getErroresP1() {
        return ErrorsProyect1;
    }

    public TableSimbol getTablaP1() {
        return tablaProyecto1;
    }

    public ArrayList<Errors> getErroresP2() {
        return ErrorsProyect2;
    }

    public TableSimbol getTablaP2() {
        return tablaProyecto2;
    }

    public String getJson() {
        return json;
    }

    public ArrayList<Errors> getErroresGenerales() {
        ArrayList<Errors> erroresG = new ArrayList<>();
        erroresG.addAll(ErrorsProyect1);
        erroresG.addAll(ErrorsProyect2);
        return erroresG;
    }

}
