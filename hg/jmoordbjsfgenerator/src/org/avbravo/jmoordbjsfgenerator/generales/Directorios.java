/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.generales;

import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class Directorios {

    static String nombrePaquete;
    static String paquete;
    static String paqueteSistema;
    static String directorioBeans;
    static String directorioController;
    static String directorioMenu;
    static String directorioGenerales;
    static String directorioPersistencia;
    static String directorioPropiedades;
    static String directorioAnotaciones;
    static String nombreArchivoPropiedades;
    static String nombreArchivoPropiedadesSinExtension;
    static String nombreArchivoConfiguracionDesktop;
    static String rutaArchivoPlantilla;
    static String nombreArchivoPlantilla;
    static String rutaArchivoPropiedades;
    static String rutaMenu;

    public Directorios() {
    }

    public static void asignarDirectorios() {
        try {
            nombrePaquete = MySession.getNombreProyecto().toLowerCase();
            setNombrePaquete(nombrePaquete);
            paquete = "org." + nombrePaquete;
            String ruta = MySession.getSrcJava() + MySession.getFileSeparator() + "org" + MySession.getFileSeparator() + Directorios.getNombrePaquete() + MySession.getFileSeparator();
            directorioBeans = ruta + "beans";
            directorioController = ruta + "controller";
            directorioMenu = MySession.getWeb();
            directorioGenerales = ruta + "generales";

            if (MySession.getTipoProyecto().equals("NbMavenProjectImpl") && MySession.isEsProyectoWeb()) {
                String rutaMaven = MySession.getMavenDirectorio() + MySession.getFileSeparator() + "org" + MySession.getFileSeparator() + MySession.getNombreProyecto().toLowerCase() + MySession.getFileSeparator();

                setDirectorioPropiedades(rutaMaven + "properties");
            } else {
                directorioPropiedades = ruta + "properties";
                Directorios.setDirectorioPropiedades(directorioPropiedades);
            }


            /*
             * web-inf
             */
            String directorioWebInf = MySession.getWeb() + MySession.getFileSeparator() + "WEB-INF";

            /*
             * agregarlos al sesion
             */
            setPaquete(paquete);
            setDirectorioBeans(directorioBeans);
            setDirectorioController(directorioController);
            setDirectorioMenu(directorioMenu);
            setDirectorioGenerales(directorioGenerales);
            MySession.setDirectorioWebInf(directorioWebInf);

        } catch (Exception ex) {
            DGlobal.error("asignarDirectorios() " + ex.getLocalizedMessage());
        }
    }

    public static String getRutaMenu() {
        return rutaMenu;
    }

    public static void setRutaMenu(String rutaMenu) {
        Directorios.rutaMenu = rutaMenu;
    }

    public static String getRutaArchivoPropiedades() {
        return rutaArchivoPropiedades;
    }

    public static void setRutaArchivoPropiedades(String rutaArchivoPropiedades) {
        Directorios.rutaArchivoPropiedades = rutaArchivoPropiedades;
    }

    public static String getNombreArchivoPlantilla() {
        return nombreArchivoPlantilla;
    }

    public static void setNombreArchivoPlantilla(String nombreArchivoPlantilla) {
        Directorios.nombreArchivoPlantilla = nombreArchivoPlantilla;
    }

    public static String getRutaArchivoPlantilla() {
        return rutaArchivoPlantilla;
    }

    public static void setRutaArchivoPlantilla(String rutaArchivoPlantilla) {
        Directorios.rutaArchivoPlantilla = rutaArchivoPlantilla;
    }

    public static String getDirectorioAnotaciones() {
        return directorioAnotaciones;
    }

    public static void setDirectorioAnotaciones(String directorioAnotaciones) {
        Directorios.directorioAnotaciones = directorioAnotaciones;
    }

    public static String getDirectorioBeans() {
        return directorioBeans;
    }

    public static void setDirectorioBeans(String directorioBeans) {
        Directorios.directorioBeans = directorioBeans;
    }

    public static String getDirectorioController() {
        return directorioController;
    }

    public static void setDirectorioController(String directorioController) {
        Directorios.directorioController = directorioController;
    }

    public static String getDirectorioGenerales() {
        return directorioGenerales;
    }

    public static void setDirectorioGenerales(String directorioGenerales) {
        Directorios.directorioGenerales = directorioGenerales;
    }

    public static String getDirectorioMenu() {
        return directorioMenu;
    }

    public static void setDirectorioMenu(String directorioMenu) {
        Directorios.directorioMenu = directorioMenu;
    }

    public static String getDirectorioPersistencia() {
        return directorioPersistencia;
    }

    public static void setDirectorioPersistencia(String directorioPersistencia) {
        Directorios.directorioPersistencia = directorioPersistencia;
    }

    public static String getDirectorioPropiedades() {
        return directorioPropiedades;
    }

    public static void setDirectorioPropiedades(String directorioPropiedades) {
        Directorios.directorioPropiedades = directorioPropiedades;
    }

    public static String getNombreArchivoConfiguracionDesktop() {
        return nombreArchivoConfiguracionDesktop;
    }

    public static void setNombreArchivoConfiguracionDesktop(String nombreArchivoConfiguracionDesktop) {
        Directorios.nombreArchivoConfiguracionDesktop = nombreArchivoConfiguracionDesktop;
    }

    public static String getNombreArchivoPropiedades() {
        return nombreArchivoPropiedades;
    }

    public static void setNombreArchivoPropiedades(String nombreArchivoPropiedades) {
        Directorios.nombreArchivoPropiedades = nombreArchivoPropiedades;
    }

    public static String getNombreArchivoPropiedadesSinExtension() {
        return nombreArchivoPropiedadesSinExtension;
    }

    public static void setNombreArchivoPropiedadesSinExtension(String nombreArchivoPropiedadesSinExtension) {
        Directorios.nombreArchivoPropiedadesSinExtension = nombreArchivoPropiedadesSinExtension;
    }

    public static String getNombrePaquete() {
        return nombrePaquete;
    }

    public static void setNombrePaquete(String nombrePaquete) {
        Directorios.nombrePaquete = nombrePaquete;
    }

    public static String getPaquete() {
        return paquete;
    }

    public static void setPaquete(String paquete) {
        Directorios.paquete = paquete;
    }

    public static String getPaqueteSistema() {
        return paqueteSistema;
    }

    public static void setPaqueteSistema(String paqueteSistema) {
        Directorios.paqueteSistema = paqueteSistema;
    }
}
