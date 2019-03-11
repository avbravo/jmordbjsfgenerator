/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.avbravo.jmoordbjsfgenerator.generales.Directorios;
import org.avbravo.jmoordbjsfgenerator.generales.MySession;
import org.avbravo.jmoordbjsfgenerator.proyecto.ProyectoInformacion;
import org.avbravo.jmoordbjsfgenerator.utilidades.Utilidades;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
//import org.netbeans.api.templates.TemplateRegistration;

import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.avbravo.jmoordbjsfgenerator.DGlobal;
import org.avbravo.jmoordbjsfgenerator.beans.Clases;
import org.avbravo.jmoordbjsfgenerator.layout.CssLayout;
import org.avbravo.jmoordbjsfgenerator.layout.DefaultCss;
import org.avbravo.jmoordbjsfgenerator.parameters.Parameters;
import org.avbravo.jmoordbjsfgenerator.temas.UpdateCambiadorTemas;
import org.avbravo.jmoordbjsfgenerator.temas.UpdateTemasWebXML;
import org.avbravo.jmoordbjsfgenerator.xhtml.IndexXhmtl;
import org.avbravo.jmoordbjsfgenerator.xhtml.JsfComposite;
import org.avbravo.jmoordbjsfgenerator.xhtml.JsfListPaginas;
import org.avbravo.jmoordbjsfgenerator.xhtml.JsfPaginas;
import org.avbravo.jmoordbjsfgenerator.xhtml.Loginxhtml;
import org.avbravo.jmoordbjsfgenerator.xhtml.Menuxhtml;
import org.avbravo.jmoordbjsfgenerator.xhtml.MyPrimeFaces;
import org.avbravo.jmoordbjsfgenerator.xhtml.SessionExpiredXhtml;
import org.avbravo.jmoordbjsfgenerator.xhtml.TemplateFacelets;
import org.netbeans.api.templates.TemplateRegistration;

// TODO define position attribute
@TemplateRegistration(folder = "JSF", displayName = "#JSFGeneratorWizardIterator_displayName", iconBase = "org/avbravo/jmoordbjsfgenerator/resources/xhtml.png", description = "jSFGenerator.html")
@Messages("JSFGeneratorWizardIterator_displayName=JmoordbJSFGenerator")
public final class JmoordbJSFGeneratorWizardIterator implements WizardDescriptor.InstantiatingIterator<WizardDescriptor> {

    private int index;
    private WizardDescriptor wizard;
    private List<WizardDescriptor.Panel<WizardDescriptor>> panels;
    /*
     * 
     */
    List<Clases> listClases;
    String[] crud = {"Insert", "Query", "List"};
    Boolean selectedCreate = Boolean.FALSE;
    Boolean selectedEdit = Boolean.FALSE;
    Boolean selectedList = Boolean.FALSE;
    Boolean selectedPage = Boolean.FALSE;
    Boolean selected = Boolean.FALSE;
//    String parametrosTemaPrimefaces = "";
    ArrayList<String> archivoList = new ArrayList<String>();
    ArrayList<String> controllerList = new ArrayList<String>();
    ProyectoInformacion projectInformation = new ProyectoInformacion();
    String tipoMenu;
    String temaPrimefaces;

    private List<WizardDescriptor.Panel<WizardDescriptor>> getPanels() {

        if (MySession.isEsProyectoWeb()) {



            if (panels == null) {
                panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
                panels.add(new JmoordbJSFGeneratorWizardPanel1());
                panels.add(new JmoordbJSFGeneratorWizardPanel2());
                panels.add(new JmoordbJSFGeneratorWizardPanel3());
                panels.add(new JmoordbJSFGeneratorWizardPanel4());
                panels.add(new JmoordbJSFGeneratorWizardPanel5());
                String[] steps = createSteps();
                for (int i = 0; i < panels.size(); i++) {
                    Component c = panels.get(i).getComponent();
                    if (steps[i] == null) {
                        // Default step name to component name of panel. Mainly
                        // useful for getting the name of the target chooser to
                        // appear in the list of steps.
                        steps[i] = c.getName();
                    }
                    if (c instanceof JComponent) { // assume Swing components
                        JComponent jc = (JComponent) c;
                        jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                        jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                        jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                        jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                        jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
                    }
                }
            }
        }
        return panels;
    }
    /*
     * se ejecuta al presionar el boton cancelar
     */

