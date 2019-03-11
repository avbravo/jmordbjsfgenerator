/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.analizador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.avbravo.jmoordbjsfgenerator.domains.Columnas;
import org.avbravo.jmoordbjsfgenerator.domains.Relaciones;
import org.avbravo.jmoordbjsfgenerator.utilidades.Utilidades;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class AnalizadorRelaciones {

    List<Columnas> arrayColumnas;
    List<Relaciones> arrayRelaciones;
    private String rutaArchivoBeans;

    public List<Columnas> getArrayColumnas() {
        return arrayColumnas;
    }

    public void setArrayColumnas(List<Columnas> arrayColumnas) {
        this.arrayColumnas = arrayColumnas;
    }

    public List<Relaciones> getArrayRelaciones() {
        return arrayRelaciones;
    }

    public void setArrayRelaciones(List<Relaciones> arrayRelaciones) {
        this.arrayRelaciones = arrayRelaciones;
    }

    public String getRutaArchivoBeans() {
        return rutaArchivoBeans;
    }

    public void setRutaArchivoBeans(String rutaArchivoBeans) {
        this.rutaArchivoBeans = rutaArchivoBeans;
    }

    public AnalizadorRelaciones(String rutaArchivoBeans) {
        this.rutaArchivoBeans = rutaArchivoBeans;
        loadArrayListRelations();
    }

    private void loadArrayListRelations() {
        try {
            arrayColumnas = new ArrayList<Columnas>();
            arrayRelaciones = new ArrayList<Relaciones>();
            BufferedReader brClase = new BufferedReader(new FileReader(rutaArchivoBeans));
            String linea = "";
            while ((linea = brClase.readLine()) != null) {
                if (linea.indexOf("@Relaciones") != - 1) {
                    fragmentarRelations(linea);
                } else {
                    if (linea.indexOf("@Columna") != - 1) {

                        fragmentarColumns(linea);
                    }
                }
            }
        } catch (Exception ex) {
            DGlobal.error("loadArrayListRelations() " + ex.getLocalizedMessage());
        }
    }

    /*
     * CargarArrayListColumnas
     */
    private void fragmentarRelations(String linea) {
        try {
            Relaciones relaciones = new Relaciones();
            relaciones.setClase(Utilidades.claseRelacionada(linea));
            relaciones.setTabla(Utilidades.tablaRelacionada(linea));
            relaciones.setRegla_actualizacion(Utilidades.reglaActualizacionRelacionada(linea));
            relaciones.setRegla_eliminacion(Utilidades.reglaEliminacionRelacionada(linea));
            relaciones.setKey_seq(Utilidades.key_SeqRelacionada(linea));
            relaciones.setNombre_relacion(Utilidades.nombreRelacionRelacionada(linea));
            relaciones.setTipo_relacion(Utilidades.tipoRelacionRelacionada(linea));
            relaciones.setColumnas(Utilidades.columnasRelacionadas(linea));
            relaciones.setIsPadre(Utilidades.padreRelacionada(linea));
            //cuando es una tabla padre
           if(!relaciones.getIsPadre()){
              arrayRelaciones.add(relaciones); 
           }
            
        } catch (Exception ex) {
            DGlobal.error("fragmentarRelations() " + ex.getLocalizedMessage());
        }
    }

    /*
     * CargarArrayListColumns
     */
    private void fragmentarColumns(String linea) {
        try {
            Columnas columnas = new Columnas();
            columnas.setNombre(Utilidades.nombreColumna(linea));
            columnas.setTipo(Utilidades.tipoColumna(linea));
            columnas.setIsNoNulo(Utilidades.isNoNuloColumna(linea));
            columnas.setTamano(Utilidades.tamanoColumna(linea));
            columnas.setMysql(Utilidades.mysqlColumna(linea));
            columnas.setDigitosDecimales(Utilidades.digitosDecimalesColumnas(linea));
            columnas.setIsImagen(Utilidades.isImagenColumna(linea));
            columnas.setComentario(Utilidades.comentarioColumna(linea));
            columnas.setIs_autoincrementable(Utilidades.isAutoIncrementableColumna(linea));
            columnas.setIsPK(Utilidades.isPKColumna(linea));
            columnas.setIsUrl(Utilidades.isURLColumna(linea));
            columnas.setIsVisible(Utilidades.isVisibleColumna(linea));
            arrayColumnas.add(columnas);
        } catch (Exception ex) {
            DGlobal.error("fragmentarColumns() ");
        }
    }
}
