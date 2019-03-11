/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avbravo.jmoordbjsfgenerator;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class JmoordbJSFGeneratorWizardPanel4 implements WizardDescriptor.Panel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private JmoordbJSFGeneratorVisualPanel4 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public JmoordbJSFGeneratorVisualPanel4 getComponent() {
        if (component == null) {
            component = new JmoordbJSFGeneratorVisualPanel4();
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
        try {
            if (DGlobal.getTemplate().equals("")) {

                return false;
            }
        } catch (Exception e) {
        }
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
        String rowsDataTable = (String) wiz.getProperty("rowsDataTable");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerRowsDataTable().setValue(Integer.parseInt(rowsDataTable));
        String typeRelationsDataTable = (String) wiz.getProperty("typeRelationsDataTable");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjComboBoxDataTable().setSelectedItem(typeRelationsDataTable);
        String tableScrollable = (String) wiz.getProperty("tableScrollable");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjCheckBoxTableScrollable().setSelected(tableScrollable.equals("true") ? true : false);
        String dialogWidth = (String) wiz.getProperty("dialogWidth");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerDialogWidth().setValue(Integer.parseInt(dialogWidth));
        String dialogWidthRelation = (String) wiz.getProperty("dialogWidthRelation");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerDialogWidthRelation().setValue(Integer.parseInt(dialogWidthRelation));
        String dialogDinamyc = (String) wiz.getProperty("dialogDinamyc");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjCheckBoxDialogDinamyc().setSelected(dialogDinamyc.equals("true") ? true : false);
        String dialogCustomized = (String) wiz.getProperty("dialogCustomized");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjCheckBoxDialogCustomized().setSelected(dialogCustomized.equals("true") ? true : false);
        String dialogHeight = (String) wiz.getProperty("dialogHeight");
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerDialogHeigth().setValue(Integer.parseInt(dialogHeight));
        ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerDialogHeigth().setEnabled(dialogCustomized.equals("true") ? true : false);


    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        // use wiz.putProperty to remember current panel state
        ((WizardDescriptor) wiz).putProperty("rowsDataTable", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerRowsDataTable().getValue());
        ((WizardDescriptor) wiz).putProperty("typeRelationsDataTable", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjComboBoxDataTable().getSelectedItem().toString());
        ((WizardDescriptor) wiz).putProperty("tableScrollable", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjCheckBoxTableScrollable().isSelected());
        ((WizardDescriptor) wiz).putProperty("dialogWidth", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerDialogWidth().getValue());
         ((WizardDescriptor) wiz).putProperty("dialogWidthRelation", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerDialogWidthRelation().getValue());
         ((WizardDescriptor) wiz).putProperty("dialogDinamyc", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjCheckBoxDialogDinamyc().isSelected());
         ((WizardDescriptor) wiz).putProperty("dialogCustomized", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjCheckBoxDialogCustomized().isSelected());
        ((WizardDescriptor) wiz).putProperty("dialogHeight", ((JmoordbJSFGeneratorVisualPanel4) getComponent()).getjSpinnerDialogHeigth().getValue());
      
    }
}