    @Override
    public Set<?> instantiate() throws IOException {
        // TODO return set of FileObject (or DataObject) you have created
        boolean cancelled = wizard.getValue() != wizard.FINISH_OPTION;

        String template = (String) wizard.getProperty("facelesTemplate");
        Boolean growSticker = (Boolean) wizard.getProperty("growSticker");
        Boolean growlLife = (Boolean) wizard.getProperty("growSticker");
        String growlsSeconds = (String) wizard.getProperty("growlsSeconds");
        Boolean generateGrowlnPage = (Boolean) wizard.getProperty("generateGrowlnPage");
        Boolean generateGrowlnTemplate = (Boolean) wizard.getProperty("generateGrowlnTemplate");
        Boolean tableScrollable = (Boolean) wizard.getProperty("tableScrollable");

        String environment = (String) wizard.getProperty("environment");
        Boolean growl = (Boolean) wizard.getProperty("growl");
        Boolean messages = (Boolean) wizard.getProperty("messages");
        Boolean growlandmessages = (Boolean) wizard.getProperty("growlandmessages");
        temaPrimefaces = (String) wizard.getProperty("temaPrimefaces");
        String patronFecha = (String) wizard.getProperty("patronFecha");
        String patronFechaHora = (String) wizard.getProperty("patronFechaHora");
        String patronHora = (String) wizard.getProperty("patronHora");
        Integer rowsDataTable = (Integer) wizard.getProperty("rowsDataTable");
        Integer dialogWidth = (Integer) wizard.getProperty("dialogWidth");
        Integer dialogWidthRelation = (Integer) wizard.getProperty("dialogWidthRelation");
        Boolean dialogDinamyc = (Boolean) wizard.getProperty("dialogDinamyc");
        Integer dialogHeight = (Integer) wizard.getProperty("dialogHeight");
        Boolean dialogCustomized = (Boolean) wizard.getProperty("dialogCustomized");
        String buttonTextIcon = (String) wizard.getProperty("buttonTextIcon");
        tipoMenu = (String) wizard.getProperty("tipoMenu");
        String typeRelationsDataTable = (String) wizard.getProperty("typeRelationsDataTable");


        MySession.setSobreEscribirTemplate(DGlobal.getReplaceTemplate());
        MySession.setSobreEscribirIndex(DGlobal.getReplaceIndex());
        MySession.setSobreEscribirMenu(DGlobal.getReplaceMenu());
        MySession.setGrowSticker(growSticker);
        MySession.setGrowSticker(growSticker);
        MySession.setGrowlLife(growlLife);
        if (growlsSeconds.equals("")) {
            DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoValidSecond"));
            return null;
        }
        MySession.setGrowlsSeconds(Integer.parseInt(growlsSeconds));
        MySession.setGenerateGrowlnPage(generateGrowlnPage);
        MySession.setGenerateGrowlnTemplate(generateGrowlnTemplate);
        MySession.setFaceletsTemplate(template);

        MySession.setTableScrollable(tableScrollable);
        MySession.setEnvironment(environment);
        MySession.setGrowl(growl);
        MySession.setMessages(messages);
        MySession.setGrowlAndMessages(growlandmessages);
        if (growl) {
            MySession.setTipoMensajes("growl");
        } else {
            if (messages) {
                MySession.setTipoMensajes("mensajes");
            } else {
                if (growlandmessages) {
                    MySession.setTipoMensajes("growlandmessages");
                }
            }
        }
        MySession.setTemaPrimefaces(temaPrimefaces);

//
        MySession.setPatronFecha(patronFecha);
        MySession.setPatronFechaHora(patronFechaHora);
        MySession.setPatronHora(patronHora);

        MySession.setRowsDataTable(rowsDataTable);
        MySession.setDialogWidth(dialogWidth);
        MySession.setDialogWidthRelation(dialogWidthRelation);

        if (dialogDinamyc) {
            MySession.setDialogDinamyc(Boolean.TRUE);
            MySession.setDialogHeight(0);
        } else {
            MySession.setDialogHeight(dialogHeight);
            MySession.setDialogDinamyc(Boolean.FALSE);
        }
        MySession.setDialogCustomized(dialogCustomized);
        MySession.setButtonTextIcon(buttonTextIcon);

        MySession.botonTextoIcono = false;
        MySession.botonTextoOnly = false;
        MySession.botonIconoOnly = false;
        if (buttonTextIcon.toString().equals("Text & Icon")) {
            MySession.botonTextoIcono = true;
        } else {
            if (buttonTextIcon.equals("Text Only")) {
                MySession.botonTextoOnly = true;
            } else {
                if (buttonTextIcon.equals("Icon Only")) {
                    MySession.botonIconoOnly = true;
                }
            }
        }

        MySession.setTipoMenu(tipoMenu);

        MySession.setTypeRelationsDataTable(typeRelationsDataTable);
        procesar();

        String rutaArchivoParameters = Directorios.getDirectorioGenerales() + MySession.getFileSeparator() + "Parameters.txt";
        Parameters parameters = new Parameters(rutaArchivoParameters);
        /*
         * 
         */
//        if (!cancelled) {
//            DGlobal.informacion("presiono el boton Cancelar");
//        } else {
//            DGlobal.informacion(" No presiono el boton cancelar.....");
//
//        }

        return Collections.emptySet();
    }

    @Override
    public void initialize(WizardDescriptor wizard) {
        try {
            this.wizard = wizard;
            Project project = Templates.getProject(wizard);

            String nombre = ProjectUtils.getInformation(project).getDisplayName();
            DGlobal.setProject(project);
            //   projectTextField.setText(ProjectUtils.getInformation(project).getDisplayName());
            if (projectInformation.getInformation(DGlobal.getProject())) {
                if (!MySession.isEsProyectoWeb()) {
                    DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensaje_ProyectoNoSeleccionado"));

                } else {
                    Directorios.asignarDirectorios();
                    cargarArchivoParametros();
                }
            }
            listClases = new ArrayList<Clases>();

        } catch (Exception e) {
            DGlobal.error("initialize() error" + e.getLocalizedMessage());
        }

    }

    @Override
    public void uninitialize(WizardDescriptor wizard) {
        panels = null;
    }

    @Override
    public WizardDescriptor.Panel<WizardDescriptor> current() {
        return getPanels().get(index);
    }

    @Override
    public String name() {
        return index + 1 + ". from " + getPanels().size();
    }

