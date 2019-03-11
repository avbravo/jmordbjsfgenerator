/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.domains;

/**
 *
 * @author avbravo
 */
public class Columnas {
    //@Columna(nombre= "idpais" ,isNoNulo=true, tipo ="Integer" ,tamano =10, digitosDecimales=0, comentario="", is_autoincrementable="NO", isPK=false,isImagen=false, isUrl=false, isVisible=true)

    String nombre;
    Boolean isNoNulo;
    String tipo;
    String mysql;
    Integer tamano;
    Integer digitosDecimales;
    String comentario;
    String is_autoincrementable;
    Boolean isPK;
    Boolean isImagen;
    Boolean isUrl;
    Boolean isVisible;

    public Columnas() {
    }

//    public Columnas(String nombre, Boolean isNoNulo, String tipo, Integer tamano, Integer digitosDecimales, String comentario, String is_autoincrementable, Boolean isPK, Boolean isImagen, Boolean isUrl, Boolean isVisible) {
//        this.nombre = nombre;
//        this.isNoNulo = isNoNulo;
//        this.tipo = tipo;
//        this.tamano = tamano;
//        this.digitosDecimales = digitosDecimales;
//        this.comentario = comentario;
//        this.is_autoincrementable = is_autoincrementable;
//        this.isPK = isPK;
//        this.isImagen = isImagen;
//        this.isUrl = isUrl;
//        this.isVisible = isVisible;
//    }

    public Columnas(String nombre, Boolean isNoNulo, String tipo, String mysql, Integer tamano, Integer digitosDecimales, String comentario, String is_autoincrementable, Boolean isPK, Boolean isImagen, Boolean isUrl, Boolean isVisible) {
        this.nombre = nombre;
        this.isNoNulo = isNoNulo;
        this.tipo = tipo;
        this.mysql = mysql;
        this.tamano = tamano;
        this.digitosDecimales = digitosDecimales;
        this.comentario = comentario;
        this.is_autoincrementable = is_autoincrementable;
        this.isPK = isPK;
        this.isImagen = isImagen;
        this.isUrl = isUrl;
        this.isVisible = isVisible;
    }

    public String getMysql() {
        return mysql;
    }

    public void setMysql(String mysql) {
        this.mysql = mysql;
    }

    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getDigitosDecimales() {
        return digitosDecimales;
    }

    public void setDigitosDecimales(Integer digitosDecimales) {
        this.digitosDecimales = digitosDecimales;
    }

    public Boolean getIsImagen() {
        return isImagen;
    }

    public void setIsImagen(Boolean isImagen) {
        this.isImagen = isImagen;
    }

    public Boolean getIsNoNulo() {
        return isNoNulo;
    }

    public void setIsNoNulo(Boolean isNoNulo) {
        this.isNoNulo = isNoNulo;
    }

    public Boolean getIsPK() {
        return isPK;
    }

    public void setIsPK(Boolean isPK) {
        this.isPK = isPK;
    }

    public Boolean getIsUrl() {
        return isUrl;
    }

    public void setIsUrl(Boolean isUrl) {
        this.isUrl = isUrl;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getIs_autoincrementable() {
        return is_autoincrementable;
    }

    public void setIs_autoincrementable(String is_autoincrementable) {
        this.is_autoincrementable = is_autoincrementable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
