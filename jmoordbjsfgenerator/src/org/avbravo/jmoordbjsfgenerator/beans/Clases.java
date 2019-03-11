/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.beans;

/**
 *
 * @author avbravo
 */
public class Clases {
    String clase;
    Boolean crear;
    Boolean editar;
    Boolean listar;

    public Clases() {
    }

    public Clases(String clase, Boolean crear, Boolean editar, Boolean listar) {
        this.clase = clase;
        this.crear = crear;
        this.editar = editar;
        this.listar = listar;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Boolean getCrear() {
        return crear;
    }

    public void setCrear(Boolean crear) {
        this.crear = crear;
    }

    public Boolean getEditar() {
        return editar;
    }

    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

    public Boolean getListar() {
        return listar;
    }

    public void setListar(Boolean listar) {
        this.listar = listar;
    }
    
}