    @Override
    public boolean hasNext() {
        return index < getPanels().size() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }
    // If something changes dynamically (besides moving between panels), e.g.
    // the number of panels changes in response to user input, then use
    // ChangeSupport to implement add/removeChangeListener and call fireChange
    // when needed

    // You could safely ignore this method. Is is here to keep steps which were
    // there before this wizard was instantiated. It should be better handled
    // by NetBeans Wizard API itself rather than needed to be implemented by a
    // client code.
    private String[] createSteps() {
        String[] beforeSteps = (String[]) wizard.getProperty("WizardPanel_contentData");
        assert beforeSteps != null : "This wizard may only be used embedded in the template wizard";
        String[] res = new String[(beforeSteps.length - 1) + panels.size()];
        for (int i = 0; i < res.length; i++) {
            if (i < (beforeSteps.length - 1)) {
                res[i] = beforeSteps[i];
            } else {
                res[i] = panels.get(i - beforeSteps.length + 1).getComponent().getName();
            }
        }
        return res;
    }

    private void cargarArchivoParametros() {
        try {
            DGlobal.setTemplate("");
            DGlobal.setTemaInicial("");
            String rutaArchivoParameters = Directorios.getDirectorioGenerales() + MySession.getFileSeparator() + "Parameters.txt";
            DGlobal.setEsPrimeraEjecucion(Boolean.TRUE);
            File fichero = new File(rutaArchivoParameters);
            if (fichero.exists()) {
                DGlobal.setEsPrimeraEjecucion(Boolean.FALSE);
                String patronFecha = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "patronFecha="));
                String patronFechaHora = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "patronFechaHora="));
                String patronHora = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "patronHora="));
                String facelesTemplate = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "facelesTemplate="));
                String rowsDataTable = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "rowsDataTable="));
                String typeRelationsDataTable = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "typeRelationsDataTable="));
                String tableScrollable = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "tableScrollable="));
                String temaPrimefaces = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "temaPrimefaces="));
                String tipoMenu = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "tipoMenu="));
                String buttonTextIcon = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "buttonTextIcon="));
                String growl = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "growl="));
                String messages = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "messages="));
                String growlandmessages = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "growlandmessages="));
                String dialogWidth = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "dialogWidth="));
                String dialogWidthRelation = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "dialogWidthRelation="));
                String dialogDinamyc = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "dialogDinamyc="));
                String dialogCustomized = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "dialogCustomized="));
                String dialogHeight = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "dialogHeight="));
                String environment = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "environment="));
                String growSticker = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "growSticker="));
                String growlLife = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "growlLife="));
                String growlsSeconds = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "growlsSeconds="));
                String generateGrowlnPage = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "generateGrowlnPage="));
                String generateGrowlnTemplate = DGlobal.cortarTexto(Utilidades.getLineaArchivo(rutaArchivoParameters, "generateGrowlnTemplate="));

                DGlobal.setTemaInicial(temaPrimefaces);
                ((WizardDescriptor) wizard).putProperty("patronFecha", patronFecha);
                ((WizardDescriptor) wizard).putProperty("patronFechaHora", patronFechaHora);
                ((WizardDescriptor) wizard).putProperty("patronHora", patronHora);
                ((WizardDescriptor) wizard).putProperty("facelesTemplate", facelesTemplate);
                ((WizardDescriptor) wizard).putProperty("rowsDataTable", rowsDataTable);
                ((WizardDescriptor) wizard).putProperty("typeRelationsDataTable", typeRelationsDataTable);
                ((WizardDescriptor) wizard).putProperty("tableScrollable", tableScrollable);
                ((WizardDescriptor) wizard).putProperty("temaPrimefaces", temaPrimefaces);
                ((WizardDescriptor) wizard).putProperty("tipoMenu", tipoMenu);
                ((WizardDescriptor) wizard).putProperty("buttonTextIcon", buttonTextIcon);
                ((WizardDescriptor) wizard).putProperty("growl", growl);
                ((WizardDescriptor) wizard).putProperty("messages", messages);
                ((WizardDescriptor) wizard).putProperty("growlandmessages", growlandmessages);
                ((WizardDescriptor) wizard).putProperty("dialogWidth", dialogWidth);
                ((WizardDescriptor) wizard).putProperty("dialogWidthRelation", dialogWidthRelation);
                ((WizardDescriptor) wizard).putProperty("dialogDinamyc", dialogDinamyc);
                ((WizardDescriptor) wizard).putProperty("dialogCustomized", dialogCustomized);
                ((WizardDescriptor) wizard).putProperty("dialogHeight", dialogHeight);
                ((WizardDescriptor) wizard).putProperty("environment", environment);
                ((WizardDescriptor) wizard).putProperty("growSticker", growSticker);
                ((WizardDescriptor) wizard).putProperty("growlLife", growlLife);
                ((WizardDescriptor) wizard).putProperty("growlsSeconds", growlsSeconds);
                ((WizardDescriptor) wizard).putProperty("generateGrowlnPage", generateGrowlnPage);
                ((WizardDescriptor) wizard).putProperty("generateGrowlnTemplate", generateGrowlnTemplate);
                DGlobal.setTemplate(facelesTemplate);

            } else {
                ((WizardDescriptor) wizard).putProperty("patronFecha", "dd/MM/yyyy");
                ((WizardDescriptor) wizard).putProperty("patronFechaHora", "dd/MM/yyyy HH:mm:ss");
                ((WizardDescriptor) wizard).putProperty("patronHora", "HH:mm:ss");
//((WizardDescriptor) wizard).putProperty("facelesTemplate", "superior_izquierda_derecha");
                ((WizardDescriptor) wizard).putProperty("facelesTemplate", "");
                ((WizardDescriptor) wizard).putProperty("rowsDataTable", "20");
                ((WizardDescriptor) wizard).putProperty("typeRelationsDataTable", "fields");
                ((WizardDescriptor) wizard).putProperty("tableScrollable", "false");
                ((WizardDescriptor) wizard).putProperty("temaPrimefaces", "aristo");
                ((WizardDescriptor) wizard).putProperty("tipoMenu", "MenuBar");
                ((WizardDescriptor) wizard).putProperty("buttonTextIcon", "Text & Icon");
                ((WizardDescriptor) wizard).putProperty("growl", "true");
                ((WizardDescriptor) wizard).putProperty("messages", "false");
                ((WizardDescriptor) wizard).putProperty("growlandmessages", "false");
                ((WizardDescriptor) wizard).putProperty("dialogWidth", "450");
                ((WizardDescriptor) wizard).putProperty("dialogWidthRelation", "550");
                ((WizardDescriptor) wizard).putProperty("dialogDinamyc", "true");
                ((WizardDescriptor) wizard).putProperty("dialogCustomized", "false");
                ((WizardDescriptor) wizard).putProperty("dialogHeight", "250");
                ((WizardDescriptor) wizard).putProperty("environment", "Production");
                ((WizardDescriptor) wizard).putProperty("growSticker", "true");
                ((WizardDescriptor) wizard).putProperty("growlLife", "false");
                ((WizardDescriptor) wizard).putProperty("growlsSeconds", "1500");
                ((WizardDescriptor) wizard).putProperty("generateGrowlnPage", "false");
                ((WizardDescriptor) wizard).putProperty("generateGrowlnTemplate", "true");

                // DGlobal.informacion(NbBundle.getMessage(JSFGeneratorWizardIterator.class, "Mensajes_NoFileParameters"));
            }
        } catch (Exception ex) {
            DGlobal.error("cargarArchivoParametros()" + ex.getLocalizedMessage());
        }
    }

    private void procesar() {
        try {
            File dir = new File(Directorios.getDirectorioPropiedades());
            String[] ficheros = dir.list();
            /*
             * reemplaza la extension .properties por espacios en blanco
             */
            if (ficheros == null) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensaje_NoArchivoPropiedades"));
                return;
            }
            String dirResources = MySession.getWeb() + MySession.getFileSeparator() + "resources";
            if (Utilidades.crearDirectorio(dirResources)) {
            } else {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoCreateFolderResources"));
                return;
            }
            String dirResourcesCss = MySession.getWeb() + MySession.getFileSeparator() + "resources" + MySession.getFileSeparator() + "css";
            if (Utilidades.crearDirectorio(dirResourcesCss)) {
            } else {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoCreateFolderCss"));
                return;
            }

            String plantilla = "templates";
            String propiedades = "mensajes";
            String rutaArchivoPlantilla = MySession.getWeb() + MySession.getFileSeparator() + plantilla + ".xhtml";
            /*
             * crear el archivo layout
             */
            String rutaArchivoCssLayout = MySession.getWeb() + MySession.getFileSeparator() + "resources" + MySession.getFileSeparator() + "css" + MySession.getFileSeparator() + "cssLayout.css";
            CssLayout cssLayout = new CssLayout();
            /*
             * actualizar archivo layout
             */
            cssLayout.CrearArchivoCssLayout(rutaArchivoCssLayout);
            Utilidades.actualizaCssLayout(rutaArchivoCssLayout);

            /*
             * crear archivo default.css
             */
            String rutaArchivoDefault = MySession.getWeb() + MySession.getFileSeparator() + "resources" + MySession.getFileSeparator() + "css" + MySession.getFileSeparator() + "default.css";
            DefaultCss defaultCss = new DefaultCss(rutaArchivoDefault);

            /*
             * crear template.xhtml
             */
            if (MySession.getFaceletsTemplate() == null || MySession.getFaceletsTemplate().equals("")) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoTemplateDesing"));
                return;

            }
            if (MySession.getSobreEscribirTemplate()) {

                TemplateFacelets templateFacelets = new TemplateFacelets(rutaArchivoPlantilla);
            }
            /*
             * Ajusto los links de la plantilla de ./ a nombredeproyecto/
             */
            if (Utilidades.encontrarTextoArchivo(rutaArchivoPlantilla, "<link href=\"./resources/css/default.css\" rel=\"stylesheet\" type=\"text/css\" />")) {
                String link = "<link href=\"" + "/" + MySession.getNombreProyecto() + "/resources/css/default.css\" rel=\"stylesheet\" type=\"text/css\" />";
                if (!Utilidades.actualizaTextoArchivo(rutaArchivoPlantilla, "<link href=\"./resources/css/default.css\" rel=\"stylesheet\" type=\"text/css\" />", link)) {
                    DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoDefaultCSS"));
                }
            }
            if (Utilidades.encontrarTextoArchivo(rutaArchivoPlantilla, "<link href=\"./resources/css/cssLayout.css\" rel=\"stylesheet\" type=\"text/css\" />")) {
                String link = "<link href=\"" + "/" + MySession.getNombreProyecto() + "/resources/css/cssLayout.css\" rel=\"stylesheet\" type=\"text/css\" />";
                if (!Utilidades.actualizaTextoArchivo(rutaArchivoPlantilla, "<link href=\"./resources/css/cssLayout.css\" rel=\"stylesheet\" type=\"text/css\" />", link)) {
                    DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoLayoutCSS"));
                }
            }

            String rutaArchivoPropiedades = Directorios.getDirectorioPropiedades() + MySession.getFileSeparator() + propiedades + ".properties";
            if (!isProperties(rutaArchivoPropiedades)) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoTituloPropiedad"));
                return;
            }
            String loadBundle = "        <f:loadBundle basename=\"" + Directorios.getPaquete() + ".properties." + propiedades + "\" var=\"" + propiedades + "\" />";
            if (isLoadBundle(rutaArchivoPlantilla)) {
                String linea = Utilidades.getLineaArchivo(rutaArchivoPlantilla, "<f:loadBundle basename=");
                Utilidades.actualizaTextoArchivo(rutaArchivoPlantilla, linea, loadBundle);
            } else {
                Utilidades.insertarTextoArchivo(rutaArchivoPlantilla, "<title>", loadBundle, true);
            }
            /*
             * inserto el view en la plantilla
             */
            String mylocale = "        <f:view locale=\"#{idiomas.locale}\"></f:view>";
            if (!Utilidades.encontrarTextoArchivo(rutaArchivoPlantilla, mylocale)) {
                Utilidades.insertarTextoArchivo(rutaArchivoPlantilla, loadBundle, mylocale, true);
            }

            /*
             * Guardamos la informacion del archivo de propiedades y de
             * plantilla
             */
            MySession.setVar(propiedades);
            Directorios.setRutaArchivoPropiedades(rutaArchivoPropiedades);
            Directorios.setNombreArchivoPlantilla(plantilla + ".xhtml");
            Directorios.setRutaArchivoPlantilla(rutaArchivoPlantilla);
            MySession.setPrimefacesVersion("3.x");

            generar();


        } catch (Exception e) {
            DGlobal.informacion("procesar() " + e.getLocalizedMessage());
        }
    }

    /*
     * buscar el loadboundle en la plantilla
     */
    private boolean isLoadBundle(String rutaFile) {
        try {

            return Utilidades.encontrarTextoArchivo(rutaFile, "<f:loadBundle basename=");

        } catch (Exception ex) {
            DGlobal.error("isLoadBundle() " + ex.getLocalizedMessage());
        }
        return false;
    }

    /*
     * busca en el archivo properties la etiqueta aplicacion.titulo
     */
    private boolean isProperties(String rutaFile) {
        try {


            return Utilidades.encontrarTextoArchivo(rutaFile, "aplicacion.titulo");

        } catch (Exception ex) {
            DGlobal.error("isProperties() " + ex.getLocalizedMessage());
        }
        return false;
    }

    private void generar() {
        try {

            habilitarPaginasTable();
            Boolean seleccionoPaginas = Boolean.FALSE;
            for (Clases clases : listClases) {
                if (clases.getCrear() || clases.getEditar() || clases.getListar()) {
                    seleccionoPaginas = Boolean.TRUE;
                    break;
                }
            }


            if (DGlobal.getTemaInicial().equals(temaPrimefaces) && !DGlobal.getReplaceMenu() && !DGlobal.getReplaceTemplate() && !DGlobal.getReplaceIndex() && !seleccionoPaginas) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoPagesSelected"));
                return;
            }



            /*
             * Cargar las clases y los controller
             */
            archivoList.removeAll(archivoList);
            controllerList.removeAll(controllerList);
            File dirBeans = new File(Directorios.getDirectorioBeans());
            File dirController = new File(Directorios.getDirectorioController());
            String[] ficherosBeans = dirBeans.list();
            String[] ficherosController = dirController.list();
            /*
             * reemplaza la extension .java por espacios en blanco
             */
            if (dirBeans == null) {
                DGlobal.informacion(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoJavaBeans"));
                return;
            }
            for (String fichero : ficherosBeans) {
                String nombre = fichero.replaceAll(".java", "").trim();
                archivoList.add(nombre);
            }
            if (ficherosController == null) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoController"));
                return;
            }
            /*
             * Verificar el directorio WEB-INF que contenga los archivios
             * web.xwml, context.xml y beans.xml si es maven
             */
            File dirWebInf = new File(MySession.getDirectorioWebInf());
            String[] ficherosWebInf = dirWebInf.list();
            if (ficherosWebInf == null) {

                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoArchivosWebINF"));
                return;
            }
            boolean bweb = false, bcontext = false, bbeans = false;
            for (String fichero : ficherosWebInf) {
                if (fichero.indexOf("web.xml") != -1) {
                    bweb = true;
                } else {
                    if (fichero.indexOf("context.xml") != -1) {
                        bcontext = true;
                    } else {
                        if (fichero.indexOf("beans.xml") != -1) {
                            bbeans = true;
                        }
                    }
                }
            }
            if (!bweb) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoWebXML"));
                return;
            }
            if (!bcontext) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoContextXML"));
                return;
            }
            if (!bbeans) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoBeansXML"));
                return;
            }
            /*
             * Verificamos que el <Resource name="jdbc/ este incluido en el
             * web.xml
             */
            if (!isWebXMLJDBC(MySession.getDirectorioWebInf() + MySession.getFileSeparator() + "web.xml")) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_WebXMLNoData"));
                return;
            }
            if (!isContextXMLJDBC(MySession.getDirectorioWebInf() + MySession.getFileSeparator() + "context.xml")) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_ContextNoData"));
                return;
            }

            /*
             * agrega primefaces
             */

            MyPrimeFaces.addTemplateHeader();
            /*
             *
             */


            String directorioModulo = MySession.getWeb() + MySession.getFileSeparator() + "page";
            if (!Utilidades.crearDirectorio(directorioModulo)) {
                DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensaje_NoDirectorioModulos"));
                return;
            }
            /*
             * genera el menu
             */
            StringBuilder menuLinks = new StringBuilder("");
            StringBuilder menuPrimefaces = new StringBuilder("");
            /*
             * menu primefaces
             */
            menuPrimefaces.append("\n            <h:form id=\"menuForm\" rendered=\"#{loginBean.logeado}\">");
