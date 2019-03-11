/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.temas;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.avbravo.jmoordbjsfgenerator.generales.Directorios;
import org.avbravo.jmoordbjsfgenerator.generales.MyArchivo;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.avbravo.jmoordbjsfgenerator.utilidades.Utilidades;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class UpdateCambiadorTemas {

    Temas temas = new Temas();
    TemasController temasController = new TemasController();
    String ruta = Directorios.getDirectorioGenerales() + MySession.getFileSeparator() + "CambiadorTemas.java";
    String nuevoTema;

    public void actualizarTema(String nuevoTema) {
        try {
            this.nuevoTema = nuevoTema;
            String searchTheme = "private String tema=";
            if (Utilidades.encontrarTextoArchivo(ruta, searchTheme)) {
                eliminarTema();
            }
        } catch (Exception ex) {
            DGlobal.error(" actualizarTema() " + ex.getLocalizedMessage());
        }
    }

    /*
     * eliminarTema() localiza el tema y lo elimina del archivo web.xml
     */
    public boolean eliminarTema() {
        try {
            String searchTheme = "private String tema=";
            if (!Utilidades.encontrarTextoArchivo(ruta, searchTheme)) {
                //no hay temas
                return true;
            }
            List<MyArchivo> list = new ArrayList<MyArchivo>();
            Integer fila_tema = -1;

            File file = new File(ruta);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            Integer numero_linea = -1;
            while ((line = reader.readLine()) != null) {
                String texto = line;
                numero_linea++;
                if (line.indexOf("private String tema=") != -1) {
                    fila_tema = numero_linea;
                    texto = "private String tema=\"" + this.nuevoTema + "\";";
                }
                MyArchivo a = new MyArchivo(texto);
                list.add(a);
            }
            Integer contador = 0;
            for (MyArchivo a : list) {
                oldtext += a.getLinea() + "\r\n";
            }
            FileWriter writer = new FileWriter(ruta);
            writer.write(oldtext);
            writer.close();
        } catch (Exception ex) {
            DGlobal.error("eliminarTema() " + ex.getLocalizedMessage());
        }
        return false;
    }
}
