/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.xhtml;

import org.avbravo.jmoordbjsfgenerator.generales.Directorios;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.avbravo.jmoordbjsfgenerator.utilidades.Utilidades;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class MyPrimeFaces {

    /*
     * agrega etiquetas al top
     */
    public static void addTemplateHeader() {
        try {

            /*
             * agrego primefaces
             */
            if (Utilidades.encontrarTextoArchivo(Directorios.getRutaArchivoPlantilla(), "xmlns:h=\"http://java.sun.com/jsf/html\">")) {
                StringBuilder sbhtml = new StringBuilder("");
                if (MySession.getPrimefacesVersion().equals("2.x")) {
                    if (!Utilidades.encontrarTextoArchivo(Directorios.getRutaArchivoPlantilla(), "http://primefaces.prime.com.tr/ui")) {
                        sbhtml.append("xmlns:p=\"http://primefaces.prime.com.tr/ui\"");
                    }

                } else {
                    if (MySession.getPrimefacesVersion().equals("3.x")) {
                        if (!Utilidades.encontrarTextoArchivo(Directorios.getRutaArchivoPlantilla(), "xmlns:p=\"http://primefaces.org/ui\"")) {
                            sbhtml.append("xmlns:p=\"http://primefaces.org/ui\"");
                        }

                    }
                }
                if (!Utilidades.encontrarTextoArchivo(Directorios.getRutaArchivoPlantilla(), "xmlns:f=\"http://java.sun.com/jsf/core\"")) {
                    sbhtml.append("\n    xmlns:f=\"http://java.sun.com/jsf/core\"");
                }
                if (!Utilidades.encontrarTextoArchivo(Directorios.getRutaArchivoPlantilla(), "xmlns:h=\"http://java.sun.com/jsf/html\">")) {
                    sbhtml.append("\n    xmlns:h=\"http://java.sun.com/jsf/html\">");
                }
                sbhtml.append("\n       xmlns:h=\"http://java.sun.com/jsf/html\">");

                Utilidades.actualizaTextoArchivo(Directorios.getRutaArchivoPlantilla(), "xmlns:h=\"http://java.sun.com/jsf/html\">", sbhtml.toString());
            }


        } catch (Exception ex) {
            DGlobal.error("addTemplateHeader() " + ex.getLocalizedMessage());
        }
    }

    /*
     * agrega el menu y otros datos al top de la plantilla
     */
    public static void topPlantilla(String menu) {
        try {

            /*
             * agrego menu primefaces en la plantilla
             */

            if (Utilidades.encontrarTextoArchivo(Directorios.getRutaArchivoPlantilla(), "<ui:insert name=\"top\">Top</ui:insert>")) {
                StringBuilder sb = new StringBuilder("");

                sb.append("\n          <h:form id=\"main\">");
                sb.append("\n              <p:toolbar>");
                sb.append("\n                  <p:toolbarGroup align=\"left\">");
                sb.append("\n                      <p:commandButton action=\"#{loginBean.irInicio}\"  value=\"#{" + MySession.getVar() + "['menu.home']}\" ajax = \"false\" rendered=\"#{loginBean.logeado}\" icon=\"ui-icon-home\"/>");
                sb.append("\n                      <p:separator />");
                sb.append("\n                      <h:outputText value=\"#{" + MySession.getVar() + "['aplicacion.titulo']}\"/>");
                sb.append("\n                      <p:separator />");
                sb.append("\n                  </p:toolbarGroup>");
                sb.append("\n       ");
                sb.append("\n                  <p:toolbarGroup align=\"right\">");
                sb.append("\n                      <h:outputText value=\"#{" + MySession.getVar() + "['login.username']}\" rendered=\"#{loginBean.logeado}\" />");
                sb.append("\n                      <h:outputText value=\"#{loginBean.nombreUsuarioLogeado}\" rendered=\"#{loginBean.logeado}\"/>");
                sb.append("\n                      <p:commandButton actionListener=\"#{loginBean.logout}\" value=\"#{").append(MySession.getVar()).append("['boton.logout']}\" ajax = \"false\" rendered=\"#{loginBean.logeado}\" icon=\"ui-icon-power\"/>");
                sb.append("\n                      <p:menuButton value=\"#{mensajes['msg.tools']}\">");
                sb.append("\n                          <p:menuitem action=\"#{idiomas.englishAction}\" value=\"#{mensajes['idioma.english']}\" immediate = \"true\" ajax = \"false\" icon=\"ui-icon-flag\"/>");
                sb.append("\n                          <p:menuitem action=\"#{idiomas.spanishAction}\" value=\"#{mensajes['idioma.spanish']}\" immediate = \"true\" ajax = \"false\" icon=\"ui-icon-flag\" />");
                sb.append("\n                          <p:menuitem onclick=\"dlgTheme.show();\" value=\"#{mensajes['msg.theme']}\" immediate = \"true\"  />");
                sb.append("\n                      </p:menuButton>");
                sb.append("\n                   </p:toolbarGroup>");
                sb.append("\n");
                sb.append("\n              </p:toolbar>");
                
            /*
             * Aqui es para mostrar el growl en el dialogo.
             */
               if(MySession.getGenerateGrowlnTemplate()){
                 if (MySession.getTipoMensajes().equals("growl") || MySession.getTipoMensajes().equals("growlandmessage")) {
                    if (MySession.getGrowSticker()) {
                        sb.append("\n      <p:growl id=\"growl\" showDetail=\"true\" sticky=\"true\" /> ");
                    } else {
                        sb.append("\n      <p:growl id=\"growl\" life=\"" + MySession.getGrowlsSeconds() + "\" /> ");
                    }

                }  
               }
                
                
                
                
              
                

                //   sb.append("\n          </h:form>");
                /*
                 * dialogo para el login
                 *
                 */
//                sb.append("\n<h:form id=\"formtopnologeado\" rendered=\"#{!loginBean.logeado}\">");
//                sb.append("\n     <p:dialog header=\"#{mensajes['menu.login']}\" widgetVar=\"dlg3\" showEffect=\"bounce\" hideEffect=\"explode\" width=\"400\"");
//                sb.append("\n                height=\"200\">  ");
//                sb.append("\n     <h:panelGrid columns=\"2\">");
//                sb.append("\n");
//                sb.append("\n        <h:outputText value=\"#{mensajes['login.username']}\"></h:outputText>");
//                sb.append("\n        <h:inputText value=\"#{loginBean.username}\"  required=\"true\" />");
//                sb.append("\n        <h:outputText value=\"#{mensajes['login.password']}\"  ></h:outputText>");
//                sb.append("\n        <h:inputSecret value=\"#{loginBean.password}\"  required=\"true\" />");
//
//                sb.append("\n        <p:commandButton action=\"#{loginBean.verificarLogin}\" value=\"#{mensajes['boton.login']}\" ajax = \"false\" />");
//                sb.append("\n   </h:panelGrid>");
//                sb.append("\n  </p:dialog>");
//                sb.append("\n");
//                sb.append("\n</h:form>");
                /*
                 * dialogo para el ltema
                 *
                 */
                //  sb.append("\n<h:form id=\"formtema\" >");
                sb.append("\n              <p:dialog header=\"#{mensajes['msg.theme']}\" widgetVar=\"dlgTheme\" showEffect=\"bounce\" hideEffect=\"explode\" width=\"400\"");
                sb.append("\n                 height=\"200\">  ");
                sb.append("\n                  <h:panelGrid columns=\"2\">");
                sb.append("\n                      <p:selectOneMenu  value=\"#{cambiadorTemas.tema}\" immediate = \"true\"   editable=\"true\">");
                sb.append("\n                          <f:selectItems value=\"#{cambiadorTemas.themes}\" />");
                sb.append("\n                     </p:selectOneMenu> ");
                sb.append("\n                     <p:commandButton value=\"#{mensajes['msg.changetheme']}\" action=\"#{cambiadorTemas.cambiar}\" ajax=\"false\"/>");
                sb.append("\n                  </h:panelGrid>");
                sb.append("\n              </p:dialog>");
                sb.append("\n");
                sb.append("\n          </h:form>");
                sb.append("\n");
                sb.append("\n");
                sb.append("\n         <ui:include src=\"menu.xhtml\"/>");
                Utilidades.actualizaTextoArchivo(Directorios.getRutaArchivoPlantilla(), "<ui:insert name=\"top\">Top</ui:insert>", sb.toString());

            }
        } catch (Exception ex) {
            DGlobal.error("topPlantilla() " + ex.getLocalizedMessage());
        }
    }
    /*
     * agrega menu
     */

    public void addFileMenuPrimeFaces(String menu) {
        try {
            if (Utilidades.encontrarTextoArchivo(Directorios.getRutaArchivoPlantilla(), "<ui:insert name=\"top\">Top</ui:insert>")) {
                Utilidades.actualizaTextoArchivo(Directorios.getRutaArchivoPlantilla(), "<ui:insert name=\"top\">Top</ui:insert>", menu.toString());
            }
        } catch (Exception ex) {
            DGlobal.error("addFileMenuPrimeFaces() " + ex.getLocalizedMessage());
        }
    }
}
