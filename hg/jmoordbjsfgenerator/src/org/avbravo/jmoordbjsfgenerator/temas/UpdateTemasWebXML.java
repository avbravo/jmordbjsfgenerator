/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.temas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.avbravo.jmoordbjsfgenerator.generales.MyArchivo;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.avbravo.jmoordbjsfgenerator.utilidades.Utilidades;
import org.avbravo.jmoordbjsfgenerator.DGlobal;


/**
 *
 * @author avbravo
 */
public class UpdateTemasWebXML {

    Temas temas = new Temas();
    TemasController temasController = new TemasController();
    String web = MySession.getDirectorioWebInf() + MySession.getFileSeparator() + "web.xml";

    public void actualizarTemaWEBXML(String nuevoTema) {
        try {
            /*
             * verificar si tenia algun tema anterior si no tenia insertar el
             * nuevo, de otro modo actualizarlo
             */
            if (nuevoTema.equals("none")) {
                String searchTheme = "<param-name>primefaces.THEME</param-name>";
                if (Utilidades.encontrarTextoArchivo(web, searchTheme)) {
                    eliminarTema();
                }
            }
            String searchTheme = "<param-name>primefaces.THEME</param-name>";
            if (!Utilidades.encontrarTextoArchivo(web, searchTheme)) {
                insertTema();
            } else {
                /*
                 * elimino el tema anterior inserto el nuevo tema
                 */
                eliminarTema();
                insertTema();
            }
        } catch (Exception ex) {
            DGlobal.error("actualizarTemaWEBXML() " + ex.getLocalizedMessage());
        }
    }

    public void insertarTemaVacio() {
        try {
            /*
             * verificar si tenia algun tema anterior si no tenia insertar el
             * nuevo, de otro modo actualizarlo
             */
            String searchTheme = "<param-name>primefaces.THEME</param-name>";
            if (!Utilidades.encontrarTextoArchivo(web, searchTheme)) {
                insertarTemaNone();
            } else {
                /*
                 * elimino el tema anterior inserto el nuevo tema
                 */
                eliminarTema();
                insertarTemaNone();
            }
        } catch (Exception ex) {
            DGlobal.error("insertarTemaVacio() " + ex.getLocalizedMessage());
        }
    }

    private boolean insertTema() {
        try {
            StringBuilder sb = new StringBuilder("");
            sb.append("<context-param>");
            sb.append("\n");
            sb.append("<param-name>primefaces.THEME</param-name>");
            sb.append("\n");
            sb.append("<param-value>#{cambiadorTemas.tema}</param-value>");
            sb.append("\n");
            sb.append("</context-param>");
            Utilidades.insertarTextoArchivo(web, "</web-app>", sb.toString(), true);
            return true;
        } catch (Exception e) {
            DGlobal.error(e.getLocalizedMessage());
        }
        return false;
    }
    /*
     * inserta tema none es decir ningun tema
     */

    public boolean insertarTemaNone() {
        try {
            StringBuilder sb = new StringBuilder("");
            sb.append("<context-param>");
            sb.append("\n");
            sb.append(" <param-name>primefaces.THEME</param-name>");
            sb.append("\n");
            sb.append(" <param-value>#{cambiadorTemas.tema}</param-value>");
            sb.append("\n");
            sb.append("</context-param>");
            Utilidades.insertarTextoArchivo(web, "</web-app>", sb.toString(), true);
            return true;

        } catch (Exception e) {
            DGlobal.error(e.getLocalizedMessage());
        }
        return false;
    }

    /*
     * eliminarTema() localiza el tema y lo elimina del archivo web.xml
     */
    public boolean eliminarTema() {
        try {
            String searchTheme = "<param-name>primefaces.THEME</param-name>";
            if (!Utilidades.encontrarTextoArchivo(web, searchTheme)) {
                //no hay temas
                return true;
            }
            List<MyArchivo> list = new ArrayList<MyArchivo>();
            Integer fila_tema = -1;

            File file = new File(web);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            Integer numero_linea = -1;
            while ((line = reader.readLine()) != null) {
                numero_linea++;
                if (line.indexOf("<param-name>primefaces.THEME</param-name>") != -1) {
                    fila_tema = numero_linea;
                }
                MyArchivo a = new MyArchivo(line);
                list.add(a);
            }
            Integer contador = 0;
            for (MyArchivo a : list) {
                contador++;
                if (contador >= fila_tema && contador <= fila_tema + 3) {
                } else {
                    oldtext += a.getLinea() + "\r\n";
                }
            }
            FileWriter writer = new FileWriter(web);
            writer.write(oldtext);
            writer.close();
        } catch (Exception ex) {
              DGlobal.error("eliminarTema() " + ex.getLocalizedMessage());
        }
        return false;
    }
}
