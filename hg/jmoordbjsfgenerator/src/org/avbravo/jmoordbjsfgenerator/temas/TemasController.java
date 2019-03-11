/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.temas;

import java.util.ArrayList;
import java.util.List;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class TemasController {

    List<Temas> listTemas = new ArrayList<Temas>();

    public TemasController() {
        
        cargarTema("afterdark");
        cargarTema("afternoon");
        cargarTema("afterwork");
        cargarTema("aristo");
        cargarTema("black-tie");
        cargarTema("blitzer");
        cargarTema("bluesky");
        cargarTema("casablanca");
        cargarTema("cruze");
        cargarTema("cupertino");
        cargarTema("dark-hive");
        cargarTema("dot-luv");
        cargarTema("eggplant");
        cargarTema("excite-bike");
        cargarTema("flick");
        cargarTema("glass-x");
        cargarTema("home");
        cargarTema("hot-sneaks");
        cargarTema("humanity");
        cargarTema("le-frog");
        cargarTema("midnight");
        cargarTema("mint-choc");
        cargarTema("none");
        cargarTema("overcast");
        cargarTema("pepper-grinder");
        cargarTema("redmond");
        cargarTema("rocket");
        cargarTema("sam");
        cargarTema("smoothness");
        cargarTema("south-street");
        cargarTema("start");
        cargarTema("sunny");
        cargarTema("swanky-purse");
        cargarTema("trontastic");
        cargarTema("ui-darkness");
        cargarTema("ui-lightness");
        cargarTema("vader");
    }

    public List<Temas> getListTemas() {

        return listTemas;
    }

    public void setListTemas(List<Temas> listTemas) {
        this.listTemas = listTemas;
    }

    private void cargarTema(String tema) {
        try {
            Temas t = new Temas(tema);
            listTemas.add(t);
        } catch (Exception ex) {
            DGlobal.error(ex.getLocalizedMessage());
        }
    }
}
