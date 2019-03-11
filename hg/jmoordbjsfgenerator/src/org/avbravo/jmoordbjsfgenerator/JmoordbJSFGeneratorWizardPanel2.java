/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class JmoordbJSFGeneratorWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private JmoordbJSFGeneratorVisualPanel2 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public JmoordbJSFGeneratorVisualPanel2 getComponent() {
        if (component == null) {
            component = new JmoordbJSFGeneratorVisualPanel2();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {

        // If it is always OK to press Next or Finish, then:

        return true;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
        String facelesTemplate = (String) wiz.getProperty("facelesTemplate");
        if (!facelesTemplate.equals("")) {
            ((JmoordbJSFGeneratorVisualPanel2) getComponent()).getjTextFieldCodeTemplate().setText(facelesTemplate);
            ((JmoordbJSFGeneratorVisualPanel2) getComponent()).getjLabelTemplateSelected().setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/avbravo/jsfgenerator/resources/layout/" + facelesTemplate + ".png")));
        }

        // NOI18N

    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        ((WizardDescriptor) wiz).putProperty("facelesTemplate", ((JmoordbJSFGeneratorVisualPanel2) getComponent()).getjTextFieldCodeTemplate().getText());
        DGlobal.setTemplate(getComponent().getjTextFieldCodeTemplate().getText());

        DGlobal.setReplaceIndex(getComponent().getjRadioButtonChangeTemplateYes().isSelected());
        DGlobal.setReplaceMenu(getComponent().getjRadioButtonChangeMenuYes().isSelected());
        DGlobal.setReplaceTemplate(getComponent().getjRadioButtonChangeTemplateYes().isSelected());

    }
}
