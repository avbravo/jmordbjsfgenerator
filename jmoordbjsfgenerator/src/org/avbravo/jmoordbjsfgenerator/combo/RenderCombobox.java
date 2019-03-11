/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.combo;

import java.awt.Component;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author avbravo
 */
public class RenderCombobox extends JLabel implements ListCellRenderer {

    /**
     * Para guardar los iconos por cada item. El item es la clave y el valor es
     * el icono.
     */
    Hashtable<Object, ImageIcon> icono = null;

    /**
     * Construye el Hashtable con los iconos seleccionados, asociándolos a los
     * items que tendrá el JComboBox "uno", "dos", "tres" y "cuatro"
     */
    public RenderCombobox() {
        icono = new Hashtable<Object, ImageIcon>();

        icono.put("izquierda_centro_derecho", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/izquierda_centro_derecho.png")));
        icono.put("izquierda_derecha", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/izquierda_derecha.png")));
        icono.put("superior_centro", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/superior_centro.png")));
        icono.put("superior_centro_abajo", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/superior_centro_abajo.png")));
        icono.put("superior_izquierda_centro_derecha_abajo", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/superior_izquierda_centro_derecha_abajo.png")));
        icono.put("superior_izquierda_derecha", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/superior_izquierda_derecha.png")));
        icono.put("superior_izquierda_derecha_abajo", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/superior_izquierda_derecha_abajo.png")));
        icono.put("superior_izquierdo_centro_derecha", new ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/superior_izquierdo_centro_derecha.png")));




    }

    /**
     * En función del value que se pasa (el item del JComboBox), se pone el
     * icono y se devuelve el JLabel.
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        if (icono.get(value) != null) {
            setIcon(icono.get(value));
        } else {
            setIcon(null);
        }
        return this;
    }
}
