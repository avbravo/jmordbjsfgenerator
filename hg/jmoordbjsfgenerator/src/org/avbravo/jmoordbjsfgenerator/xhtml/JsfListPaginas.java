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
import static org.avbravo.jmoordbjsfgenerator.xhtml.IndexXhmtl.salida;

/**
 *
 * @author avbravo
 */
public class JsfListPaginas {

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
    StringBuilder fieldsJSFDialogInsert;
    StringBuilder camposDialogQuery;
    private Integer numeroCampos = 0;
    private Boolean tieneRelaciones = false;
    private String nombreForm;

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
            this.nombreForm = "form" + this.nombreClase + "List";
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
            pt.println(sp(17) + "<h:form id=\"" + this.nombreForm + "\"  rendered=" + this.formRendered + ">");
            generarDataTables();
            analizarAnotacion();
            generarCierrePagina();
        } catch (Exception ex) {
            DGlobal.error("generarContenido() " + ex.getLocalizedMessage());
        }
    }
    /*
     * genera el encabezado para las datatables 
     */

    private void generarDataTables() {
        try {
            pt.println(sp(21) + "<p:panelGrid columns=\"2\">");
            pt.println(sp(25) + "<f:facet name=\"header\">");
            pt.println(sp(29) + "<h:outputText value=\"#{" + MySession.getVar() + "['tabla." + Utilidades.convertirLetraMayuscula(this.nombreClase) + "']}\" />");
            pt.println(sp(25) + "</f:facet>");
            pt.println(sp(21) + "</p:panelGrid>");
            pt.println(sp(29) + "<p:dataTable var=\"var\" value=\"#{" + this.nombreClaseData + "." + this.nombreClase + "list}\" paginator=\"true\"");
            pt.println(sp(42) + "rows=\"" + MySession.getRowsDataTable() + "\" ");
            pt.println(sp(42) + "selectionMode=\"single\"");
            for (Columnas columnas : arrayColumnas) {
                if (columnas.getIsPK()) {
                    pt.println(sp(42) + "rowKey=\"#{var." + columnas.getNombre() + "}\"");
                    break;
                }
            }
            pt.println(sp(42) + "selection=\"#{" + this.nombreClaseData + ".selected" + Utilidades.convertirLetraMayuscula(this.nombreClase) + "}\"  ");
            pt.println(sp(42) + "widgetVar= \"" + this.nombreClase.toLowerCase() + "Table\"");
            pt.println(sp(42) + "filteredValue=\"#{" + this.nombreClaseData + ".filtered" + Utilidades.convertirLetraMayuscula(this.nombreClase) + "}\"");
            pt.println(sp(42) + "id=\"datatable" + Utilidades.convertirLetraMayuscula(this.nombreClase) + "\"");
            if (MySession.getTableScrollable()) {
                pt.println(sp(42) + "scrollable=\"true\"");
            }
            pt.println(sp(42) + ">");
            pt.println(sp(33) + "<f:facet name=\"header\">");
            pt.println(sp(37) + "<p:outputPanel>");
            StringBuilder sbr = new StringBuilder("");
            if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                sbr.append(" value=\"#{").append(MySession.getVar()).append("['boton.crear']}\"");
            }
            if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                sbr.append(" icon=\"ui-icon-plus\"");
            }
                sbr.append(" title=\"#{").append(MySession.getVar()).append("['boton.crear']}\"");
            pt.println("\n" + sp(25) + "<p:commandButton id=\"mostrarInsertButton\" rendered=\"#{menuBeans." +this.nombreClase.toLowerCase()+"Crear}\"" + sbr.toString() + " oncomplete=\"" + this.nombreClase + "DialogInsert.show()\"/> ");
            pt.println(sp(41) + "<h:outputText value=\"#{mensajes['msg.filterallfield']}\" />");
            pt.println(sp(41) + "<p:inputText id=\"globalFilter\" onkeyup=\"" + this.nombreClase.toLowerCase() + "Table.filter()\" style=\"width:150px\" />  ");
            pt.println(sp(37) + "</p:outputPanel>");
            pt.println(sp(33) + "</f:facet> ");
