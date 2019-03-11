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
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class Loginxhtml {

    static FileWriter fw;
    static BufferedWriter bw;
    static PrintWriter salida;
    static FileReader fr = null;
    static BufferedReader br = null;

    /*
     *
     * ruta_plantilla: corresponde a la ruta del archivo plantilla nombreClase
     * es el que se usara para los beans rutaWeb corresponde a la ruta del
     * archivo xhtml que se desea crear nombre_plantilla es el nombre de la
     * plantilla
     */
    /*
     * crea el archivo login.xhtml
     *
     */
    public static void AddLoginxhtmlFile(String rutaArchivoLogin) {
        try {
            fw = new FileWriter(rutaArchivoLogin);
            bw = new BufferedWriter(fw);
            salida = new PrintWriter(bw);
            /*
             * Plantilla
             */
            String lineSep = System.getProperty("line.separator");
            BufferedReader br = new BufferedReader(new FileReader(Directorios.getRutaArchivoPlantilla()));
            String nextLine = "";
            while ((nextLine = br.readLine()) != null) {
                analizarTexto(nextLine, "login");
            }
            salida.close();
            fw.close();
            bw.close();

        } catch (Exception ex) {
            DGlobal.error("AddLoginxhtmlFile() " + ex.getLocalizedMessage());
        }
    }

    /*
     * Analiza cada linea de la plantilla el parametro generarArchivoXHTML true:
     * indica que son archivos xhtml generados de las clases false: indica que
     * es el archivo de menu final con todos los enlaces
     *
     */
    private static void analizarTexto(String texto, String tipo) {
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
                if (!tipo.equals("index")) {
                    salida.println("<h:body>\n<ui:composition template=\"./../../" + Directorios.getNombreArchivoPlantilla() + "\">");
                } else {
                    salida.println("<h:body>\n<ui:composition template=\"./" + Directorios.getNombreArchivoPlantilla() + "\">");
                }
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
                salida.println("<!--\n<ui:define name=\"toformRenderedp\">\ntop\n</ui:define>\n-->");
                return;
            }
            if (texto.indexOf("<div id=\"left\">") != -1) {
                salida.println("<!--\n<ui:define name=\"left\">\nleft\n</ui:define>\n-->");
                return;
            }
            if (texto.indexOf("<div id=\"content\"") != -1) {
                salida.println("<ui:define name=\"content\">");
                /*
                 * Genero el contenido que estara en el content
                 */

                generarLogin();
                salida.println("</ui:define>");
                return;
            }

            if (texto.indexOf("<div id=\"right\"") != -1) {
                salida.println("<!--\n<ui:define name=\"rigth\">\nRight\n</ui:define>\n-->");
                return;
            }

            if (texto.indexOf("<div id=\"bottom\"") != -1) {
                salida.println("<!--\n<ui:define name=\"bottom\">\nBottom\n</ui:define>\n-->");
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
     *
     * GenerarLogin() Genera el contenido para las paginas xhtml
     */
    private static void generarLogin() {
        try {

            salida.println("<h:form rendered=\"#{!loginBean.logeado}\">");
            //AGREGAMOS EL TITULO
            salida.println("  <center><h1>Login</h1></center>");
            salida.println("  <h:panelGrid columns=\"2\">");
            salida.println("     <h:outputText value=\"#{" + MySession.getVar() + "['login.username']}\"  ></h:outputText>");
            salida.println("     <h:inputText value=\"#{loginBean.username}\"  required=\"true\" />");
            salida.println("     <h:outputText value=\"#{" + MySession.getVar() + "['login.password']}\"  ></h:outputText>");
            salida.println("     <h:inputSecret value=\"#{loginBean.password}\"  required=\"true\" />");
            salida.println("     <p:commandButton action=\"#{loginBean.verificarLogin}\" value=\"#{" + MySession.getVar() + "['boton.login']}\"  ajax=\"false\" />");
            salida.println("   </h:panelGrid>");
        
            if (MySession.getTipoMensajes().equals("growl")) {
                if(MySession.getGrowSticker()){
                       salida.append("\n<p:growl id=\"growl\" showDetail=\"true\" sticky=\"true\" /> ");
                    }else{
                       salida.append("\n<p:growl id=\"growl\" life=\"" + MySession.getGrowlsSeconds()+ "\" /> ");
                    }

            } else {
                if (MySession.getTipoMensajes().equals("growlandmessages")) {
                    if(MySession.getGrowSticker()){
                       salida.append("\n<p:growl id=\"growl\" showDetail=\"true\" sticky=\"true\" /> ");
                    }else{
                       salida.append("\n<p:growl id=\"growl\" life=\"" + MySession.getGrowlsSeconds()+ "\" /> ");
                    }
                    salida.println("\n     <h:messages/>");
                }else{                    
                    salida.println("\n     <h:messages/>");
                }
            }
            salida.println("</h:form>");
            salida.println("<h:form rendered=\"#{loginBean.logeado}\">");
            salida.println("   <h:panelGrid columns=\"2\">");
            salida.println("      <h:outputText value=\"#{" + MySession.getVar() + "['login.username']}\"/>");
            salida.println("      <h:outputText value=\"#{loginBean.nombreUsuarioLogeado}\"/>");
            salida.println("      <h:link outcome = \"/index.xhtml\" value=\"#{" + MySession.getVar() + "['boton.regresar']}\" />");
            salida.println("   </h:panelGrid>");
            salida.println("</h:form>");
        } catch (Exception ex) {
            DGlobal.error("generarLogin() " + ex.getLocalizedMessage());
        }
    }
}