/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.generales;

import org.avbravo.jmoordbjsfgenerator.DGlobal;
import org.netbeans.api.project.Project;

/**
 *
 * @author avbravo
 */
public class MySession {

    static String SistemaOperativo;
    static String nombreProyecto;
    static String tipoProyecto;
    static String src;
    static String srcJava;
    static String web;
    static String directorioWebInf;
    static boolean esProyectoWeb;
    static String fileSeparator;
    static String var;
    static String primefacesVersion;
    static String faceletsTemplate;
    static String temaPrimefaces;
    static Project myProject;
    static String patronFecha = "dd/MM/yyyy";
    static String patronFechaHora = "dd/MM/yyyy HH:mm:ss";
    static String patronHora = "HH:mm:ss";
    static String mavenDirectorio;
    static String tipoMenu;
    static String tipoMensajes;
    // para saber como mostrar el tipo de relaciones de las datatable
    static String typeRelationsDataTable;
    static Integer rowsDataTable;
    public static Boolean existeTemplate = false;
    public static Boolean sobreEscribirTemplate = false;
    public static Boolean existeIndex = false;
    public static Boolean sobreEscribirIndex = false;
    public static Boolean sobreEscribirMenu = false;
    public static Boolean botonTextoIcono = true;
    public static Boolean botonTextoOnly = false;
    public static Boolean botonIconoOnly = false;
    public static Integer dialogWidth;
    public static Integer dialogWidthRelation;
    public static Boolean dialogDinamyc = true;
    public static Integer dialogHeight;
    public static Boolean dialogCustomized;

    public static Boolean tableScrollable = false;
    public static String buttonTextIcon;
    public static String environment;
    public static Boolean growl;
    public static Boolean messages;
    public static Boolean growlAndMessages;
    public static Boolean growSticker;
    public static Boolean growlLife;
    public static Integer growlsSeconds;
    /*
     * indica en donde se generara el grow si se desea en cada pagina o en el template
     * recordar que si es en el template no es recomendables mostrar message alli 
     * solo growl.
     */
    public static Boolean generateGrowlnPage;
    public static Boolean generateGrowlnTemplate;

    public static Boolean getGenerateGrowlnPage() {
        return generateGrowlnPage;
    }

    public static void setGenerateGrowlnPage(Boolean generateGrowlnPage) {
        MySession.generateGrowlnPage = generateGrowlnPage;
    }

    public static Boolean getGenerateGrowlnTemplate() {
        return generateGrowlnTemplate;
    }

    public static void setGenerateGrowlnTemplate(Boolean generateGrowlnTemplate) {
        MySession.generateGrowlnTemplate = generateGrowlnTemplate;
    }

    
    
    public static Boolean getSobreEscribirMenu() {
        return sobreEscribirMenu;
    }

    public static void setSobreEscribirMenu(Boolean sobreEscribirMenu) {
        MySession.sobreEscribirMenu = sobreEscribirMenu;
    }

    public static Boolean getGrowSticker() {
        return growSticker;
    }

    public static void setGrowSticker(Boolean growSticker) {
        MySession.growSticker = growSticker;
    }

    public static Boolean getGrowlLife() {
        return growlLife;
    }

    public static void setGrowlLife(Boolean growlLife) {
        MySession.growlLife = growlLife;
    }

    public static Integer getGrowlsSeconds() {
        return growlsSeconds;
    }

    public static void setGrowlsSeconds(Integer growlsSeconds) {
        MySession.growlsSeconds = growlsSeconds;
    }

    public static Boolean getGrowl() {
        return growl;
    }

    public static void setGrowl(Boolean growl) {
        MySession.growl = growl;
    }

    public static Boolean getMessages() {
        return messages;
    }

    public static void setMessages(Boolean messages) {
        MySession.messages = messages;
    }

    public static Boolean getGrowlAndMessages() {
        return growlAndMessages;
    }

    public static void setGrowlAndMessages(Boolean growlAndMessages) {
        MySession.growlAndMessages = growlAndMessages;
    }

