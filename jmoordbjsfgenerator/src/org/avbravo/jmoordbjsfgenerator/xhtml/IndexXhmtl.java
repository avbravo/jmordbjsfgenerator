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
public class IndexXhmtl {

    static FileWriter fw;
    static BufferedWriter bw;
    static PrintWriter salida;

    /*
     * crea el achivo index.xhtml basado en la plantilla y con el enlace al menu
     */
    public static void addIndexFile() {
        try {
            String directorioWebIndex = MySession.getWeb() + MySession.getFileSeparator() + "index.xhtml";
            fw = new FileWriter(directorioWebIndex);
            bw = new BufferedWriter(fw);
            salida = new PrintWriter(bw);
            /*
             * Plantilla
             */
            String lineSep = System.getProperty("line.separator");
            BufferedReader br = new BufferedReader(new FileReader(Directorios.getRutaArchivoPlantilla()));
            String nextLine = "";
            while ((nextLine = br.readLine()) != null) {
                analizarTexto(nextLine, "index");
            }
            salida.close();
            fw.close();
            bw.close();

        } catch (Exception ex) {
            DGlobal.error("AddIndexFile() " + ex.getLocalizedMessage());
        }
    }

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
                    salida.println("<h:body>\n  <ui:composition template=\"./../../" + Directorios.getNombreArchivoPlantilla() + "\">");
                } else {
                    salida.println("<h:body>\n  <ui:composition template=\"./" + Directorios.getNombreArchivoPlantilla() + "\">");
                }
                return;
            }
            if (texto.indexOf("</h:body>") != -1) {
                salida.println("   </ui:composition>\n</h:body>");
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
                salida.println("   <!--\n<ui:define name=\"toformRenderedp\">\ntop\n</ui:define>\n-->");
                return;
            }
            if (texto.indexOf("<div id=\"left\">") != -1) {
                salida.println("   <!--\n<ui:define name=\"left\">\nleft\n</ui:define>\n-->");
                return;
            }
            if (texto.indexOf("<div id=\"content\"") != -1) {
                salida.println("   <ui:define name=\"content\">");

                generarIndex();
                salida.println("   </ui:define>");
                return;
            }

            if (texto.indexOf("<div id=\"right\"") != -1) {
                salida.println("   <!--\n<ui:define name=\"rigth\">\nRight\n</ui:define>\n-->");
                return;
            }

            if (texto.indexOf("<div id=\"bottom\"") != -1) {
                salida.println("   <!--\n<ui:define name=\"bottom\">\nBottom\n</ui:define>\n-->");
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
                salida.println(" </html>");
                return;
            }
            return;
        } catch (Exception ex) {
            DGlobal.error("AnalizarTexto() " + ex.getLocalizedMessage());
        }
        return;
    }

    /*
     * Genera la pagina index
     *
     */
    private static void generarIndex() {
        try {


            /*
             * 
             */
            salida.append("\n<h:form id=\"formtopnologeado\" rendered=\"#{!loginBean.logeado}\">");
            if (MySession.getTipoMensajes().equals("growl")) {
                if (MySession.getGrowSticker()) {
                    salida.append("\n<p:growl id=\"growl\" showDetail=\"true\" sticky=\"true\" /> ");
                } else {
                    salida.append("\n<p:growl id=\"growl\" life=\"" + MySession.getGrowlsSeconds() + "\" /> ");
                }


            } else {
                if (MySession.getTipoMensajes().equals("growlandmessages")) {

                    if (MySession.getGrowSticker()) {
                        salida.append("\n<p:growl id=\"growl\" showDetail=\"true\" sticky=\"true\" /> ");
                    } else {
                        salida.append("\n<p:growl id=\"growl\" life=\"" + MySession.getGrowlsSeconds() + "\" /> ");
                    }
                    salida.println("\n     <h:messages style=\"color: red; text-decoration:overline\"/>");
                } else {
                    salida.println("\n     <h:messages style=\"color: red; text-decoration:overline\"/>");
                }
            }
            salida.append("\n     <p:dialog visible=\"true\" header=\"#{mensajes['aplicacion.titulo']}\" widgetVar=\"dlg3\" showEffect=\"bounce\" hideEffect=\"explode\" width=\"400\"");
            salida.append("\n                height=\"200\">  ");


            salida.append("\n     <h:panelGrid columns=\"2\">");

            salida.println("\n         <f:facet name=\"header\">");
            salida.println("\n           <h:outputLabel  value=\"#{" + MySession.getVar() + "['boton.login']}\"/>");
            salida.println("\n        </f:facet>");
            salida.append("\n        <h:outputText value=\"#{mensajes['login.username']}\"></h:outputText>");
            salida.append("\n        <h:inputText value=\"#{loginBean.username}\"  required=\"true\" />");
            salida.append("\n        <h:outputText value=\"#{mensajes['login.password']}\"  ></h:outputText>");
            salida.append("\n        <h:inputSecret value=\"#{loginBean.password}\"  required=\"true\" />");

            salida.println("\n         <f:facet name=\"footer\">");
            salida.append("\n        <p:commandButton action=\"#{loginBean.verificarLogin}\" value=\"#{mensajes['boton.login']}\" ajax = \"false\" />");
            salida.println("\n        </f:facet>");
            salida.append("\n   </h:panelGrid>");
 salida.append("\n   </p:dialog>");
            salida.append("\n");

            salida.append("\n</h:form>\n");
        } catch (Exception ex) {
            DGlobal.error("GenerarIndex() " + ex.getLocalizedMessage());
        }
    }
}