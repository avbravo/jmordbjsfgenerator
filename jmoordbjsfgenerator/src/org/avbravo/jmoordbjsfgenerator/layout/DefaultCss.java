/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.layout;

import java.io.*;
import java.util.HashMap;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author avbravo
 */
public class DefaultCss {

    NotifyDescriptor nd;
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter salida;
    FileReader fr = null;
    BufferedReader br = null;
    File filePlantilla;
    String ltexto = "";
  

    public DefaultCss() {
    }

    public DefaultCss(String rutaArchivoDefault) {
        try {
            fw = new FileWriter(rutaArchivoDefault);
            bw = new BufferedWriter(fw);
            salida = new PrintWriter(bw);
            /*
             * Plantilla
             */
            GenerarDefault();
            salida.close();
            fw.close();
            bw.close();
        } catch (Exception ex) {
            Error("CrearArchivoDefault() " + ex.getLocalizedMessage());
        }
    }

    /*
     *
     * ruta_plantilla: corresponde a la ruta del archivo plantilla nombreClase
     * es el que se usara para los beans rutaWeb corresponde a la ruta del
     * archivo xhtml que se desea crear nombre_plantilla es el nombre de la
     * plantilla
     */
    /*
     * Analiza cada linea de la plantilla el parametro generarArchivoXHTML true:
     * indica que son archivos xhtml generados de las clases false: indica que
     * es el archivo de menu final con todos los enlaces
     *
     */
    private void GenerarDefault() {
        try {
            /*
             * encabezado
             */


            salida.println("body {");
            salida.println("background-color: #ffffff;");
            salida.println("font-size: 12px;");
            salida.println("font-family: Verdana, \"Verdana CE\",  Arial, \"Arial CE\", \"Lucida Grande CE\", lucida, \"Helvetica CE\", sans-serif;");
            salida.println("color: #000000;");
            salida.println("margin: 10px;");
            salida.println("}");
            salida.println("");
            salida.println("h1 {");
            salida.println("font-family: Arial, \"Arial CE\", \"Lucida Grande CE\", lucida, \"Helvetica CE\", sans-serif;");
            salida.println("border-bottom: 1px solid #AFAFAF; ");
            salida.println("font-size:  16px;");
            salida.println("font-weight: bold;");
            salida.println("margin: 0px;");
            salida.println("padding: 0px;");
            salida.println("color: #D20005;");
            salida.println("}");
            salida.println("");
            salida.println("a:link, a:visited {");
            salida.println("color: #045491;");
            salida.println("font-weight : bold;");
            salida.println("text-decoration: none;");
            salida.println("}");
            salida.println("");
            salida.println("a:link:hover, a:visited:hover  {");
            salida.println("color: #045491;");
            salida.println("font-weight : bold;");
            salida.println("text-decoration : underline;");
            salida.println("}");
            salida.println("");
            return;
        } catch (Exception ex) {
            Error("AnalizarTexto() " + ex.getLocalizedMessage());
        }
        return;
    }

    public void Error(String msg) {
        try {
            nd = new NotifyDescriptor.Message(msg, NotifyDescriptor.Message.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        } catch (Exception ex) {
            nd = new NotifyDescriptor.Message("Error() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
    }
}