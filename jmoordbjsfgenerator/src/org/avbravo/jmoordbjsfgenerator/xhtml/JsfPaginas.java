/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.xhtml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import org.avbravo.jmoordbjsfgenerator.analizador.AnalizadorRelaciones;
import org.avbravo.jmoordbjsfgenerator.domains.Columnas;
import org.avbravo.jmoordbjsfgenerator.domains.Relaciones;
import org.avbravo.jmoordbjsfgenerator.generales.Directorios;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.avbravo.jmoordbjsfgenerator.utilidades.Utilidades;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class JsfPaginas {

    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pt;
    FileReader fr = null;
    BufferedReader br = null;
    File filePlantilla;
    String ltexto = "";
    HashMap menu = new HashMap();
    private String nombreClase;
    private String nombreClaseServices;
    private String nombreClaseData;
    private String rutaArchivoWeb;
    private String rutaArchivoPropiedades;
    private String rutaArchivoBeans;
    private String boton;
    private String botonEnglish;
    // indica si es crear, actualizar, eliminar, imprimir
    private String tipoCrud;
    /*
     *
     * @ColumnAnnotation
     */
    String nombre;
    boolean isNoNulo;
    String tipo;
    Integer tamano;
    Integer digitosDecimales;
    String comentario;
    String is_autoincrementable;
    boolean isPK;
    boolean isImagen;
    boolean isUrl;
    /*
     *
     * @RelacionadasAnnotation
     */
    String claseController;
    String clase;
    String tabla;
    String columna;
    String regla_actualizacion;
    String regla_eliminacion;
    Integer key_seq;
    String nombre_relacion;
    String tipo_relacion;
    private String saltoLinea = "\n";
    private String formRendered;
    private String formNotRendered;
    List<Columnas> arrayColumnas;
    List<Relaciones> arrayRelaciones;
    StringBuilder camposAjax;
    StringBuilder fieldsJSF;
    StringBuilder camposDialogQuery;
    private Integer numeroCampos = 0;
    private Boolean tieneRelaciones = false;

    public String getNombreClaseData() {
        return nombreClaseData;
    }

    public void setNombreClaseData(String nombreClaseData) {
        this.nombreClaseData = nombreClaseData;
    }

    public String getBotonEnglish() {
        return botonEnglish;
    }

    public void setBotonEnglish(String botonEnglish) {
        this.botonEnglish = botonEnglish;
    }

    public String getTipoCrud() {
        return tipoCrud;
    }

    public void setTipoCrud(String tipoCrud) {
        this.tipoCrud = tipoCrud;
    }

    public String getFormNotRendered() {
        return formNotRendered;
    }

    public void setFormNotRendered(String formNotRendered) {
        this.formNotRendered = formNotRendered;
    }

    public String getFormRendered() {
        return formRendered;
    }

    public void setFormRendered(String formRendered) {
        this.formRendered = formRendered;
    }

    public HashMap getMenu() {
        return menu;
    }

    public void setMenu(HashMap menu) {
        this.menu = menu;
    }

    public String getNombreClaseServices() {
        return nombreClaseServices;
    }

    public void setNombreClaseServices(String nombreClaseServices) {
        this.nombreClaseServices = nombreClaseServices;
    }

    public String getClaseController() {
        return claseController;
    }

    public void setClaseController(String claseController) {
        this.claseController = claseController;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getRutaArchivoBeans() {
        return rutaArchivoBeans;
    }

    public void setRutaArchivoBeans(String rutaArchivoBeans) {
        this.rutaArchivoBeans = rutaArchivoBeans;
    }

    public String getRutaArchivoPropiedades() {
        return rutaArchivoPropiedades;
    }

    public void setRutaArchivoPropiedades(String rutaArchivoPropiedades) {
        this.rutaArchivoPropiedades = rutaArchivoPropiedades;
    }

    public String getRutaArchivoWeb() {
        return rutaArchivoWeb;
    }

    public void setRutaArchivoWeb(String rutaArchivoWeb) {
        this.rutaArchivoWeb = rutaArchivoWeb;
    }

    /*
     *
     * ruta_plantilla: corresponde a la ruta del archivo plantilla nombreClase
     * es el que se usara para los beans rutaWeb corresponde a la ruta del
     * archivo xhtml que se desea crear nombre_plantilla es el nombre de la
     * plantilla
     */
    public void makeFileJSF() {
        try {
            fw = new FileWriter(rutaArchivoWeb);
            bw = new BufferedWriter(fw);
            pt = new PrintWriter(bw);
            /*
             * Plantilla
             */
            String lineSep = System.getProperty("line.separator");
            BufferedReader br = new BufferedReader(new FileReader(Directorios.getRutaArchivoPlantilla()));
            String nextLine = "";
            while ((nextLine = br.readLine()) != null) {
                analizarTexto(nextLine, "pagina");
            }
            pt.close();
            fw.close();
            bw.close();
        } catch (Exception ex) {
            DGlobal.error("makeFileJSF() " + ex.getLocalizedMessage());
        }
    }
    /*
     * Analiza cada linea de la plantilla el parametro generarArchivoXHTML true:
     * indica que son archivos xhtml generados de las clases false: indica que
     * es el archivo de menu final con todos los enlaces
     *
     */

    private void analizarTexto(String texto, String tipo) {
        try {
            /*
             * encabezado
             */
            if (texto.indexOf("<?xml version=") != - 1) {
                pt.println(texto);
                return;
            }
            if (texto.indexOf("<!DOCTYPE html PUBLIC") != - 1) {
                pt.println(texto);
                return;
            }
            if (texto.indexOf("xmlns=") != -1) {
                pt.println(texto);
                return;
            }
            if (texto.indexOf("xmlns:") != -1) {
                pt.println(texto);
                return;
            }

            if (texto.indexOf("<h:body>") != -1) {
                if (!tipo.equals("index")) {
                    pt.println(sp(5) + "<h:body>");
                    pt.println("\n" + sp(9) + "<ui:composition template=\"./../../" + Directorios.getNombreArchivoPlantilla() + "\">");
                } else {
                    pt.println(sp(5) + "<h:body>");
                    pt.println("\n" + sp(9) + "<ui:composition template=\"./" + Directorios.getNombreArchivoPlantilla() + "\">");
                }
                return;
            }
            if (texto.indexOf("</h:body>") != -1) {
                pt.println(sp(9) + "</ui:composition>");
                pt.println(sp(5) + "\n</h:body>");
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
                pt.println(sp(13) + "<!--\n" + sp(14) + "<ui:define name=\"toformRenderedp\">\n" + sp(17) + "top\n" + sp(14) + "</ui:define>\n" + sp(13) + "-->");
                return;
            }
            if (texto.indexOf("<div id=\"left\">") != -1) {
                pt.println(sp(13) + "<!--\n" + sp(14) + "<ui:define name=\"left\">\n" + sp(17) + "left\n" + sp(14) + "</ui:define>\n" + sp(13) + "-->");
                return;
            }
            if (texto.indexOf("<div id=\"content\"") != -1) {
                pt.println(sp(13) + "<ui:define name=\"content\">");
                /*
                 * Genero el contenido que estara en el content
                 */

                AnalizadorRelaciones analizadorRelaciones = new AnalizadorRelaciones(rutaArchivoBeans);
                arrayColumnas = analizadorRelaciones.getArrayColumnas();
                arrayRelaciones = analizadorRelaciones.getArrayRelaciones();

                generarContenido();
                pt.println(sp(13) + "</ui:define>");
                return;
            }
            if (texto.indexOf("<div id=\"right\"") != -1) {
                pt.println(sp(13) + "<!--\n" + sp(14) + "<ui:define name=\"rigth\">\n" + sp(17) + "Right\n" + sp(14) + "</ui:define>\n" + sp(13) + "-->");
                return;
            }
            if (texto.indexOf("<div id=\"bottom\"") != -1) {
                pt.println(sp(13) + "<!--\n" + sp(14) + "<ui:define name=\"bottom\">\n" + sp(17) + "Bottom\n" + sp(14) + "</ui:define>\n" + sp(13) + "-->");
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
                pt.println("</html>");
                return;
            }
            return;
        } catch (Exception ex) {
            DGlobal.error("analizarTexto() " + ex.getLocalizedMessage());
        }
        return;
    }


    /*
     * generarContenido()
     */
    private void generarContenido() {
        try {
            /*
             * genera la pagina xhtml
             */
            pt.println(sp(17) + "<h:form id=\"form" + this.nombreClase + Utilidades.convertirLetraMayuscula(this.botonEnglish) + "\"  rendered=" + this.formRendered + ">");
            pt.println(sp(17) + "<p:panelGrid columns=\"2\">");
            pt.println(sp(25) + "<f:facet name=\"header\">");
            pt.println(sp(29) + "<h:outputText value=\"#{" + MySession.getVar() + "['tabla." + Utilidades.convertirLetraMayuscula(this.nombreClase) + "']}\" />");
            pt.println(sp(25) + "</f:facet>");
            analizarAnotacion();
            generarCierrePagina();
        } catch (Exception ex) {
            DGlobal.error("generarContenido() " + ex.getLocalizedMessage());
        }
    }

    private void generarCierrePagina() {
        try {

            pt.println(sp(25) + "  <f:facet name=\"footer\">");
            if (this.tipoCrud.equals("Crear")) {
                StringBuilder sb = new StringBuilder("");
                sb.append(sp(29) + "<p:commandButton action=\"#{" + this.nombreClaseServices + ".insert}\"");
                if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                    sb.append(" value=\"#{" + MySession.getVar() + "['boton." + this.boton + "']}\"");
                }
                sb.append(" ajax=\"false\"");
                if//            pt.println(sp(29) + "<ui:include src=\"" + this.nombreClase + ".xhtml\"/> ");
                        (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                    sb.append(" icon=\"ui-icon-disk\"");
                }
                sb.append(" />");
                pt.println(sb.toString());
            }
            if (!this.tipoCrud.equals("Crear")) {
                StringBuilder sbQuery = new StringBuilder("");
                sbQuery.append(sp(29) + "<p:commandButton action=\"#{" + this.nombreClaseServices + ".findByPrimaryKey()}\"");
                     sbQuery.append(" rendered=\"#{menuBeans.").append(this.nombreClase).append("Consultar}\"");
                if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                    sbQuery.append(" value=\"#{" + MySession.getVar() + "['boton.consultar']}\"");
                }
                sbQuery.append(" ajax=\"false\"");
                if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                    sbQuery.append(" icon=\"ui-icon-search\"");
                }
                sbQuery.append(" />");
                pt.println(sbQuery.toString());

                StringBuilder sbEdit = new StringBuilder("");
                sbEdit.append(sp(29) + "<p:commandButton action=\"#{" + this.nombreClaseServices + ".update}\"");
                if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                    sbEdit.append(" value=\"#{" + MySession.getVar() + "['boton.editar']}\"");
                }
                sbEdit.append(" rendered=\"#{menuBeans.").append(this.nombreClase).append("Editar}\"");
                sbEdit.append(" ajax=\"false\"");
                if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                    sbEdit.append(" icon=\"ui-icon-disk\"");
                }
                sbEdit.append(" />");
                pt.println(sbEdit.toString());
                StringBuilder sbDelete = new StringBuilder("");
                sbDelete.append(sp(29) + "<p:commandButton id=\"showDialogButton\"");
                    sbDelete.append(" rendered=\"#{menuBeans.").append(this.nombreClase).append("Eliminar}\"");
                if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                    sbDelete.append(" value=\"#{" + MySession.getVar() + "['boton.eliminar']}\"");
                }
                sbDelete.append(" onclick=\"confirmation.show()\" type=\"button\"");
                if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                    sbDelete.append(" icon=\"ui-icon-trash\"");
                }
                sbDelete.append(" />");
                pt.println(sbDelete.toString());
            }
            pt.println(sp(25) + "</f:facet>");
            pt.println(sp(25) + "</p:panelGrid>");

            pt.println(sp(21) + "<br />");

            if (MySession.getGenerateGrowlnPage()) {
                if (MySession.getTipoMensajes().equals("growl")) {
                    if (MySession.getGrowSticker()) {
                        pt.append("\n" + sp(21) + "<p:growl id=\"growl\" showDetail=\"true\" sticky=\"true\" /> ");
                    } else {
                        pt.append("\n" + sp(21) + "<p:growl id=\"growl\" life=\"" + MySession.getGrowlsSeconds() + "\" /> ");
                    }
                } else {
                    if (MySession.getTipoMensajes().equals("growlandmessages")) {
                        if (MySession.getGrowSticker()) {
                            pt.append("\n" + sp(21) + "<p:growl id=\"growl\" showDetail=\"true\" sticky=\"true\" /> ");
                        } else {
                            pt.append("\n" + sp(21) + "<p:growl id=\"growl\" life=\"" + MySession.getGrowlsSeconds() + "\" /> ");
                        }
                        pt.println("\n" + sp(21) + "<h:messages id=\"idmessages\" style=\"color: red; text-decoration:overline\" />");
                    } else {
                        pt.println("\n" + sp(21) + "<h:messages id=\"idmessages\" style=\"color: red; text-decoration:overline\"/>");
                    }
                }

            }