    public static Boolean getDialogCustomized() {
        return dialogCustomized;
    }

    public static void setDialogCustomized(Boolean dialogCustomized) {
        MySession.dialogCustomized = dialogCustomized;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static void setEnvironment(String environment) {
        MySession.environment = environment;
    }

    public static String getButtonTextIcon() {
        return buttonTextIcon;
    }

    public static void setButtonTextIcon(String buttonTextIcon) {
        MySession.buttonTextIcon = buttonTextIcon;
    }

    public static String getTipoMenu() {
        return tipoMenu;
    }

    public static void setTipoMenu(String tipoMenu) {
        MySession.tipoMenu = tipoMenu;
    }

    public static Boolean getTableScrollable() {
        return tableScrollable;
    }

    public static void setTableScrollable(Boolean tableScrollable) {
        MySession.tableScrollable = tableScrollable;
    }



    public static String getTypeRelationsDataTable() {
        return typeRelationsDataTable;
    }

    public static void setTypeRelationsDataTable(String typeRelationsDataTable) {
        MySession.typeRelationsDataTable = typeRelationsDataTable;
    }

    public static Boolean getDialogDinamyc() {
        return dialogDinamyc;
    }

    public static void setDialogDinamyc(Boolean dialogDinamyc) {
        MySession.dialogDinamyc = dialogDinamyc;
    }

    public static Integer getDialogHeight() {
        return dialogHeight;
    }

    public static void setDialogHeight(Integer dialogHeight) {
        MySession.dialogHeight = dialogHeight;
    }

    public static Integer getDialogWidth() {
        return dialogWidth;
    }

    public static void setDialogWidth(Integer dialogWidth) {
        MySession.dialogWidth = dialogWidth;
    }

    public static Integer getDialogWidthRelation() {
        return dialogWidthRelation;
    }

    public static void setDialogWidthRelation(Integer dialogWidthRelation) {
        MySession.dialogWidthRelation = dialogWidthRelation;
    }

    public static Boolean getBotonTextoIcono() {
        return botonTextoIcono;
    }

    public static void setBotonTextoIcono(Boolean botonTextoIcono) {
        MySession.botonTextoIcono = botonTextoIcono;
    }

    public static Boolean getBotonTextoOnly() {
        return botonTextoOnly;
    }

    public static void setBotonTextoOnly(Boolean botonTextoOnly) {
        MySession.botonTextoOnly = botonTextoOnly;
    }

    public static Boolean getBotonIconoOnly() {
        return botonIconoOnly;
    }

    public static void setBotonIconoOnly(Boolean botonIconoOnly) {
        MySession.botonIconoOnly = botonIconoOnly;
    }

    public static Boolean getExisteIndex() {
        return existeIndex;
    }

    public static void setExisteIndex(Boolean existeIndex) {
        MySession.existeIndex = existeIndex;
    }

    public static Boolean getSobreEscribirIndex() {
        return sobreEscribirIndex;
    }

    public static void setSobreEscribirIndex(Boolean sobreEscribirIndex) {
        MySession.sobreEscribirIndex = sobreEscribirIndex;
    }

    public static Boolean getExisteTemplate() {
        return existeTemplate;
    }

    public static void setExisteTemplate(Boolean existeTemplate) {
        MySession.existeTemplate = existeTemplate;
    }

    public static Boolean getSobreEscribirTemplate() {
        return sobreEscribirTemplate;
    }

    public static void setSobreEscribirTemplate(Boolean sobreEscribirTemplate) {
        MySession.sobreEscribirTemplate = sobreEscribirTemplate;
    }

    public static Integer getRowsDataTable() {
        return rowsDataTable;
    }

    public static void setRowsDataTable(Integer rowsDataTable) {
        MySession.rowsDataTable = rowsDataTable;
    }

    public static String getTipoMensajes() {
        return tipoMensajes;
    }

    public static void setTipoMensajes(String tipoMensajes) {
        MySession.tipoMensajes = tipoMensajes;
    }

    public static Project getMyProject() {
        return myProject;
    }

    public static void setMyProject(Project myProject) {
        MySession.myProject = myProject;
    }

    public static String getMavenDirectorio() {
        return mavenDirectorio;
    }

    public static void setMavenDirectorio(String mavenDirectorio) {
        MySession.mavenDirectorio = mavenDirectorio;
    }

    public static String getPatronFecha() {
        return patronFecha;
    }

    public static String getPatronHora() {
        return patronHora;
    }

    public static void setPatronHora(String patronHora) {
        MySession.patronHora = patronHora;
    }

    public static void setPatronFecha(String patronFecha) {
        MySession.patronFecha = patronFecha;
    }

    public static String getPatronFechaHora() {
        return patronFechaHora;
    }

    public static void setPatronFechaHora(String patronFechaHora) {
        MySession.patronFechaHora = patronFechaHora;
    }

    public static String getSistemaOperativo() {
        return SistemaOperativo;
    }

    public static void setSistemaOperativo(String SistemaOperativo) {
        MySession.SistemaOperativo = SistemaOperativo;
    }

    public static String getNombreProyecto() {
        return nombreProyecto;
    }

    public static void setNombreProyecto(String nombreProyecto) {
        MySession.nombreProyecto = nombreProyecto;
    }

    public static String getTipoProyecto() {
        return tipoProyecto;
    }

    public static void setTipoProyecto(String tipoProyecto) {
        MySession.tipoProyecto = tipoProyecto;
    }

    public static String getSrc() {
        return src;
    }

    public static void setSrc(String src) {
        MySession.src = src;
    }

    public static String getSrcJava() {
        return srcJava;
    }

    public static void setSrcJava(String srcJava) {
        MySession.srcJava = srcJava;
    }

    public static String getWeb() {
        return web;
    }

    public static void setWeb(String web) {
        MySession.web = web;
    }

    public static String getDirectorioWebInf() {
        return directorioWebInf;
    }

    public static void setDirectorioWebInf(String directorioWebInf) {
        MySession.directorioWebInf = directorioWebInf;
    }

    public static boolean isEsProyectoWeb() {
        return esProyectoWeb;
    }

    public static void setEsProyectoWeb(boolean esProyectoWeb) {
        MySession.esProyectoWeb = esProyectoWeb;
    }

    public static String getFileSeparator() {
        return System.getProperties().getProperty("file.separator");
    }

    public static void setFileSeparator(String fileSeparator) {
        MySession.fileSeparator = fileSeparator;
    }

    public static String getVar() {
        return var;
    }

    public static void setVar(String var) {
        MySession.var = var;
    }

    public static String getPrimefacesVersion() {
        return primefacesVersion;
    }

    public static void setPrimefacesVersion(String primefacesVersion) {
        MySession.primefacesVersion = primefacesVersion;
    }

    public static String getFaceletsTemplate() {
        return faceletsTemplate;
    }

    public static void setFaceletsTemplate(String faceletsTemplate) {
        MySession.faceletsTemplate = faceletsTemplate;
    }

    public static String getTemaPrimefaces() {
        return temaPrimefaces;
    }

    public static void setTemaPrimefaces(String temaPrimefaces) {
        MySession.temaPrimefaces = temaPrimefaces;
    }

    public static String crudEnglishToSpanish(String lcrud) {
        String crudSpanish = "";
        lcrud = lcrud.toLowerCase();
        if (lcrud.equals("insert")) {
            crudSpanish = "Crear";
        } else {
            if (lcrud.equals("edit")) {
                crudSpanish = "Editar";
            } else {
                if (lcrud.equals("list")) {
                    crudSpanish = "Listar";
                } else {
                    if (lcrud.equals("query")) {
                        crudSpanish = "Consultar";
                    } else {
                        if (lcrud.equals("delete")) {
                            crudSpanish = "Eliminar";
                        }
                    }
                }
            }
        }
        return crudSpanish;
    }


    /*
     * devuelve espacios para colocar los componentes
     */
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
