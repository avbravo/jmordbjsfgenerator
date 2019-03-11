/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.xhtml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.avbravo.jmoordbjsfgenerator.generales.Directorios;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class Menuxhtml {

    static FileWriter fw;
    static BufferedWriter bw;
    static PrintWriter salida;
    static FileReader fr = null;
    static BufferedReader br = null;
    static String menuLinks;

    public static String getMenuLinks() {
        return menuLinks;
    }

    public static void setMenuLinks(String menuLinks) {
        Menuxhtml.menuLinks = menuLinks;
    }

    public static void AddMenuFile(String lmenuLinks, String rutaArchivoMenu) {
        try {
            menuLinks = lmenuLinks;

            fw = new FileWriter(rutaArchivoMenu);
            bw = new BufferedWriter(fw);
            salida = new PrintWriter(bw);
            /*
             * Plantilla
             */
            String lineSep = System.getProperty("line.separator");
            BufferedReader br = new BufferedReader(new FileReader(Directorios.getRutaArchivoPlantilla()));
            String nextLine = "";
            while ((nextLine = br.readLine()) != null) {
                AnalizarTexto(nextLine, "menu");
            }
            salida.close();
            fw.close();
            bw.close();
        } catch (Exception ex) {
            DGlobal.error("AddMenuFile() " + ex.getLocalizedMessage());
        }
    }

    /*
     * Analiza cada linea de la plantilla el parametro generarArchivoXHTML true:
     * indica que son archivos xhtml generados de las clases false: indica que
     * es el archivo de menu final con todos los enlaces
     *
     */
    private static void AnalizarTexto(String texto, String tipo) {
        try {
            /*
             * encabezado
             */
            if (texto.indexOf("<?xml version=") != - 1) {
                salida.println(texto);
                return;
            }
            if (texto.indexOf("<!DOCTYPE html PUBLIC") != - 1) {
                salida.println(texto);
                return;
            }
            if (texto.indexOf("xmlns=") != -1) {
                salida.println(texto);
                return;
            }
            if (texto.indexOf("xmlns:") != -1) {
                salida.println(texto);
                return;
            }
            /*
             * body
             */
          
            if (texto.indexOf("<h:body>") != -1) {

                salida.println("<h:body>\n<ui:composition>");
                return;
            }
            if (texto.indexOf("</h:body>") != -1) {
                salida.println(" </ui:composition>\n</h:body>");
                return;
            }
            /*
             * omito el header
             */
            String[] header = {"<h:head>", "<meta", "<link href=", "<title>", "</h:head>"};
            String[] div;
            for (String h : header) {
                if (texto.indexOf(h) != -1) {
                    return;
                }
            }
            /*
             * convertimos los top, left, content
             */
            if (texto.indexOf("<div id=\"top\"") != - 1) {
//                salida.println("<!--\n<ui:define name=\"toformRenderedp\">\ntop\n</ui:define>\n-->");
                return;
            }
            if (texto.indexOf("<div id=\"left\">") != -1) {
//                salida.println("<!--\n<ui:define name=\"left\">\nleft\n</ui:define>\n-->");
                return;
            }
            if (texto.indexOf("<div id=\"content\"") != -1) {
//                salida.println("<ui:define name=\"content\">");
                /*
                 * Genero el contenido que estara en el content
                 */

                GenerarMenu();
//                salida.println("</ui:define>");
                return;
            }

            if (texto.indexOf("<div id=\"right\"") != -1) {
//                salida.println("<!--\n<ui:define name=\"rigth\">\nRight\n</ui:define>\n-->");
                return;
            }

            if (texto.indexOf("<div id=\"bottom\"") != -1) {
//                salida.println("<!--\n<ui:define name=\"bottom\">\nBottom\n</ui:define>\n-->");
                return;
            }

            if (texto.indexOf("<ui:insert") != - 1) {
                return;
            }
            if (texto.indexOf("<div>") != -1) {
                return;
            }
            if (texto.indexOf("</div>") != -1) {
                return;
            }

            if (texto.indexOf("</html>") != -1) {
                salida.println("</html>");
                return;
            }
            return;
        } catch (Exception ex) {
            DGlobal.error("AnalizarTexto() " + ex.getLocalizedMessage());
        }
        return;
    }


    /*
     * Genera la pagina de menu
     *
     */
    private static void GenerarMenu() {
        try {
//            salida.println("<h:form id=\"menuForm\" rendered=\"#{loginBean.logeado}\">");

            salida.println(menuLinks);
//            salida.println("</h:form>");

        } catch (Exception ex) {
            DGlobal.error("GenerarMenu() " + ex.getLocalizedMessage());
        }
    }
}