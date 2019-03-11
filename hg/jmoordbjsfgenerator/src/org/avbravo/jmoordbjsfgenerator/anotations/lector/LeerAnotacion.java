/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.anotations.lector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.avbravo.jmoordbjsfgenerator.generales.Directorios;

/**
 *
 * @author avbravo
 */
public class LeerAnotacion {
    /*
     * dos formas de llamarlo
     *  Class aClass = Privilegios.class;
     * o
     *    Lector l = new Lector();
     l.LeerClaseDirectamente();
     l.LeerParametroClaseString("org.leeranotacionesdataclassg.beans.Privilegios");

     */

    public void LeerClaseDirectamente() {
        try {
            Class aClass = Directorios.class;

            Annotation[] annotations = aClass.getAnnotations();
            /*
             * a nivel de clase
             */
            for (Annotation annotation : annotations) {
                if (annotation instanceof AnotacionTable) {
                    AnotacionTable myAnnotation = (AnotacionTable) annotation;
                    System.out.println("tabla: " + myAnnotation.nombre());
                    //System.out.println("value: " + myAnnotation.catalogo());
                }
            }
            /*
             * Ahora los campos
             */
            System.out.println(" Columnas:");
            Field[] fields = aClass.getDeclaredFields();
            for (Field f : fields) {
                Annotation[] annotationsf = f.getDeclaredAnnotations();
                for (Annotation annotation : annotationsf) {
                    if (annotation instanceof AnotacionColumna) {
                        AnotacionColumna myAnnotation = (AnotacionColumna) annotation;

                        System.out.print("nombre: " + myAnnotation.nombre());
                        System.out.print("  tipo: " + myAnnotation.tipo());
                        System.out.println("        ");
                    } else {
                        System.out.println("Relaciones:->");
                        if (annotation instanceof AnotacionRelaciones) {
                            AnotacionRelaciones myAnnotation = (AnotacionRelaciones) annotation;

                            System.out.print("columna: " + myAnnotation.columna());
                            System.out.print("  tabla: " + myAnnotation.tabla());
                            System.out.print("  nombre relacion: " + myAnnotation.nombre_relacion());
                            System.out.println("        ");
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void LeerParametroClaseString(String cn) {
        try {
            Class aClass = Class.forName(cn);
            Annotation[] annotations = aClass.getAnnotations();
            /*
             * a nivel de clase
             */
            for (Annotation annotation : annotations) {
                if (annotation instanceof AnotacionTable) {

                    AnotacionTable myAnnotation = (AnotacionTable) annotation;
                    System.out.println("tabla: " + myAnnotation.nombre());
                    //System.out.println("value: " + myAnnotation.catalogo());
                }
            }
            /*
             * Ahora los campos
             */
         
            Field[] fields = aClass.getDeclaredFields();
            for (Field f : fields) {
                Annotation[] annotationsf = f.getDeclaredAnnotations();
                for (Annotation annotation : annotationsf) {
                    if (annotation instanceof AnotacionColumna) {
                        AnotacionColumna myAnnotation = (AnotacionColumna) annotation;

                        System.out.print("nombre: " + myAnnotation.nombre());
                        System.out.print("  tipo: " + myAnnotation.tipo());
                        System.out.println("        ");
                    } else {
                        System.out.println("Relaciones:-->");
                        if (annotation instanceof AnotacionRelaciones) {
                            AnotacionRelaciones myAnnotation = (AnotacionRelaciones) annotation;

                            System.out.print("columna: " + myAnnotation.columna());
                            System.out.print("  tabla: " + myAnnotation.tabla());
                            System.out.print("  nombre relacion: " + myAnnotation.nombre_relacion());
                            System.out.println("        ");
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
