/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.domains;

import java.util.ArrayList;

/**
 *
 * @author avbravo
 */
public class Relaciones {
    //@Relaciones(clase ="Gruposusuarios", tabla = "gruposusuarios" ,columna = "idgrupousuario,idpais" , regla_actualizacion="restringuido", regla_eliminacion="restringuido", key_seq=1, nombre_relacion="new_fk_SDSDAD", tipo_relacion="muchos a uno" )
    String clase;
    String tabla;
    ArrayList<String> columnas;
    String regla_actualizacion;
    String regla_eliminacion;
    String key_seq;
    String nombre_relacion;
    String tipo_relacion;
    Boolean isPadre;

    public Relaciones() {
    }

    public Relaciones(String clase, String tabla, ArrayList<String> columnas, String regla_actualizacion, String regla_eliminacion, String key_seq, String nombre_relacion, String tipo_relacion,Boolean isPadre) {
        this.clase = clase;
        this.tabla = tabla;
        this.columnas = columnas;
        this.regla_actualizacion = regla_actualizacion;
        this.regla_eliminacion = regla_eliminacion;
        this.key_seq = key_seq;
        this.nombre_relacion = nombre_relacion;
        this.tipo_relacion = tipo_relacion;
        this.isPadre = isPadre;
    }

    public Boolean getIsPadre() {
        return isPadre;
    }

    public void setIsPadre(Boolean isPadre) {
        this.isPadre = isPadre;
    }

    
    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList<String> columnas) {
        this.columnas = columnas;
    }

    public String getKey_seq() {
        return key_seq;
    }

    public void setKey_seq(String key_seq) {
        this.key_seq = key_seq;
    }

    public String getNombre_relacion() {
        return nombre_relacion;
    }

    public void setNombre_relacion(String nombre_relacion) {
        this.nombre_relacion = nombre_relacion;
    }

    public String getRegla_actualizacion() {
        return regla_actualizacion;
    }

    public void setRegla_actualizacion(String regla_actualizacion) {
        this.regla_actualizacion = regla_actualizacion;
    }

    public String getRegla_eliminacion() {
        return regla_eliminacion;
    }

    public void setRegla_eliminacion(String regla_eliminacion) {
        this.regla_eliminacion = regla_eliminacion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getTipo_relacion() {
        return tipo_relacion;
    }

    public void setTipo_relacion(String tipo_relacion) {
        this.tipo_relacion = tipo_relacion;
    }

   
}
