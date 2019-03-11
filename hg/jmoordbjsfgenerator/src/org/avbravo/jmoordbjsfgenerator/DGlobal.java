/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator;

import javax.swing.table.DefaultTableModel;
import org.netbeans.api.project.Project;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author avbravo
 */
public class DGlobal {

    static Project project;
    static String template;
    static Boolean replaceTemplate;
    static Boolean replaceIndex;
    static Boolean replaceMenu;
    static DefaultTableModel modelo;
    static String temaInicial;
    static Boolean esPrimeraEjecucion;

    public DGlobal() {
    }

    public static Boolean getEsPrimeraEjecucion() {
        return esPrimeraEjecucion;
    }

    public static void setEsPrimeraEjecucion(Boolean esPrimeraEjecucion) {
        DGlobal.esPrimeraEjecucion = esPrimeraEjecucion;
    }

    public static String getTemaInicial() {
        return temaInicial;
    }

    public static void setTemaInicial(String temaInicial) {
        DGlobal.temaInicial = temaInicial;
    }

    
    public static DefaultTableModel getModelo() {
        return modelo;
    }

    public static void setModelo(DefaultTableModel modelo) {
        DGlobal.modelo = modelo;
    }

    public static Boolean getReplaceTemplate() {
        return replaceTemplate;
    }

    public static void setReplaceTemplate(Boolean replaceTemplate) {
        DGlobal.replaceTemplate = replaceTemplate;
    }

    public static Boolean getReplaceIndex() {
        return replaceIndex;
    }

    public static void setReplaceIndex(Boolean replaceIndex) {
        DGlobal.replaceIndex = replaceIndex;
    }

    public static Boolean getReplaceMenu() {
        return replaceMenu;
    }

    public static void setReplaceMenu(Boolean replaceMenu) {
        DGlobal.replaceMenu = replaceMenu;
    }

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        DGlobal.project = project;
    }

    public static String getTemplate() {
        return template;
    }

    public static void setTemplate(String template) {
        DGlobal.template = template;
    }

    public static void error(String msg) {
        NotifyDescriptor nd;
        try {
            nd = new NotifyDescriptor.Message(msg, NotifyDescriptor.Message.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        } catch (Exception ex) {
            nd = new NotifyDescriptor.Message("Error() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
    }

    public static void advertencia(String msg) {
        NotifyDescriptor nd;
        try {
            nd = new NotifyDescriptor.Message(msg, NotifyDescriptor.Message.WARNING_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        } catch (Exception ex) {
            nd = new NotifyDescriptor.Message("Advertencia() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.WARNING_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
    }

    public static void informacion(String msg) {
        NotifyDescriptor nd;
        try {
            nd = new NotifyDescriptor.Message(msg, NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        } catch (Exception ex) {
            nd = new NotifyDescriptor.Message("Advertencia() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
    }
    /*
     * devuelve espacios para colocar los componentes
     */

    public static String sp(Integer n) {
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

    public static String cortarTexto(String texto) {
        try {

            Integer pos = texto.indexOf("=");
            if (pos != -1) {
                return texto.substring(pos + 1, texto.length());
            }

        } catch (Exception e) {
            DGlobal.error("cortarTexto()" + e.getLocalizedMessage());
        }
        return "";
    }
}