//            menuPrimefaces.append("\n<h:form >");
            if (tipoMenu.equals("MenuBar")) {
                menuPrimefaces.append("\n              <p:menubar autoDisplay=\"true\" rendered=\"#{loginBean.logeado}\">");
            } else {
                menuPrimefaces.append("\n              <p:megaMenu rendered=\"#{loginBean.logeado}\">");
                menuPrimefaces.append("\n                  <p:submenu label=\"Menu\" rendered=\"#{loginBean.logeado}\">");
            }

            String linkmenu = "";
            Integer contadorColumnasMegaMenu = -1;
            Integer contadorSubMenu = 0;
            Integer contadorNuevaColumna = 0;
            String icono = "";
            // for (String archivo : archivoList) {
            String archivo;

            for (Clases clases : listClases) {
                archivo = clases.getClase();
                if (archivo.indexOf('.') == -1) {
                    if (tipoMenu.equals("MegaMenu")) {
                        contadorColumnasMegaMenu++;
                        switch (contadorColumnasMegaMenu) {
                            case 0:
                                menuPrimefaces.append("\n           <p:column>");
                                break;
                            case 3:
                                menuPrimefaces.append("\n           </p:column>");
                                contadorNuevaColumna++;
                                if (contadorNuevaColumna.equals(3)) {
                                    menuPrimefaces.append("\n         </p:submenu>");
                                    menuPrimefaces.append("\n         <p:submenu label=\"Menu\" >");
                                    contadorNuevaColumna = 0;
                                }
                                menuPrimefaces.append("\n           <p:column>");
                                contadorColumnasMegaMenu = 0;
                                break;
                            default:
                        }
                    }

                    //String ldirectorio = MySession.getWeb() + MySession.getFileSeparator() + "page" + MySession.getFileSeparator() + Utilidades.convertirLetraMayuscula(archivo);                    
                    String ldirectorio = MySession.getWeb() + MySession.getFileSeparator() + "page" + MySession.getFileSeparator() + archivo.toLowerCase();
                    String rendered = "menuBeans." + Utilidades.convertirLetraMinuscula(archivo) + "Menu";
                    menuPrimefaces.append("\n         <p:submenu label=\"" + "#{mensajes['tabla.").append(archivo).append("']}" + "\" rendered=\"#{").append(rendered).append("}\"> ");
                    String lrendered;
                    String crudSpanish = "";

                    if (Utilidades.crearDirectorio(ldirectorio)) {
                        String nombreClaseServices;
                        String nombreClaseController;
                        String nombreClaseData;
                        String nombreClase = archivo.toLowerCase();
                        nombreClaseController = Utilidades.convertirLetraMinuscula(archivo) + "Controller";
                        nombreClaseServices = Utilidades.convertirLetraMinuscula(archivo) + "Services";
                        nombreClaseData = Utilidades.convertirLetraMinuscula(archivo) + "Data";
                        /*
                         * composite
                         */
                        JsfComposite jsfComposite = new JsfComposite();
                        jsfComposite.setRutaArchivoWeb(ldirectorio + MySession.getFileSeparator() + archivo.toLowerCase() + ".xhtml");
                        lrendered = Utilidades.convertirLetraMinuscula(archivo);
                        jsfComposite.setFormRendered("\"#{menuBeans." + lrendered + "}\"");
                        jsfComposite.setFormNotRendered("\"#{!menuBeans." + lrendered + "}\"");

                        jsfComposite.setNombreClase(archivo.toLowerCase());
                        jsfComposite.setNombreClaseServices(nombreClaseServices);
                        jsfComposite.setNombreClaseData(nombreClaseData);

                        jsfComposite.setRutaArchivoBeans(Directorios.getDirectorioBeans() + MySession.getFileSeparator() + Utilidades.convertirLetraMayuscula(archivo) + ".java");
                        jsfComposite.makeCompositeJSF();

                        for (String lcrud : crud) {
                            crudSpanish = MySession.crudEnglishToSpanish(lcrud);


                            String rutaArchivoWeb = ldirectorio + MySession.getFileSeparator() + archivo.toLowerCase() + lcrud.toLowerCase() + ".xhtml";
                            String rutaArchivoBeans = Directorios.getDirectorioBeans() + MySession.getFileSeparator() + Utilidades.convertirLetraMayuscula(archivo) + ".java";
                            String rutaArchivoController = Directorios.getDirectorioBeans() + MySession.getFileSeparator() + Utilidades.convertirLetraMayuscula(archivo) + "Controller.java";

                            /*
                             * registrando el texto y el enlace para el menu
                             */
                            String value = nombreClase + lcrud;
                            lrendered = Utilidades.convertirLetraMinuscula(archivo);

                            String url = "/page/" + archivo.toLowerCase() + "/" + archivo.toLowerCase() + lcrud.toLowerCase() + ".xhtml";
                            String urlFaces = "/faces/page/" + archivo.toLowerCase() + "/" + archivo.toLowerCase() + lcrud.toLowerCase() + ".xhtml";
                            rendered = "menuBeans." + Utilidades.convertirLetraMinuscula(archivo) + crudSpanish;

                            if (lcrud.equals("List") && clases.getListar()) {
                                menuLinks.append("\n<h:link outcome=\"" + url + "\" value=\"" + value + "\" rendered=\"#{" + rendered + "}\"></h:link>");
                            }


                            if (lcrud.equals("Insert")) {
                                icono = "ui-icon-plus";
                            } else {
                                if (lcrud.equals("Query")) {
                                    icono = "ui-icon-pencil";
                                } else {
                                    if (lcrud.equals("List")) {
                                        icono = "ui-icon-document";
                                    }
                                }
                            }
                            if (lcrud.equals("List") && clases.getListar()) {
                                //       menuPrimefaces.append("\n                       <p:menuitem value=\"" + "#{mensajes['menu." + lcrud.toLowerCase() + "']}" + "\"   url=\"" + urlFaces + "\" rendered=\"#{" + rendered + "}\" icon=\"" + icono + "\" />");
                                menuPrimefaces.append("\n                       <p:menuitem value=\"" + "#{mensajes['menu.records']}" + "\"   url=\"").append(urlFaces).append("\" rendered=\"#{").append(rendered).append("}\" icon=\"").append(icono).append("\" />");
                            } else {
                                if (lcrud.equals("Query") && clases.getEditar()) {
//                                    menuPrimefaces.append("\n                   <p:menuitem value=\"" + "#{mensajes['menu." + lcrud.toLowerCase() + "']}" + "\" url=\"" + urlFaces + "\" rendered=\"#{" + rendered + "}\" icon=\"" + icono + "\" />");
                                } else {
                                    if (lcrud.equals("Insert") && clases.getCrear()) {
                                        //menuPrimefaces.append("\n               <p:menuitem value=\"" + "#{mensajes['menu." + lcrud.toLowerCase() + "']}" + "\" url=\"" + urlFaces + "\" rendered=\"#{" + rendered + "}\" icon=\"" + icono + "\" />");
                                        menuPrimefaces.append("\n               <p:menuitem value=\"" + "#{mensajes['menu.").append(lcrud.toLowerCase()).append("']}" + "\" action=\"#{").append(archivo.toLowerCase()).append("Services.prepareCreate()}\" rendered=\"#{").append(rendered).append("}\" icon=\"").append(icono).append("\" />");
                                    }

                                }

                            }

                            /*
                             * agrego los componentes a la clase
                             */


                            JsfPaginas jsfxhtml = new JsfPaginas();
                            jsfxhtml.setFormRendered("\"#{menuBeans." + lrendered + crudSpanish + "}\"");
                            jsfxhtml.setFormNotRendered("\"#{!menuBeans." + lrendered + crudSpanish + "}\"");
                            jsfxhtml.setTipoCrud(crudSpanish);
                            jsfxhtml.setBotonEnglish(lcrud.toLowerCase());
                            jsfxhtml.setBoton(crudSpanish.toLowerCase());

                            jsfxhtml.setNombreClase(archivo.toLowerCase());
                            jsfxhtml.setNombreClaseServices(nombreClaseServices);
                            jsfxhtml.setNombreClaseData(nombreClaseData);
                            jsfxhtml.setRutaArchivoWeb(rutaArchivoWeb);
                            jsfxhtml.setRutaArchivoBeans(rutaArchivoBeans);
                            if (lcrud.equals("Insert")) {
                                if (clases.getCrear()) {
                                    jsfxhtml.makeFileJSF();
                                }
                            }
                            if (lcrud.equals("Query")) {
                                if (clases.getEditar()) {
                                    jsfxhtml.makeFileJSF();
                                }
                            }

                            if (lcrud.equals("List")) {
                                JsfListPaginas jsfListPaginas = new JsfListPaginas();
                                jsfListPaginas.setFormRendered("\"#{menuBeans." + lrendered + crudSpanish + "}\"");
                                jsfListPaginas.setFormNotRendered("\"#{!menuBeans." + lrendered + crudSpanish + "}\"");
                                jsfListPaginas.setTipoCrud(crudSpanish);
                                jsfListPaginas.setBotonEnglish(lcrud.toLowerCase());
                                jsfListPaginas.setBoton(crudSpanish.toLowerCase());

                                jsfListPaginas.setNombreClase(archivo.toLowerCase());
                                jsfListPaginas.setNombreClaseServices(nombreClaseServices);
                                jsfListPaginas.setNombreClaseData(nombreClaseData);
                                jsfListPaginas.setRutaArchivoWeb(rutaArchivoWeb);
                                jsfListPaginas.setRutaArchivoBeans(rutaArchivoBeans);
                                if (clases.getListar()) {
                                    jsfListPaginas.makeFileJSF();
                                }
                            }


                        }//for crud
                        menuPrimefaces.append("\n           </p:submenu>");
                    } else {
                        DGlobal.advertencia(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_NoDirectorio") + Utilidades.convertirLetraMayuscula(archivo));
                    }
                }
            }//for archivolist

            if (tipoMenu.equals("MenuBar")) {
                menuPrimefaces.append("\n              </p:menubar>");
            } else {
                menuPrimefaces.append("\n            </p:column>");
                menuPrimefaces.append("\n           </p:submenu>");
                menuPrimefaces.append("\n        </p:megaMenu>");
            }
            menuPrimefaces.append("\n       </h:form>");

            /*
             * crear directorio login
             */
            String dirLogin = MySession.getWeb() + MySession.getFileSeparator() + "page" + MySession.getFileSeparator() + "login";
            if (Utilidades.crearDirectorio(dirLogin)) {
            }
            /*
             * ARCHIVO MENU
             */
            String rutaArchivoMenu = Directorios.getDirectorioMenu() + MySession.getFileSeparator() + "menu.xhtml";

            File fichero = new File(rutaArchivoMenu);
            if (!fichero.exists()) {
                MySession.setSobreEscribirMenu(Boolean.TRUE);
            }


            if (MySession.getSobreEscribirMenu()) {
                Menuxhtml.AddMenuFile(menuPrimefaces.toString(), rutaArchivoMenu);
                //        Menuxhtml.AddMenuFile(menuPrimefaces.toString(), dir + MySession.getFileSeparator() + "menu.xhtml");
            }

            /*
             * crea el index
             */
            if (MySession.getSobreEscribirIndex()) {
                IndexXhmtl.addIndexFile();
            }
            /*
             * crea la pagina sessionexpired.xhtml
             */
            SessionExpiredXhtml.addSessionExpiredFile();

            Loginxhtml.AddLoginxhtmlFile(dirLogin + MySession.getFileSeparator() + "login.xhtml");
            /*
             * 
             */
            MyPrimeFaces.topPlantilla(menuPrimefaces.toString());
            /*
             * Crear archivo JSFUtils.java
             */
            String directorioGeneral = Directorios.getDirectorioGenerales() + MySession.getFileSeparator() + "JSFUtil.java";
            /*
             * update temas primefaces
             */


            if (!DGlobal.getTemaInicial().equals(temaPrimefaces)) {

                UpdateTemasWebXML updateTemasWebXML = new UpdateTemasWebXML();

                updateTemasWebXML.actualizarTemaWEBXML(MySession.getTemaPrimefaces());


                UpdateCambiadorTemas updateCambiadorTemas = new UpdateCambiadorTemas();

                updateCambiadorTemas.actualizarTema(MySession.getTemaPrimefaces());

            }

            DGlobal.informacion(NbBundle.getMessage(JmoordbJSFGeneratorWizardIterator.class, "Mensajes_Completo"));


        } catch (Exception ex) {
            DGlobal.error("Generar() " + ex.getLocalizedMessage());
        }
    }

    private void habilitarPaginasTable() {
        try {
            listClases.removeAll(listClases);
            String tempName, temp;
            selectedCreate = Boolean.FALSE;
            selectedEdit = Boolean.FALSE;
            selectedList = Boolean.FALSE;
            selectedPage = Boolean.FALSE;
            selected = Boolean.FALSE;
            for (int i = 0; i < DGlobal.getModelo().getRowCount(); i++) {
                tempName = DGlobal.getModelo().getValueAt(i, 0).toString();
                selectedCreate = Boolean.FALSE;
                selectedEdit = Boolean.FALSE;
                selectedList = Boolean.FALSE;
                temp = DGlobal.getModelo().getValueAt(i, 1).toString();
                if (temp.equals("true")) {
                    selectedCreate = Boolean.TRUE;
                }
                temp = DGlobal.getModelo().getValueAt(i, 2).toString();
                if (temp.equals("true")) {
                    selectedEdit = Boolean.TRUE;
                }
                temp = DGlobal.getModelo().getValueAt(i, 3).toString();
                if (temp.equals("true")) {
                    selectedList = Boolean.TRUE;
                }
                selectedPage = Boolean.TRUE;
                Clases clases = new Clases();
                clases.setClase(tempName);
                clases.setCrear(selectedCreate);
                clases.setEditar(selectedEdit);
                clases.setListar(selectedList);
                listClases.add(clases);

            }

        } catch (Exception e) {
            DGlobal.error("habilitarPaginasTable()" + e.getLocalizedMessage());
        }
    }

    /*
     * verifica que el archivo web.xml contenga el <res-ref-name>jdbc/ es decir
     * que se haya establecido la conexion a la base datos
     */
    private boolean isContextXMLJDBC(String rutaFile) {
        try {

            return Utilidades.encontrarTextoArchivo(rutaFile, "<Resource name=\"jdbc/");

        } catch (Exception ex) {
            DGlobal.error("isContextXMLJDBC() " + ex.getLocalizedMessage());
        }
        return false;
    }

    private boolean isWebXMLJDBC(String rutaFile) {
        try {

            return Utilidades.encontrarTextoArchivo(rutaFile, "<res-ref-name>jdbc/");

        } catch (Exception ex) {
            DGlobal.error("isWebXMLJDBC " + ex.getLocalizedMessage());
        }
        return false;
    }
}
