/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator.proyecto;

import java.io.File;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.netbeans.api.project.Project;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;
import org.avbravo.jmoordbjsfgenerator.DGlobal;
/**
 *
 * @author avbravo
 */
public class ProyectoInformacion extends CookieAction {

    String web = "";
    String src = "";

    public boolean getInformation(Project project) {
        try {
            web = "";

            MySession.setEsProyectoWeb(false);
            MySession.setDirectorioWebInf("");
            MySession.setMavenDirectorio("");

            if (project != null) {
                MySession.setNombreProyecto(project.getProjectDirectory().getName());
                String tipoProyecto = project.getClass().getSimpleName();
                MySession.setTipoProyecto(tipoProyecto);
                src = project.getProjectDirectory().getFileObject("src").toString();
                src = src.substring(src.indexOf("[") + 1, src.indexOf("@"));
                MySession.setSrc(src);

                if (tipoProyecto.equals("NbMavenProjectImpl")) {
                    /*
                     * maven
                     */
                    MySession.setSrcJava(MySession.getSrc() + MySession.getFileSeparator() + "main" + MySession.getFileSeparator() + "java");
                    String lweb = MySession.getSrc() + MySession.getFileSeparator() + "main" + MySession.getFileSeparator() + "webapp";

                    File file = new File(lweb);
                    if (file.exists()) {
                        //indica que es web
                        web = lweb;
                        MySession.setWeb(web);
                        MySession.setDirectorioWebInf(web + MySession.getFileSeparator() + "WEB-INF");
                        MySession.setEsProyectoWeb(true);
                        MySession.setMavenDirectorio(src + MySession.getFileSeparator() + "main" + MySession.getFileSeparator() + "resources");
                    }
                } else {

                    if (tipoProyecto.equals("NbModuleProject")) {
                        /*
                         * modulo netbeans
                         */
                        MySession.setSrcJava(MySession.getSrc());

                    } else {
                        if (tipoProyecto.equals("J2SEProject")) {
                            /*
                             * desktop
                             */
                            MySession.setSrcJava(MySession.getSrc());
                        } else {
                            if (tipoProyecto.equals("WebProject")) {
                                /*
                                 * web
                                 */
                                web = project.getProjectDirectory().getFileObject("web").toString();
                                web = web.substring(web.indexOf("[") + 1, web.indexOf("@"));
                                MySession.setSrcJava(MySession.getSrc() + MySession.getFileSeparator() + "java");
                                MySession.setDirectorioWebInf(web + MySession.getFileSeparator() + "WEB-INF");
                                MySession.setEsProyectoWeb(true);
                            }
                        }
                    }

                    MySession.setWeb(web);
                }

                return true;

            }

        } catch (Exception ex) {
            DGlobal.error(ex.getLocalizedMessage());


        }
        return false;
    }

    @Override
    protected int mode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Class<?>[] cookieClasses() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void performAction(Node[] nodes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HelpCtx getHelpCtx() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
