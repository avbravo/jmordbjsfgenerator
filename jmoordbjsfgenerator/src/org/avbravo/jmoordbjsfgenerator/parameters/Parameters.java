/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.parameters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.avbravo.jmoordbjsfgenerator.DGlobal;
/**
 *
 * @author avbravo
 */
public class Parameters {

    FileWriter fw;
    BufferedWriter bw;
    PrintWriter out;

    public Parameters(String rutaArchivo) {

        try {
            fw = new FileWriter(rutaArchivo);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);

            out.println("-----------------------------------------");
            out.println("* @Parameters ");
            out.println("-----------------------------------------");
            out.println("");
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            out.println("Date: " + formateador.format(ahora));
            Calendar calendario = new GregorianCalendar();
            Integer hora = calendario.get(Calendar.HOUR_OF_DAY);
            Integer minutos = calendario.get(Calendar.MINUTE);
            Integer segundos = calendario.get(Calendar.SECOND);
            out.println("Time: " + hora + ":" + minutos + ":" + segundos);
            out.println("");
            out.println("* @Page Patron");
            out.println("-----------------------------------------");
            out.println("patronFecha=" + MySession.getPatronFecha());
            out.println("patronFechaHora=" + MySession.getPatronFechaHora());
            out.println("patronHora=" + MySession.getPatronHora());

            out.println("-----------------------------------------");
            out.println("* @Page Facelets");
            out.println("-----------------------------------------");
            out.println("facelesTemplate=" + MySession.getFaceletsTemplate());
            out.println("-----------------------------------------");
            out.println("* @Page DataTable");
            out.println("-----------------------------------------");
            out.println("rowsDataTable=" + MySession.getRowsDataTable());
            out.println("typeRelationsDataTable=" + MySession.getTypeRelationsDataTable());
            out.println("tableScrollable=" + MySession.getTableScrollable());
//            out.println("ajaxRowSelect=" + MySession.getAjaxRowSelect());
//            out.println("showDialog=" + MySession.getShowDialog());
//            out.println("tableButtonQuery=" + MySession.getTableButtonQuery());
//            out.println("tableButtonEdit=" + MySession.getTableButtonEdit());
//            out.println("tableButtonDelete=" + MySession.getTableButtonDelete());
//            out.println("pageListType=" + MySession.getPageListType());
            out.println("*-------------@Dialog--------------------");
            out.println("dialogWidth=" + MySession.getDialogWidth());
            out.println("dialogWidthRelation=" + MySession.getDialogWidthRelation());
            out.println("dialogDinamyc=" + MySession.getDialogDinamyc());
            out.println("dialogCustomized=" + MySession.getDialogCustomized());
            out.println("dialogHeight=" + MySession.getDialogHeight());
            out.println("-----------------------------------------");
            out.println("* @Page Primefaces");
            out.println("-----------------------------------------");
            out.println("primefacesVersion=" + MySession.getPrimefacesVersion());
            out.println("temaPrimefaces=" + MySession.getTemaPrimefaces());
            out.println("tipoMenu=" + MySession.getTipoMenu());
            out.println("buttonTextIcon=" + MySession.getButtonTextIcon());
            out.println("tipoMensajes=" + MySession.getTipoMensajes());
//            out.println("showDialog=" + MySession.getShowDialog());
//            out.println("pageListType=" + MySession.getPageListType());
            out.println("environment=" + MySession.getEnvironment());
            out.println("growl=" + MySession.getGrowl());
            out.println("messages=" + MySession.getMessages());
            out.println("growlandmessages=" + MySession.getGrowlAndMessages());
            out.println("growSticker=" + MySession.getGrowSticker());
            out.println("growlLife=" + MySession.getGrowlLife());
            out.println("growlsSeconds=" + MySession.getGrowlsSeconds());
           out.println("generateGrowlnPage="+MySession.getGenerateGrowlnPage());
            out.println("generateGrowlnTemplate="+MySession.getGenerateGrowlnTemplate());
            out.close();
            fw.close();
            bw.close();


        } catch (Exception ex) {
            DGlobal.error("Parameters() " + ex.getLocalizedMessage());
        }
    }
}