//            pt.println("\n" + sp(21) + "<p:confirmDialog id=\"confirmDialog\" message=\"#{" + MySession.getVar() + "['msg.deletequestion']}\" ");
//            pt.println(sp(38) + "header=\"#{" + MySession.getVar() + "['msg.deletetitle']}\" severity=\"alert\" widgetVar=\"confirmation\"> ");
//            if (camposAjax.toString().equals("")) {
//                /*
//                 * 
//                 */
//                StringBuilder sb1 = new StringBuilder("");
//                sb1.append("\n" + sp(25) + "<p:commandButton id=\"confirm\" value=\"#{" + MySession.getVar() + "['boton.yes']}\"");
//                if (MySession.getTipoMensajes().equals("growl") && MySession.getGenerateGrowlnPage()) {
//                    sb1.append(" update=\"growl\"");
//                }
//                if (MySession.getTipoMensajes().equals("mensajes") && MySession.getGenerateGrowlnPage()) {
//                    sb1.append(" update=\"idmessages\"");
//                }
//                if (MySession.getTipoMensajes().equals("growlandmessages") && MySession.getGenerateGrowlnPage()) {
//                    sb1.append(" update=\"growl,idmessages\"");
//                }
//                sb1.append(" oncomplete=\"confirmation.hide()\" ");
//                /*
//                 * 
//                 */
//                pt.println(sb1.toString());
//            } else {
//                StringBuilder sb1 = new StringBuilder("");
//                sb1.append("\n" + sp(25) + "<p:commandButton id=\"confirm\" value=\"#{" + MySession.getVar() + "['boton.yes']}\"");
//                if (MySession.getTipoMensajes().equals("growl") && MySession.getGenerateGrowlnPage()) {
//                    sb1.append(" update=\"growl,");
//                }
//                if (MySession.getTipoMensajes().equals("mensajes") && MySession.getGenerateGrowlnPage()) {
//                    sb1.append(" update=\"idmessages,");
//                }
//                if (MySession.getTipoMensajes().equals("growlandmessages") && MySession.getGenerateGrowlnPage()) {
//                    sb1.append(" update=\"growl,idmessages,");
//                }
//                String textoUpdate = sb1.toString().indexOf("update") != -1 ? "" : " update=\"";
//                String idform = "form" + this.nombreClase + Utilidades.convertirLetraMayuscula(this.botonEnglish) + "\"";
//                //sb1.append(camposAjax.toString() + "\" oncomplete=\"confirmation.hide()\" ");;
//                sb1.append(textoUpdate + idform + " oncomplete=\"confirmation.hide()\" ");;
//                pt.println(sb1.toString());
//            }
//         
//                pt.println(sp(42) + "actionListener=\"#{" + this.nombreClaseServices + ".delete}\" />");
//          
//
//            pt.println("\n" + sp(25) + "<p:commandButton id=\"decline\" value=\"#{" + MySession.getVar() + "['boton.no']}\" onclick=\"confirmation.hide()\" type=\"button\" /> ");
//            pt.println(sp(21) + "</p:confirmDialog>");

            /*
             * dialog
             */
           
            pt.println("\n" + sp(17) + "</h:form>");
            pt.println("");
            /*
             * form no logueado
             */
            generarFormNoLogeado();
        } catch (Exception ex) {
            DGlobal.error("generarCierrePagina() " + ex.getLocalizedMessage());
        }
    }

    public void generarFormNoLogeado() {
        try {
            pt.println(sp(17) + "<h:form rendered=" + this.formNotRendered + ">");
            pt.println(sp(21) + "<p:panelGrid columns=\"1\">");
            pt.println(sp(25) + "<h:outputText value=\"#{" + MySession.getVar() + "['login.accesodenegado']}\"/>");
            pt.println(sp(25) + "<h:outputText value=\"#{" + MySession.getVar() + "['login.accesodenegadoDetalle']}\"/>");
            pt.println(sp(25) + "<p:commandButton action=\"#{loginBean.irLogin}\" value=\"#{mensajes['boton.regresar']}\" ajax=\"false\"/>");
            pt.println(sp(21) + "</p:panelGrid>");
            pt.println(sp(17) + "</h:form>");
        } catch (Exception ex) {
            DGlobal.error("generarFormNoLogeado() " + ex.getLocalizedMessage());
        }
    }

    /*
     * Genera la pagina de menu
     *
     */
    private void analizarAnotacion() {
        try {
            numeroCampos = 0;
            tieneRelaciones = false;

            fieldsJSF = new StringBuilder("");
            camposDialogQuery = new StringBuilder("");
            camposAjax = new StringBuilder("");
            Boolean found = Boolean.FALSE;
            for (Columnas columnas : arrayColumnas) {
                if (columnas.getIsVisible()) {
                    Boolean encontrado = false;
                    for (Relaciones relaciones : arrayRelaciones) {

                        this.tieneRelaciones = true;
                        for (String col : relaciones.getColumnas()) {
                            if (columnas.getNombre().trim().equals(col.trim())) {
                                if (relaciones.getColumnas().size() > 1) {
                                    encontrado = false;
                                } else {
                                    campoRelacionado(columnas, relaciones);
                                    numeroCampos++;
                                    encontrado = true;
                                }
                            }
                        }
                    }//relaciones
                    if (!encontrado) {
                        numeroCampos++;
                        if (columnas.getTipo().equals("Boolean") || columnas.getTipo().equals("boolean")) {
                            campoBooleano(columnas);
                        } else {
                            if (columnas.getTipo().equals("Date") || columnas.getTipo().equals("date")) {
                                campoDate(columnas);
                            } else {
                                //
                                if (columnas.getTipo().equals("Timestamp") || columnas.getTipo().equals("timestamp")) {
                                    campoTimestamp(columnas);
                                } else {
                                    if (columnas.getTipo().equals("Time") || columnas.getTipo().equals("time")) {
                                        campoTime(columnas);
                                    } else {
                                        if (columnas.getIsImagen()) {
                                            campoImagen(columnas);
                                        } else {
                                            campoTexto(columnas, found);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    campoHiden(columnas);
                }
            }
        } catch (Exception ex) {
            DGlobal.error("analizarAnotacion() " + ex.getLocalizedMessage());
        }
    }

   

    private void campoRelacionado(Columnas columnas, Relaciones relaciones) {
        try {
        
                pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
                pt.println(sp(25) + "<p:selectOneMenu value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\">");
                pt.println(sp(29) + "<f:selectItems value=\"#{" + Utilidades.convertirLetraMinuscula(relaciones.getClase()) + "Services.loadItems()}\"/>");
                pt.println(sp(25) + "</p:selectOneMenu>");
            
        } catch (Exception e) {
            DGlobal.error("campoRelacionado() " + e.getLocalizedMessage());
        }
    }

    private void campoBooleano(Columnas columnas) {
        try {
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            fieldsJSF.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            camposDialogQuery.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");

            if (tipoCrud.equals("Crear")) {
                pt.println(sp(25) + "<h:selectBooleanCheckbox value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "} #{mensajes['msg.nonulo']} \" />");
            } else {
                pt.println(sp(25) + "<h:selectBooleanCheckbox value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"  />");
                fieldsJSF.append("\n" + sp(29) + "<h:selectBooleanCheckbox value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"  />");
                camposDialogQuery.append("\n" + sp(29) + "<h:selectBooleanCheckbox value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"  />");
            }
        } catch (Exception e) {
            DGlobal.error("campoBooleano() " + e.getLocalizedMessage());
        }
    }

    private void campoDate(Columnas columnas) {
        try {
           
                pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
                fieldsJSF.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
                camposDialogQuery.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
           
            if (tipoCrud.equals("Crear")) {
                pt.println(sp(25) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}  #{mensajes['msg.nonulo']}\" pattern=\"" + MySession.getPatronFecha() + "\"/>");
            } else {
               
                    pt.println(sp(37) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronFecha() + "\"/>");
              

                fieldsJSF.append("\n" + sp(27) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronFecha() + "\"/>");
                camposDialogQuery.append("\n" + sp(37) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronFecha() + "\"/>");
            }
        
        } catch (Exception e) {
            DGlobal.error("campoDate() " + e.getLocalizedMessage());
        }
    }

    private void campoTimestamp(Columnas columnas) {
        try {
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            fieldsJSF.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            camposDialogQuery.append("\n" + sp(41) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            if (tipoCrud.equals("Crear")) {
                pt.println(sp(25) + "<p:calendar  showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}  #{mensajes['msg.nonulo']}\" pattern=\"" + MySession.getPatronFechaHora() + "\"/>");
            } else {
                pt.println(sp(25) + "<p:calendar  showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronFechaHora() + "\"/>");
                fieldsJSF.append("\n" + sp(25) + "<p:calendar  showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronFechaHora() + "\"/>");
                camposDialogQuery.append("\n" + sp(25) + "<p:calendar  showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronFechaHora() + "\"/>");

            }
        } catch (Exception e) {
            DGlobal.error("campoTimestamp() " + e.getLocalizedMessage());
        }
    }

    private void campoTime(Columnas columnas) {
        try {
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            fieldsJSF.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            camposDialogQuery.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            if (tipoCrud.equals("Crear")) {
                pt.println(sp(25) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}  #{mensajes['msg.nonulo']}\" pattern=\"" + MySession.getPatronHora() + "\"/>");
            } else {
                pt.println(sp(25) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronHora() + "\"/>");
                fieldsJSF.append("\n" + sp(25) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronHora() + "\"/>");
                camposDialogQuery.append("\n" + sp(25) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronHora() + "\"/>");
            }
        } catch (Exception e) {
            DGlobal.error("campoTime() " + e.getLocalizedMessage());
        }
    }

    private void campoImagen(Columnas columnas) {
        try {
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            pt.println(sp(25) + "<h:graphicImage value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"></h:graphicImage>");
            fieldsJSF.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            fieldsJSF.append("\n" + sp(25) + "<h:graphicImage value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"></h:graphicImage>");
            camposDialogQuery.append("\n" + sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            camposDialogQuery.append("\n" + sp(25) + "<h:graphicImage value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"></h:graphicImage>");
        } catch (Exception e) {
            DGlobal.error("campoImagen() " + e.getLocalizedMessage());
        }
    }

    private void campoTexto(Columnas columnas, Boolean found) {
        try {
          
                pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
                fieldsJSF.append(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
                camposDialogQuery.append(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
                if (tipoCrud.equals("Crear")) {
                    pt.println(sp(25) + "<p:inputText id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "} #{mensajes['msg.nonulo']}\"/>");
                } else {
                    if (found) {
                        camposAjax.append(",");
                    }
                    camposAjax.append(columnas.getNombre());
                    found = Boolean.TRUE;
                    pt.println(sp(25) + "<p:inputText id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" />");
                    fieldsJSF.append("\n    <p:inputText id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" />");
                    camposDialogQuery.append("\n    <p:inputText id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" />");
                }

    
        } catch (Exception e) {
            DGlobal.error("campoImagen() " + e.getLocalizedMessage());
        }
    }
    /*
     * asigna espacios para colocar los componentes en las columnas
     */

    private void campoHiden(Columnas columnas) {
        try {
            pt.println(sp(25) + "<h:panelGroup>");
            pt.println(sp(25) + "</h:panelGroup>");
            pt.println(sp(25) + "<h:inputHidden id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase  + "." + columnas.getNombre() + "}\"  />");



        } catch (Exception e) {
            DGlobal.error("campoHiden() " + e.getLocalizedMessage());
        }
    }

    private String sp(Integer n) {
        String space = "";
        try {

            for (Integer i = 0; i < n - 1; i++) {
                space += " ";
            }

        } catch (Exception e) {
            DGlobal.error("espacios() " + e.getLocalizedMessage());
        }
        return space;
    }
}