//            if (MySession.getAjaxRowSelect()) {

                pt.println(sp(33) + "<p:ajax event=\"rowSelect\"  listener=\"#{" + nombreClaseData + ".establecer()}\" oncomplete=\"" + this.nombreClase + "Dialog.show()\" update=\":form" + this.nombreClase + "List:display" + this.nombreClase + "\" />");
//            }

        } catch (Exception ex) {
            DGlobal.error("generarDataTables() " + ex.getLocalizedMessage());
        }
    }

    private void generarCierrePagina() {
        try {
            generarFooterTabla();


            pt.println(sp(29) + "</p:dataTable>");
            pt.println(sp(21) + "<br />");
            generarGrowl();

            generarDialogoEliminar();
            generarDialogoEditar();
            /*
             *dialogo insertar 
             */
            generarDialogoInsertar();
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

    private void generarFooterTabla() {
        try {
            pt.println(sp(33) + "<f:facet name=\"footer\">");
            pt.println(sp(33) + "</f:facet>");
        } catch (Exception e) {
            DGlobal.error("generarFooterTabla()" + e.getLocalizedMessage());
        }
    }

    private void generarGrowl() {

        try {
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
        } catch (Exception e) {
            DGlobal.error("generarGrowl()" + e.getLocalizedMessage());
        }
    }

    private void generarDialogoEliminar() {
        try {
            /*
             * dialogo de eliminar
             */

            pt.println("\n" + sp(21) + "<p:confirmDialog id=\"confirmDialog\" message=\"#{" + MySession.getVar() + "['msg.deletequestion']}\" ");
            pt.println(sp(38) + "header=\"#{" + MySession.getVar() + "['msg.deletetitle']}\" severity=\"alert\" widgetVar=\"confirmation\"> ");
            StringBuilder sb1 = new StringBuilder("");
            sb1.append("\n").append(sp(25)).append("<p:commandButton id=\"confirmButton\" value=\"#{").append(MySession.getVar()).append("['boton.yes']}\"");
            //
            sb1.append("\n").append(sp(42)).append("update=\":").append(this.nombreForm).append(":" + "display").append(this.nombreClase);
            if (MySession.getTipoMensajes().equals("growl") && MySession.getGenerateGrowlnPage()) {
                sb1.append(", :").append(this.nombreForm).append(":growl");
            }
            if (MySession.getTipoMensajes().equals("mensajes") && MySession.getGenerateGrowlnPage()) {
                sb1.append(", :").append(this.nombreForm).append(":idmessages");
            }
            if (MySession.getTipoMensajes().equals("growlandmessages") && MySession.getGenerateGrowlnPage()) {

                sb1.append(", :").append(this.nombreForm).append(":growl");
                sb1.append(", :").append(this.nombreForm).append(":idmessages");
            }
            sb1.append(", :").append(this.nombreForm).append(":" + "datatable").append(Utilidades.convertirLetraMayuscula(this.nombreClase)).append("\"");
            sb1.append("\n").append(sp(42)).append(" oncomplete=\"confirmation.hide(), ").append(this.nombreClase).append("Dialog.hide()\" ");
            pt.println(sb1.toString());
            pt.println(sp(42) + "actionListener=\"#{" + this.nombreClaseData + ".delete}\" />");
            pt.println("\n" + sp(25) + "<p:commandButton id=\"declineButton\" value=\"#{" + MySession.getVar() + "['boton.no']}\" onclick=\"confirmation.hide()\" type=\"button\" /> ");
            pt.println(sp(21) + "</p:confirmDialog>");

        } catch (Exception e) {
            DGlobal.error("generarDialogoEliminar()" + e.getLocalizedMessage());
        }
    }

    private void generarDialogoEditar() {
        try {
            /*
             * Crea el dialo editar/eliminar
             */

       
            pt.println(sp(21) + "<p:dialog id=\"dialog" + this.nombreClase + "\" rendered=\"#{menuBeans."+ this.nombreClase+"Consultar}\" header=\"#{" + MySession.getVar() + "['tabla." + Utilidades.convertirLetraMayuscula(this.nombreClase) + "']}\"" + " widgetVar=\"" + this.nombreClase + "Dialog\" resizable=\"false\"  height=\"" + (MySession.getDialogDinamyc() ? this.numeroCampos * 45 : MySession.getDialogHeight()) + "\" width=\"" + (!tieneRelaciones ? MySession.getDialogWidth() : MySession.getDialogWidthRelation()) + "\" showEffect=\"clip\" hideEffect=\"fold\"> ");
            pt.println(sp(23) + "<h:panelGroup id=\"display" + this.nombreClase + "\">");
            pt.println(sp(25) + "<h:panelGrid id=\"displayQuery\" columns=\"2\" cellpadding=\"4\" ");
            pt.println(sp(42) + "rendered=\"#{" + nombreClaseData + ".renderizar}\" >");
            pt.println(fieldsJSF.toString());
            pt.println(sp(25) + "  <f:facet name=\"footer\">");
            StringBuilder sbEdit = new StringBuilder("");
            sbEdit.append(sp(29)).append("<p:commandButton actionListener=\"#{").append(this.nombreClaseData).append(".update}\"");
            sbEdit.append(" id=\"editButton\" ");
            sbEdit.append(" rendered=\"#{menuBeans.").append(this.nombreClase).append("Editar}\"");
            if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                sbEdit.append(" value=\"#{").append(MySession.getVar()).append("['boton.editar']}\"");
            }
              sbEdit.append(" title=\"#{").append(MySession.getVar()).append("['boton.editar']}\"");
            if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                sbEdit.append(" icon=\"ui-icon-disk\"");
            }

            sbEdit.append("\n").append(sp(54)).append("update=\":").append(this.nombreForm).append(":" + "display").append(this.nombreClase);
            if (MySession.getTipoMensajes().equals("growl") && MySession.getGenerateGrowlnPage()) {
                sbEdit.append(", :").append(this.nombreForm).append(":growl");
            }
            sbEdit.append(", :").append(this.nombreForm).append(":" + "datatable").append(Utilidades.convertirLetraMayuscula(this.nombreClase)).append("\"");
            sbEdit.append("\n").append(sp(54)).append("oncomplete=\"").append(this.nombreClase).append("Dialog.hide()\"");

            sbEdit.append(" />");
            pt.println(sbEdit.toString());

            /*
             * 
             */
            StringBuilder sbDelete = new StringBuilder("");
            sbDelete.append(sp(29)).append("<p:commandButton id=\"deleteButton\"");
              sbDelete.append(" rendered=\"#{menuBeans.").append(this.nombreClase).append("Eliminar}\"");
            if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                sbDelete.append(" value=\"#{").append(MySession.getVar()).append("['boton.eliminar']}\"");
            }
              sbDelete.append(" title=\"#{").append(MySession.getVar()).append("['boton.eliminar']}\"");
            sbDelete.append(" onclick=\"confirmation.show()\" type=\"button\"");
            sbEdit.append("\n").append(sp(54)).append("oncomplete=\"").append(this.nombreClase).append("Dialog.hide()\"");
            if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                sbDelete.append(" icon=\"ui-icon-trash\"");
            }
            sbDelete.append(" />");
            pt.println(sbDelete.toString());

            StringBuilder sbCancelar = new StringBuilder("");
            sbCancelar.append(sp(29)).append("<p:commandButton ");
            sbCancelar.append(" id=\"cancelButton\" ");
            if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                sbCancelar.append(" value=\"#{").append(MySession.getVar()).append("['boton.regresar']}\"");
            }
             sbCancelar.append(" title=\"#{").append(MySession.getVar()).append("['boton.regresar']}\"");
            if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                sbCancelar.append(" icon=\"ui-icon-close\"");
            }
            sbCancelar.append(" onclick = \"").append(this.nombreClase).append("Dialog.hide()\"/>");
            pt.println(sbCancelar.toString());
            pt.println(sp(25) + "</f:facet>");
            pt.println(sp(25) + "</h:panelGrid>");
            pt.println(sp(23) + "</h:panelGroup>");
            pt.println(sp(21) + "</p:dialog> ");
        } catch (Exception e) {
            DGlobal.error("generarDialogoEditarEliminar()" + e.getLocalizedMessage());
        }
    }

    private void generarDialogoInsertar() {
        try {
            pt.println(sp(21) + "<p:dialog id=\"dialog" + this.nombreClase + "Insert\" rendered=\"#{menuBeans."+ this.nombreClase+"Crear}\" header=\"#{" + MySession.getVar() + "['tabla." + Utilidades.convertirLetraMayuscula(this.nombreClase) + "']}\"" + " widgetVar=\"" + this.nombreClase + "DialogInsert\" resizable=\"false\"  height=\"" + (MySession.getDialogDinamyc() ? this.numeroCampos * 45 : MySession.getDialogHeight()) + "\" width=\"" + (!tieneRelaciones ? MySession.getDialogWidth() : MySession.getDialogWidthRelation()) + "\" showEffect=\"clip\" hideEffect=\"fold\"> ");
            pt.println(sp(23) + "<h:panelGroup id=\"display" + this.nombreClase + "Insert\">");
            pt.println(sp(25) + "<h:panelGrid id=\"displayInsert\" columns=\"2\" cellpadding=\"4\"> ");
            pt.println(fieldsJSFDialogInsert.toString());
            pt.println(sp(25) + "  <f:facet name=\"footer\">");
            StringBuilder sb = new StringBuilder("");
            sb.append(sp(29)).append("<p:commandButton actionListener=\"#{").append(this.nombreClaseData).append(".insert}\"");
            if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                sb.append(" value=\"#{").append(MySession.getVar()).append("['boton.crear").append("']}\"");
            }
                 sb.append(" title=\"#{").append(MySession.getVar()).append("['boton.crear").append("']}\"");
            sb.append(sp(46)).append(" \noncomplete=\" handleSubmitRequest(xhr, status, args, '"+nombreClase+"DialogInsert','newUserForm');\"" );
            sb.append(sp(46)).append(" \najax=\"false\"");

            if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                sb.append(" icon=\"ui-icon-disk\"");
            }
            sb.append(" />");
            pt.println(sb.toString());


            StringBuilder sbCancelar = new StringBuilder("");
            sbCancelar.append(sp(29)).append("<p:commandButton ");
            sbCancelar.append(" id=\"cancelButton_2\" ");
            if (MySession.botonTextoIcono || MySession.botonTextoOnly) {
                sbCancelar.append(" value=\"#{").append(MySession.getVar()).append("['boton.regresar']}\"");
            }
               sbCancelar.append(" title=\"#{").append(MySession.getVar()).append("['boton.regresar']}\"");
            if (MySession.botonTextoIcono || MySession.botonIconoOnly) {
                sbCancelar.append(" icon=\"ui-icon-close\"");
            }
            sbCancelar.append(" onclick = \"").append(this.nombreClase).append("DialogInsert.hide()\"/>");
            pt.println(sbCancelar.toString());

            pt.println(sp(25) + "</f:facet>");
            pt.println(sp(25) + "</h:panelGrid>");
            pt.println(sp(23) + "</h:panelGroup>");
            pt.println(sp(21) + "</p:dialog> ");
        } catch (Exception e) {
            DGlobal.error("generarDialogInsertar() " + e.getLocalizedMessage());
        }
    }

    public void generarFormNoLogeado() {
        try {
            
            pt.println(sp(17) + "<h:form rendered=" + this.formNotRendered + ">");
            pt.append(sp(21) + "<p:dialog visible=\"true\" header=\"#{" + MySession.getVar() + "['login.accesodenegado']}\" widgetVar=\"dlg3\" showEffect=\"bounce\" hideEffect=\"explode\" >");            
            pt.println(sp(25) + "<p:panelGrid columns=\"1\">");
            pt.println(sp(29) + "<h:outputText value=\"#{" + MySession.getVar() + "['login.accesodenegadoDetalle']}\"/>");
            pt.println(sp(29) + "<p:commandButton action=\"#{loginBean.irLogin}\" value=\"#{mensajes['boton.regresar']}\" ajax=\"false\"/>");
            pt.println(sp(25) + "</p:panelGrid>");
             pt.println(sp(21) + "</p:dialog>"); 
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
            fieldsJSFDialogInsert = new StringBuilder("");
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

    private void escribirFacetColumna(String nombre_columna) {
        try {
            pt.println(sp(33) + "<p:column filterBy=\"#{var." + nombre_columna + "}\" sortBy=\"#{var." + nombre_columna + "}\">");
            pt.println(sp(37) + "<f:facet name=\"header\">");
            pt.println(sp(41) + "<h:outputText value=\"#{" + MySession.getVar() + "." + nombre_columna + "}\" />");
            pt.println(sp(37) + "</f:facet>");
            fieldsJSF.append("\n").append(sp(37)).append("<h:outputText value=\"#{").append(MySession.getVar()).append(".").append(nombre_columna).append("}\" />");
            fieldsJSFDialogInsert.append("\n").append(sp(37)).append("<h:outputText value=\"#{").append(MySession.getVar()).append(".").append(nombre_columna).append("}\" />");
            camposDialogQuery.append("\n").append(sp(37)).append("<h:outputText value=\"#{").append(MySession.getVar()).append(".").append(nombre_columna).append("}\" />");

        } catch (Exception e) {
            DGlobal.error("escribirFacetColumna() " + e.getLocalizedMessage());
        }

    }

    private void campoRelacionado(Columnas columnas, Relaciones relaciones) {
        try {

            escribirFacetColumna(columnas.getNombre());

            if (MySession.getTypeRelationsDataTable().equals("fields")) {
                pt.println(sp(37) + "<h:outputText value=\"#{var." + Utilidades.convertirLetraMinuscula(relaciones.getClase()) + "." + columnas.getNombre() + "}\" />");
            } else {
                if (MySession.getTypeRelationsDataTable().equals("selectOneMenu")) {

                    pt.println(sp(37) + "<p:selectOneMenu value=\"#{" + this.nombreClaseData + ".selected" + Utilidades.convertirLetraMayuscula(this.nombreClase) + "." + columnas.getNombre() + "}\">");
                    pt.println(sp(41) + "<f:selectItems value=\"#{" + Utilidades.convertirLetraMinuscula(relaciones.getClase()) + "Data.loadItems()}\"/>");
                    pt.println(sp(37) + "</p:selectOneMenu>");
                } else {
                    if (MySession.getTypeRelationsDataTable().equals("class.toString()")) {
                        pt.println(sp(37) + "<h:outputText value=\"#{var." + Utilidades.convertirLetraMinuscula(relaciones.getClase()) + "}\" />");
                    }
                }
            }
            camposDialogQuery.append("\n").append(sp(37)).append("<h:outputT"
                    + "ext value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" />");
            fieldsJSF.append("\n").append(sp(41)).append("<p:selectOneMenu value=\"#{").append(this.nombreClaseData).append(".selected").append(Utilidades.convertirLetraMayuscula(this.nombreClase)).append(".").append(columnas.getNombre()).append("}\">");
            fieldsJSF.append("\n").append(sp(43)).append("<f:selectItems value=\"#{").append(Utilidades.convertirLetraMinuscula(relaciones.getClase())).append("Data.loadItems()}\"/>");
            fieldsJSF.append("\n").append(sp(41)).append("</p:selectOneMenu>");
            fieldsJSFDialogInsert.append("\n").append(sp(41)).append("<p:selectOneMenu value=\"#{").append(this.nombreClase).append("Data").append(".").append(this.nombreClase).append("New.").append(columnas.getNombre()).append("}\">");
            fieldsJSFDialogInsert.append("\n").append(sp(43)).append("<f:selectItems value=\"#{").append(Utilidades.convertirLetraMinuscula(relaciones.getClase())).append("Data.loadItems()}\"/>");
            fieldsJSFDialogInsert.append("\n").append(sp(41)).append("</p:selectOneMenu>");
            pt.println(sp(33) + "</p:column>");
        } catch (Exception e) {
            DGlobal.error("campoRelacionado() " + e.getLocalizedMessage());
        }
    }

    private void campoBooleano(Columnas columnas) {
        try {
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            fieldsJSF.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            fieldsJSFDialogInsert.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            camposDialogQuery.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");

            if (tipoCrud.equals("Crear")) {
                pt.println(sp(25) + "<h:selectBooleanCheckbox value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "} #{mensajes['msg.nonulo']} \" />");
            } else {
                pt.println(sp(25) + "<h:selectBooleanCheckbox value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"  />");
                fieldsJSF.append("\n").append(sp(29)).append("<h:selectBooleanCheckbox value=\"#{").append(columnas.getNombre()).append("}\"  width=\"5\" height=\"5\"  />");
                fieldsJSFDialogInsert.append("\n").append(sp(29)).append("<h:selectBooleanCheckbox value=\"#{").append(columnas.getNombre()).append("}\"  width=\"5\" height=\"5\"  />");
                camposDialogQuery.append("\n").append(sp(29)).append("<h:selectBooleanCheckbox value=\"#{").append(columnas.getNombre()).append("}\"  width=\"5\" height=\"5\"  />");
            }
        } catch (Exception e) {
            DGlobal.error("campoBooleano() " + e.getLocalizedMessage());
        }
    }

    private void campoDate(Columnas columnas) {
        try {
            escribirFacetColumna(columnas.getNombre());
         //   pt.println(sp(37) + "<h:outputText value=\"#{var." + columnas.getNombre() + "}\" />");
               pt.println(sp(37) + "<h:outputText value=\"#{var." + columnas.getNombre() + "}\" >");
             pt.println(sp(45) + " <f:convertDateTime type=\"date\" pattern=\""+ MySession.getPatronFecha()+"\"/>");
              pt.println(sp(37) + "</h:outputText>");
           
                                String xservices = this.nombreClase+"Data.selected"+Utilidades.convertirLetraMayuscula(this.nombreClase);            
            fieldsJSF.append("\n").append(sp(33)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(xservices).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFecha()).append("\"/>");
            String xdata = this.nombreClase+"Data."+this.nombreClase+"New.";
            fieldsJSFDialogInsert.append("\n").append(sp(33)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("1\" value=\"#{").append(xdata).append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFecha()).append("\"/>");
            //camposDialogQuery.append("\n").append(sp(37)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(this.nombreClase).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFecha()).append("\"/>");
            camposDialogQuery.append("\n").append(sp(37)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(xdata).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFecha()).append("\"/>");
            pt.println(sp(33) + "</p:column>");
        } catch (Exception e) {
            DGlobal.error("campoDate() " + e.getLocalizedMessage());
        }
    }

    private void campoTimestamp(Columnas columnas) {
        try {
             String xdata = this.nombreClase+"Data."+this.nombreClase+"New.";
               String xservices = this.nombreClase+"Data.selected"+Utilidades.convertirLetraMayuscula(this.nombreClase);            
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            fieldsJSF.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            fieldsJSFDialogInsert.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            camposDialogQuery.append("\n").append(sp(41)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            if (tipoCrud.equals("Crear")) {
                pt.println(sp(25) + "<p:calendar  showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}  #{mensajes['msg.nonulo']}\" pattern=\"" + MySession.getPatronFechaHora() + "\"/>");
            } else {
                pt.println(sp(25) + "<p:calendar  showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronFechaHora() + "\"/>");
                fieldsJSF.append("\n").append(sp(25)).append("<p:calendar  showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(xservices).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFechaHora()).append("\"/>");
                fieldsJSFDialogInsert.append("\n").append(sp(25)).append("<p:calendar  showOn=\"button\" id=\"").append(columnas.getNombre()).append("1\" value=\"#{").append(xdata).append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFechaHora()).append("\"/>");
                //camposDialogQuery.append("\n").append(sp(25)).append("<p:calendar  showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(this.nombreClase).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFechaHora()).append("\"/>");
                camposDialogQuery.append("\n").append(sp(25)).append("<p:calendar  showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(xdata).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronFechaHora()).append("\"/>");

            }
        } catch (Exception e) {
            DGlobal.error("campoTimestamp() " + e.getLocalizedMessage());
        }
    }

    private void campoTime(Columnas columnas) {
        try {
            String xdata = this.nombreClase+"Data."+this.nombreClase+"New.";
              String xservices = this.nombreClase+"Data.selected"+Utilidades.convertirLetraMayuscula(this.nombreClase);            
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            fieldsJSF.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            fieldsJSFDialogInsert.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            camposDialogQuery.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            if (tipoCrud.equals("Crear")) {
                pt.println(sp(25) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" required=\"" + columnas.getIsNoNulo() + "\" requiredMessage=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}  #{mensajes['msg.nonulo']}\" pattern=\"" + MySession.getPatronHora() + "\"/>");
            } else {
                pt.println(sp(25) + "<p:calendar showOn=\"button\" id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\"  pattern=\"" + MySession.getPatronHora() + "\"/>");
                fieldsJSF.append("\n").append(sp(25)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(xservices).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronHora()).append("\"/>");
                fieldsJSFDialogInsert.append("\n").append(sp(25)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("1\" value=\"#{").append(xdata).append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronHora()).append("\"/>");
                //camposDialogQuery.append("\n").append(sp(25)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(this.nombreClase).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronHora()).append("\"/>");
                camposDialogQuery.append("\n").append(sp(25)).append("<p:calendar showOn=\"button\" id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(xdata).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\"  pattern=\"").append(MySession.getPatronHora()).append("\"/>");
            }
        } catch (Exception e) {
            DGlobal.error("campoTime() " + e.getLocalizedMessage());
        }
    }

    private void campoImagen(Columnas columnas) {
        try {
            String xdata = this.nombreClase+"Data."+this.nombreClase+"New.";
            pt.println(sp(25) + "<h:outputLabel value=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" for=\"" + columnas.getNombre() + "\" />");
            pt.println(sp(25) + "<h:graphicImage value=\"#{" + columnas.getNombre() + "}\"  width=\"5\" height=\"5\"></h:graphicImage>");
            fieldsJSF.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            fieldsJSF.append("\n").append(sp(25)).append("<h:graphicImage value=\"#{").append(columnas.getNombre()).append("}\"  width=\"5\" height=\"5\"></h:graphicImage>");
            fieldsJSFDialogInsert.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            fieldsJSFDialogInsert.append("\n").append(sp(25)).append("<h:graphicImage value=\"#{").append(xdata).append(columnas.getNombre()).append("}\"  width=\"5\" height=\"5\"></h:graphicImage>");
            camposDialogQuery.append("\n").append(sp(25)).append("<h:outputLabel value=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" for=\"").append(columnas.getNombre()).append("\" />");
            camposDialogQuery.append("\n").append(sp(25)).append("<h:graphicImage value=\"#{").append(columnas.getNombre()).append("}\"  width=\"5\" height=\"5\"></h:graphicImage>");
        } catch (Exception e) {
            DGlobal.error("campoImagen() " + e.getLocalizedMessage());
        }
    }

    private void campoTexto(Columnas columnas, Boolean found) {
        try {
 String xdata = this.nombreClase+"Data."+this.nombreClase+"New.";
            escribirFacetColumna(columnas.getNombre());
            pt.println(sp(37) + "<h:outputText value=\"#{var." + columnas.getNombre() + "}\" />");
            pt.println(sp(33) + "</p:column>");
            //  fieldsJSF.append("\n" + sp(37) + "<h:outputText id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClaseData + ".selected" + Utilidades.convertirLetraMayuscula(this.nombreClase) + "." + columnas.getNombre() + "}\" title=\"#{" + MySession.getVar() + "." + columnas.getNombre() + "}\" />");
            fieldsJSF.append("\n").append(sp(37)).append("<p:inputText   id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(this.nombreClaseData).append(".selected").append(Utilidades.convertirLetraMayuscula(this.nombreClase)).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" />");
            fieldsJSFDialogInsert.append("\n").append(sp(37)).append("<p:inputText id=\"").append(columnas.getNombre()).append("1\" value=\"#{").append(xdata).append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" />");
            camposDialogQuery.append("\n").append(sp(37)).append("<h:outputText id=\"").append(columnas.getNombre()).append("\" value=\"#{").append(this.nombreClaseData).append(".selected").append(Utilidades.convertirLetraMayuscula(this.nombreClase)).append(".").append(columnas.getNombre()).append("}\" title=\"#{").append(MySession.getVar()).append(".").append(columnas.getNombre()).append("}\" />");
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
            pt.println(sp(25) + "<h:inputHidden id=\"" + columnas.getNombre() + "\" value=\"#{" + this.nombreClase + "Data.selected" + Utilidades.convertirLetraMayuscula(this.nombreClase) + "." + columnas.getNombre() + "}\"  />");
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