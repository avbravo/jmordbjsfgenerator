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
public class CssLayout {

    NotifyDescriptor nd;
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter sld;
    FileReader fr = null;
    BufferedReader br = null;
    File filePlantilla;
    String ltexto = "";


    /*
     *
     * ruta_plantilla: corresponde a la ruta del archivo plantilla nombreClase
     * es el que se usara para los beans rutaWeb corresponde a la ruta del
     * archivo xhtml que se desea crear nombre_plantilla es el nombre de la
     * plantilla
     */
    public void CrearArchivoCssLayout(String rutaArchivoCssLayout) {
        try {
            fw = new FileWriter(rutaArchivoCssLayout);
            bw = new BufferedWriter(fw);
            sld = new PrintWriter(bw);
            generarCss();
            sld.close();
            fw.close();
            bw.close();
        } catch (Exception ex) {
            Error("CrearArchivoCssLayout() " + ex.getLocalizedMessage());
        }
    }

    /*
     * Analiza cada linea de la plantilla el parametro generarArchivoXHTML true:
     * indica que son archivos xhtml generados de las clases false: indica que
     * es el archivo de menu final con todos los enlaces
     *
     */
    private void generarCss() {
        try {
            /*
             * encabezado
             */

            sld.println("#top {");
            sld.println("  position: relative;");
            //salida.println("  background-color: #eff1f5;");
            sld.println("  background-color: white;");
            sld.println("  color: white;");
            sld.println("  padding: 5px;");
            sld.println("  margin: 0px 0px 10px 0px;");
            sld.println("}");
            sld.println("");
            sld.println("#bottom {");
            sld.println("  position: relative;");
            sld.println("  background-color: #c2dfef;");
            sld.println("  padding: 5px;");
            sld.println("  margin: 10px 0px 0px 0px;");
            sld.println("}");
            sld.println("");
            sld.println("#left {");
            sld.println("  float: left;");
            //salida.println("  background-color: #ece3a5;");
            sld.println("  background-color: white;");
            sld.println("  padding: 5px;");
            sld.println("  width: 150px;");
            sld.println("}");
            sld.println("");
            sld.println("#right {");
            sld.println("  float: right;");
            sld.println("  background-color: #ece3a5;");
            sld.println("  padding: 5px;");
            sld.println("  width: 150px;");
            sld.println("}");
            sld.println("");
            sld.println(".center_content {");
            sld.println("  position: relative;");
            sld.println("  background-color: #dddddd;");
            sld.println("  padding: 5px;");
            sld.println("}");
            sld.println("");
            sld.println(".left_content {");
            //salida.println("  background-color: #dddddd;");
            sld.println("  background-color: white;");
            sld.println("  padding: 5px;");
            sld.println("  margin-left: 170px;");
            sld.println("}");
            sld.println("");
            sld.println(".right_content {");
            sld.println("  background-color: #dddddd;");
            sld.println("  padding: 5px;");
            sld.println("  margin: 0px 170px 0px 170px;");
            sld.println("}");
            sld.println("");
            //quita las lineas de los paneles
            sld.println(" .ui-panelgrid td, .ui-panelgrid tr { ");
            sld.println("border-style: none !important ");
            sld.println("}");
//            sld.println("#top a:link, #top a:visited {");
//            sld.println("  color: blue;");
//            sld.println("  font-weight : bold;");
//            sld.println("  text-decoration: none;");
//            sld.println("}");
//            sld.println("");
//            sld.println("#top a:link:hover, #top a:visited:hover  {");
//            sld.println("   color: black;");
//            sld.println("   font-weight : bold;");
//            sld.println("   text-decoration : underline;");
//            sld.println("}");

            return;
        } catch (Exception ex) {
            Error("generarCss() " + ex.getLocalizedMessage());
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