/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.table.DefaultTableModel;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.avbravo.jmoordbjsfgenerator.DGlobal;

/**
 *
 * @author avbravo
 */
public class Utilidades {

    public static boolean crearDirectorio(String ruta) {
        try {
            File file = new File(ruta);
            if (file.exists()) {
                //existe
                return true;
            } else {
                //System.out.println("No existe");
                boolean creado = file.mkdir();
                if (creado == true) {
                    // se creo exitosamente
                    return true;
                } else {
                    // no se pudo crear
                    return false;
                }
            }
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("CrearDirectorio() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return false;
    }

    public boolean encontrarDirectorio(String ruta) {
        try {
            File file = new File(ruta);
            if (file.exists()) {
                //existe
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("EncontrarDirectorio() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return false;
    }

    public void deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
    }

    public boolean eliminarDirectorio(String ruta) {
        try {
            File file = new File(ruta);
            if (!file.exists()) {
                //existe
                return false;
            } else {
                deleteDirectory(file);
                return true;
            }
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("EliminarDirectorio() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return false;
    }

    public static String convertirLetraMayuscula(String texto) {

        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return texto;
            }
            String letra = texto.substring(0, 1);

            texto = letra.toUpperCase() + texto.substring(1);
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("convertirLetraMayuscula() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return texto;
    }

    /**
     * ConvertirLetraMinuscula
     *
     * @param s_cadena
     * @param caracter
     * @return
     */
    public static String convertirLetraMinuscula(String texto) {

        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return texto;
            }
            String letra = texto.substring(0, 1);

            texto = letra.toLowerCase() + texto.substring(1);
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("convertirLetraMinuscula() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return texto;
    }

    public static void agregarTextoAlFinalArchivo(String rutaArchivo, String texto) {
        try {
            RandomAccessFile miRAFile;
            // Abrimos el fichero de acceso aleatorio
            miRAFile = new RandomAccessFile(rutaArchivo, "rw");
            // Nos vamos al final del fichero
            miRAFile.seek(miRAFile.length());
            // Incorporamos la cadena al fichero     
            miRAFile.writeBytes(texto);
            // Cerramos el fichero
            miRAFile.close();
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("agregarTextoAlFinalArchivo() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
    }

    /*
     * Actualiza el archivo una cadena con la que le especifiquemos ejemplo
     * ActualizarTextoArchivo("/home/avbravo/Documentos/etiquetas.properties",
     * "nombre", "name"); Actualiza en el archivo nombre por name
     */
    public static boolean actualizaTextoArchivo(String rutaArchivo, String search, String reemplazo) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";

            while ((line = reader.readLine()) != null) {

                oldtext += line + "\r\n";

            }
            reader.close();

            if (oldtext.indexOf(search) != -1) {
                String newtext = oldtext.replaceAll(search, reemplazo);

                FileWriter writer = new FileWriter(rutaArchivo);
                writer.write(newtext);
                writer.close();

                return true;
            }

        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("ActualizaTextoArchivo() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return false;
    }

    public static boolean encontrarTextoArchivo(String rutaArchivo, String search) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(search) != -1) {
                    encontrado = true;
                }
            }
            return encontrado;
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("EncontrarTextoArchivo() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return false;
    }

    /*
     * Inserta texto en el archivo antes o despues de la linea donde se
     * encuentre la cadena search el parametro antes = true : indica que se
     * insertara antes antes = false : indica que se insertara despues
     * InsertarTextoArchivo("/home/avbravo/Documentos/etiquetas.properties",
     * "name", "email=\"@ww\"", false)
     */
    public static boolean insertarTextoArchivo(String rutaArchivo, String search, String textoInsertar, boolean antes) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(search) != -1) {
                    if (antes) {
                        //insertarlo antes
                        oldtext += textoInsertar + "\r\n" + line + "\r\n";
                    } else {
                        //insertar despues
                        oldtext += line + "\r\n" + textoInsertar + "\r\n";
                    }

                    encontrado = true;

                } else {
                    oldtext += line + "\r\n";
                }


            }
            reader.close();

            if (encontrado) {
                FileWriter writer = new FileWriter(rutaArchivo);
                writer.write(oldtext);
                writer.close();

                return true;
            }

        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("InsertarTextoArchivo() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return false;
    }

    /*
     * DescomponerCadena
     */
    public static ArrayList<String> descomponerCadena(String texto, String separador) {
        ArrayList<String> lista = new ArrayList<String>();
        try {
            lista.removeAll(lista);
            StringTokenizer tk = new StringTokenizer(texto, separador); // Cambia aquí el separador
            while (tk.hasMoreTokens()) {

                lista.add(tk.nextToken());
            }
        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("DescomponerCadena() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);

        }
        return lista;
    }
    /*
     * nombre de columna
     */

    public static String nombreColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf("nombre=") != -1) {
                } else {
                }
                if (texto.indexOf(",isNoNulo=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf("nombre=") + 7, texto.indexOf(",isNoNulo="));
                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("NombreColumna() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);

        }
        return "";
    }
    /*
     *
     */

    public static Boolean isNoNuloColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return false;
                }
                if (texto.indexOf(")") == -1) {
                    return false;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(",isNoNulo=") != -1) {
                } else {
                }
                if (texto.indexOf(", tipo =") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(",isNoNulo=") + 10, texto.indexOf(", tipo ="));
                texto = texto.replace("\"", "");
                if (texto.equals("true")) {
                    return true;
                }
                return false;
            }

        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("isNoNuloColumna() " + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);

        }
        return false;
    }

    public static String tipoColumna(String texto) {
        try { 
            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", tipo =") != -1) {
                } else {
                }
                if (texto.indexOf(", mysql=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(", tipo =") + 8, texto.indexOf(", mysql="));
                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {

            DGlobal.error("tipoColumna()" + ex.getLocalizedMessage());
        }
        return "";
    }
public static String mysqlColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", mysql=") != -1) {
                } else {
                }
                if (texto.indexOf(",tamano =") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(", mysql=") + 8, texto.indexOf(",tamano ="));
                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {

            DGlobal.error("mysqlColumna()()" + ex.getLocalizedMessage());
        }
        return "";
    }
    
    
    
    public static Integer tamanoColumna(String texto) {
        try {
            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return 0;
                }
                if (texto.indexOf(")") == -1) {
                    return 0;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(",tamano =") != -1) {
                } else {
                }
                if (texto.indexOf(", digitosDecimales=") != -1) {
                } else {
                }
//                texto = texto.substring(texto.indexOf(",tamano =") + 8, texto.indexOf(", digitosDecimales="));
                texto = texto.substring(texto.indexOf(",tamano =") + 9, texto.indexOf(", digitosDecimales="));
                texto = texto.replace("\"", "");
                return Integer.parseInt(texto);
            }

        } catch (Exception ex) {
            NotifyDescriptor nd;
            nd = new NotifyDescriptor.Message("tamanoColumna()\ntexto: " + texto + "\n" + ex.getLocalizedMessage(), NotifyDescriptor.Message.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
        return 0;
    }

    public static Integer digitosDecimalesColumnas(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return 0;
                }
                if (texto.indexOf(")") == -1) {
                    return 0;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", digitosDecimales=") != -1) {
                } else {
                }
                if (texto.indexOf(", comentario=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(", digitosDecimales=") + 19, texto.indexOf(", comentario="));
                texto = texto.replace("\"", "");
                return Integer.parseInt(texto);
            }

        } catch (Exception ex) {

            DGlobal.error("digitosDecimalesColumnas()" + ex.getLocalizedMessage());
        }
        return 0;
    }

    public static String comentarioColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", comentario=") != -1) {
                } else {
                }
                if (texto.indexOf(", is_autoincrementable=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(", comentario=") + 13, texto.indexOf(", is_autoincrementable="));
                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {

            DGlobal.error("comentarioColumna() " + ex.getLocalizedMessage());
        }
        return "";
    }

    public static String isAutoIncrementableColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", is_autoincrementable=") != -1) {
                } else {
                }
                if (texto.indexOf(", isPK=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(", is_autoincrementable=") + 23, texto.indexOf(", isPK="));
                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {

            DGlobal.error("isAutoIncrementableColumna()" + ex.getLocalizedMessage());
        }
        return "";
    }

    public static Boolean isPKColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return false;
                }
                if (texto.indexOf(")") == -1) {
                    return false;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", isPK=") != -1) {
                } else {
                }
                if (texto.indexOf(",isImagen=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(", isPK=") + 7, texto.indexOf(",isImagen="));
                texto = texto.replace("\"", "");
                if (texto.equals("true")) {
                    return true;
                }

                return false;
            }

        } catch (Exception ex) {
            DGlobal.error("isPKColumna()" + ex.getLocalizedMessage());
        }
        return false;
    }

    public static Boolean isImagenColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return false;
                }
                if (texto.indexOf(")") == -1) {
                    return false;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(",isImagen=") != -1) {
                } else {
                }
                if (texto.indexOf(", isUrl=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(",isImagen=") + 10, texto.indexOf(", isUrl="));
                texto = texto.replace("\"", "");
                if (texto.equals("true")) {
                    return true;
                }

                return false;
            }

        } catch (Exception ex) {

            DGlobal.error("isImagenColumna()" + ex.getLocalizedMessage());
        }
        return false;
    }

    /*
     *
     */
    public static Boolean isURLColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return false;
                }
                if (texto.indexOf(")") == -1) {
                    return false;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", isUrl=") != -1) {
                } else {
                }
                if (texto.indexOf(", isVisible=") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(", isUrl=") + 8, texto.indexOf(", isVisible="));
                texto = texto.replace("\"", "");
                if (texto.equals("true")) {
                    return true;
                }

                return false;
            }

        } catch (Exception ex) {
            DGlobal.error("isURLColumna() " + ex.getLocalizedMessage());
        }
        return false;
    }

    public static Boolean isVisibleColumna(String texto) {
        try {

            if (texto.indexOf("@Columna(") != - 1) {
                texto.replace("@Columna(", "");
                if (texto.indexOf("(") == -1) {
                    return false;
                }
                if (texto.indexOf(")") == -1) {
                    return false;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(", isVisible=") != -1) {
                } else {
                }

                System.out.println(" -->  " + texto);
                texto = texto.substring(texto.indexOf(", isVisible=") + 12, texto.indexOf(", isVisible=") + 16);
                texto = texto.replace("\"", "");

                texto = texto.replace("\"", "");
                if (texto.equals("true")) {
                    return true;
                }

                return false;
            }

        } catch (Exception ex) {
            DGlobal.error("isVisibleColumna() " + ex.getLocalizedMessage());

        }
        return false;
    }

    /*
     * obtiene el nombre de la clase relacionada
     */
    public static String claseRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("(") + 8, texto.indexOf(", tabla"));

                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {
            DGlobal.error("claseRelacionada() " + ex.getLocalizedMessage());

        }
        return "";

    }

    public static String tablaRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("tabla =") + 8, texto.indexOf(",columna"));

                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {

            DGlobal.error("tablaRelacionada() " + ex.getLocalizedMessage());
        }
        return "";

    }

    public static Boolean padreRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return Boolean.FALSE;
                }
                if (texto.indexOf(")") == -1) {
                    return Boolean.FALSE;
                }
                texto = texto.substring(texto.indexOf("ispadre=") + 8, texto.indexOf(")"));

                texto = texto.replace("\"", "");
                texto = texto.trim();

                return texto.equals("true") ? Boolean.TRUE : Boolean.FALSE;
            }

        } catch (Exception ex) {
            DGlobal.error("tablaRelacionada() " + ex.getLocalizedMessage());
        }
        return Boolean.FALSE;

    }

    public static String reglaActualizacionRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("regla_actualizacion=") + 20, texto.indexOf(", regla_eliminacion="));

                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {
            DGlobal.error("reglaActualizacionRelacionada() " + ex.getLocalizedMessage());

        }
        return "";

    }

    public static String reglaEliminacionRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("regla_eliminacion=") + 17, texto.indexOf(", key_seq="));

                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {
            DGlobal.error("reglaEliminacionRelacionada() " + ex.getLocalizedMessage());

        }
        return "";

    }

    public static String key_SeqRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("key_seq=") + 8, texto.indexOf(", nombre_relacion="));

                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {
            DGlobal.error("key_SeqRelacionada() " + ex.getLocalizedMessage());

        }
        return "";

    }

    public static String nombreRelacionRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("nombre_relacion=") + 16, texto.indexOf(", tipo_relacion="));

                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {
            DGlobal.error("nombreRelacionRelacionada() " + ex.getLocalizedMessage());
        }
        return "";

    }

    public static String tipoRelacionRelacionada(String texto) {
        try {

            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                System.out.println(" texto " + texto);
                if (texto.indexOf("(") == -1) {
                    return "";
                }
                if (texto.indexOf(")") == -1) {
                    return "";
                }
                texto = texto.substring(texto.indexOf("tipo_relacion=") + 15, texto.indexOf(")"));

                texto = texto.replace("\"", "");
                texto = texto.trim();
                return texto;
            }

        } catch (Exception ex) {

            DGlobal.error("tipoRelacionRelacionada() " + ex.getLocalizedMessage());
        }
        return "";

    }

    /*
     * Se pasa una cadena @Relaciones y devuelve una lista con las columnas.
     * Esto es para el caso de que existan multiples relaciones
     */
    public static ArrayList<String> columnasRelacionadas(String texto) {
        ArrayList<String> lista = new ArrayList<String>();
        try {
            lista.removeAll(lista);
            if (texto.indexOf("@Relaciones(") != - 1) {
                texto.replace("@Relaciones(", "");
                if (texto.indexOf("(") == -1) {
                    return null;
                }
                if (texto.indexOf(")") == -1) {
                    return null;
                }
                texto = texto.substring(texto.indexOf("("), texto.indexOf(")"));
                if (texto.indexOf(",columna =") != -1) {
                } else {
                }
                if (texto.indexOf(", regla_actualizacion") != -1) {
                } else {
                }
                texto = texto.substring(texto.indexOf(",columna =") + 11, texto.indexOf(", regla_actualizacion"));
                texto = texto.replace("\"", "");
                lista = descomponerCadena(texto, ",");

            }

        } catch (Exception ex) {

            DGlobal.error("columnasRelacionadas() " + ex.getLocalizedMessage());
        }
        return lista;
    }

    public static boolean actualizaCssLayout(String rutaArchivo) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            boolean control = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("#top a:link, #top a:visited {")) {
                    control = true;
                } else {
                    if (control) {
                        if (line.equals("color: white;")) {
                            line = "color: blue;";
                            control = false;
                        }
                    }
                }
                oldtext += line + "\r\n";

            }
            reader.close();



            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write(oldtext);
            writer.close();

            return true;


        } catch (Exception ex) {

            DGlobal.error("actualizaCssLayout()() " + ex.getLocalizedMessage());
        }
        return false;
    }

    public static String getLineaArchivo(String rutaArchivo, String search) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String cadena = "";
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(search) != -1) {
                    encontrado = true;
                    cadena = line;
                }
            }
            return cadena;
        } catch (Exception ex) {
            DGlobal.error("getLineaArchivo() " + ex.getLocalizedMessage());
        }
        return "";
    }

    public static DefaultTableModel limpiarModelo(DefaultTableModel modelo) {
        if (modelo != null) {
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
        return modelo;
    }
}
