/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.xhtml;

import java.io.*;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.openide.NotifyDescriptor;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class TemplateFacelets {

    NotifyDescriptor nd;
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pt;
    FileReader fr = null;
    BufferedReader br = null;
    File filePlantilla;
    String ltexto = "";

    public TemplateFacelets() {
    }

    public TemplateFacelets(String rutaArchivoTemplate) {
        try {
            fw = new FileWriter(rutaArchivoTemplate);
            bw = new BufferedWriter(fw);
            pt = new PrintWriter(bw);
            /*
             * Plantilla
             */
            if (MySession.getFaceletsTemplate().equals("superior_centro")) {
                topCenter();
            } else {
                if (MySession.getFaceletsTemplate().equals("superior_centro_abajo")) {
                    topContenBottom();
                } else {
                    if (MySession.getFaceletsTemplate().equals("izquierda_derecha")) {
                        leftContent();
                    } else {
                        if (MySession.getFaceletsTemplate().equals("izquierda_centro_derecho")) {
                            leftCenterRight();
                        } else {
                            if (MySession.getFaceletsTemplate().equals("superior_izquierda_derecha")) {
                                topLeftRight();
                            } else {
                                if (MySession.getFaceletsTemplate().equals("superior_izquierdo_centro_derecha")) {
                                    topLeftCenterRight();
                                } else {
                                    if (MySession.getFaceletsTemplate().equals("superior_izquierda_derecha_abajo")) {
                                        topLeftRigthBottom();
                                    } else {

                                        if (MySession.getFaceletsTemplate().equals("superior_izquierda_centro_derecha_abajo")) {
                                            topLeftCenterRightBottom();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            pt.close();
            fw.close();
            bw.close();
        } catch (Exception ex) {
            DGlobal.error("templateFacelets() " + ex.getLocalizedMessage());
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
    private void topCenter() {
        try {
            /*
             * encabezado
             */


            pt.println("<?xml version='1.0' encoding='UTF-8' ?> ");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
            escribirHead();
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");
            pt.println("        <div id=\"top\" class=\"top\">");
            pt.println("            <ui:insert name=\"top\">Top</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("        <div id=\"content\" class=\"center_content\">");
            pt.println("            <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");

            return;
        } catch (Exception ex) {
            DGlobal.error("topCenter() " + ex.getLocalizedMessage());
        }
        return;
    }

    private void topContenBottom() {
        try {
            pt.println("<?xml version='1.0' encoding='UTF-8' ?> ");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
            escribirHead();
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");
            pt.println("        <div id=\"top\">");
            pt.println("            <ui:insert name=\"top\">Top</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("        <div id=\"content\" class=\"center_content\">");
            pt.println("            <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("        <div id=\"bottom\">");
            pt.println("            <ui:insert name=\"bottom\">Bottom</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");
        } catch (Exception ex) {
            DGlobal.error("topContenBottom() " + ex.getLocalizedMessage());
        }

    }

    private void leftContent() {
        try {
            pt.println("<?xml version='1.0' encoding='UTF-8' ?> ");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
            escribirHead();
            pt.println("   </h:head>");
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");
            escribirLeft();
            pt.println("        <div id=\"content\" class=\"left_content\">");
            pt.println("            <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");
        } catch (Exception ex) {
            DGlobal.error("leftContent() " + ex.getLocalizedMessage());
        }
    }

    private void leftCenterRight() {
        try {
            pt.println("<?xml version='1.0' encoding='UTF-8' ?>");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
            escribirHead();
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");

            escribirLeft();
            pt.println("        <div>");
            pt.println("            <div id=\"right\">");
            pt.println("                <ui:insert name=\"right\">Right</ui:insert>");
            pt.println("            </div>");
            pt.println("            <div id=\"content\" class=\"right_content\">");
            pt.println("                <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("            </div>");
            pt.println("        </div>");
            pt.println("");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");

        } catch (Exception ex) {
            DGlobal.error("leftCenterRight() " + ex.getLocalizedMessage());
        }
    }

    private void topLeftRight() {
        try {
            pt.println("<?xml version='1.0' encoding='UTF-8' ?> ");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
            escribirHead();
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");
            pt.println("");
            pt.println("        <div id=\"top\" class=\"top\">");
            pt.println("            <ui:insert name=\"top\">Top</ui:insert>");
            pt.println("        </div>");
            pt.println("        <div>");
            escribirLeft();
            pt.println("            <div id=\"content\" class=\"left_content\">");
            pt.println("                <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("            </div>");
            pt.println("        </div>");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");

        } catch (Exception ex) {
            DGlobal.error("topLeftRight() " + ex.getLocalizedMessage());
        }
    }

    private void topLeftCenterRight() {
        try {
            pt.println("<?xml version='1.0' encoding='UTF-8' ?>");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
            escribirHead();
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");
            pt.println("        <div id=\"top\">");
            pt.println("            <ui:insert name=\"top\">Top</ui:insert>");
            pt.println("        </div>");
            pt.println("        <div>");

            escribirLeft();
            pt.println("           <div>");
            pt.println("                <div id=\"right\">");
            pt.println("                    <ui:insert name=\"right\">Right</ui:insert>");
            pt.println("                </div>");
            pt.println("                <div id=\"content\" class=\"right_content\">");
            pt.println("                    <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("                </div>");
            pt.println("            </div>");
            pt.println("        </div> ");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");

        } catch (Exception ex) {
            DGlobal.error("topLeftCenterRight() " + ex.getLocalizedMessage());
        }

    }

    private void topLeftRigthBottom() {
        try {
            pt.println("<?xml version='1.0' encoding='UTF-8' ?>");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
            escribirHead();
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");
            pt.println("        <div id=\"top\">");
            pt.println("            <ui:insert name=\"top\">Top</ui:insert>");
            pt.println("        </div>");
            pt.println("        <div>");
            escribirLeft();
            pt.println("            <div id=\"content\" class=\"left_content\">");
            pt.println("                <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("            </div>");
            pt.println("        </div>");
            pt.println("        <div id=\"bottom\">");
            pt.println("            <ui:insert name=\"bottom\">Bottom</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");

        } catch (Exception ex) {
            DGlobal.error("topLeftRigthBottom() " + ex.getLocalizedMessage());
        }

    }

    private void topLeftCenterRightBottom() {
        try {
            pt.println("<?xml version='1.0' encoding='UTF-8' ?> ");
            pt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            pt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            pt.println("      xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
            pt.println("      xmlns:h=\"http://java.sun.com/jsf/html\">");
            pt.println("");
           escribirHead();
            pt.println("");
            pt.println("    <h:body>");
            pt.println("");
            pt.println("        <div id=\"top\">");
            pt.println("            <ui:insert name=\"top\">Top</ui:insert>");
            pt.println("        </div>");
            pt.println("        <div>");
            escribirLeft();
            pt.println("            <div>");
            pt.println("                <div id=\"right\">");
            pt.println("                    <ui:insert name=\"right\">Right</ui:insert>");
            pt.println("                </div>");
            pt.println("                <div id=\"content\" class=\"right_content\">");
            pt.println("                    <ui:insert name=\"content\">Content</ui:insert>");
            pt.println("                </div>");
            pt.println("            </div>");
            pt.println("        </div>");
            pt.println("        <div id=\"bottom\">");
            pt.println("            <ui:insert name=\"bottom\">Bottom</ui:insert>");
            pt.println("        </div>");
            pt.println("");
            pt.println("    </h:body>");
            pt.println("");
            pt.println("</html>");
        } catch (Exception ex) {
            DGlobal.error("topLeftCenterRightBottom() " + ex.getLocalizedMessage());
        }

    }

    private void escribirLeft() {
        try {
            pt.println("            <div id=\"left\">");
            pt.println("                 <ui:insert name=\"left\">");
            pt.println("                     <p:accordionPanel>");
            pt.println("                         <p:tab title=\"Tab I\">");
            pt.println("                             <h:panelGrid columns=\"2\" cellpadding=\"10\"> ");
            pt.println("                                 <h:outputText  value=\"First tab\" />  ");
            pt.println("                             </h:panelGrid>  ");
            pt.println("                         </p:tab>  ");
            pt.println("                         <p:tab title=\"Tab II\">");
            pt.println("                             <h:panelGrid columns=\"2\" cellpadding=\"10\"> ");
            pt.println("                                 <h:outputText value=\"Second Tab\" /> ");
            pt.println("                             </h:panelGrid>  ");
            pt.println("                         </p:tab>  ");
            pt.println("                     </p:accordionPanel>  ");
            pt.println("                 </ui:insert>");
            pt.println("            </div>");
        } catch (Exception ex) {
            DGlobal.error("escribirLeft() " + ex.getLocalizedMessage());
        }
    }
    
    private void escribirHead(){
        try{
             pt.println("    <h:head>");
            pt.println("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
            pt.println("        <link href=\"./resources/css/default.css\" rel=\"stylesheet\" type=\"text/css\" />");
            pt.println("        <link href=\"./resources/css/cssLayout.css\" rel=\"stylesheet\" type=\"text/css\" />");
            pt.println("       <title><h:outputText value=\"#{mensajes['aplicacion.titulo']}\"/></title>");
            pt.println("    </h:head>");
        } catch (Exception ex) {
            DGlobal.error("escribirHead() " + ex.getLocalizedMessage());
        }
    }
}