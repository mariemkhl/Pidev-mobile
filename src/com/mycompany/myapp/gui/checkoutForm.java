/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Paiment;
import com.mycompany.myapp.services.ServicePaiment;

import java.util.List;

/**
 *
 * @author bhk
 */
public class checkoutForm extends BaseForm {
private ServicePaiment ServicePaiment;


    public checkoutForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        
         initGuiBuilderComponents(resourceObjectInstance);
        setLayout(BoxLayout.y());
        setScrollableY(true);
        getContentPane().setScrollVisible(false);
        getToolbar().setUIID("Container");
        Button b = new Button(" ");
        b.setUIID("Container");
        getToolbar().setTitleComponent(b);
        getTitleArea().setUIID("Container");
        installSidemenu(resourceObjectInstance);
        ServicePaiment = ServicePaiment.getInstance();
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("Payer");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "Payer", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("Payer");

    }



//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    public com.codename1.ui.TextField numCarte = new com.codename1.ui.TextField();
    public com.codename1.ui.TextField nomCarte = new com.codename1.ui.TextField();
    public com.codename1.ui.TextField cvCode = new com.codename1.ui.TextField();
    public com.codename1.ui.TextField prixTot = new com.codename1.ui.TextField();

    private com.codename1.ui.Button valider = new com.codename1.ui.Button();

    checkoutForm() {

    }
    

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        valider.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {

        private com.codename1.ui.Component cmp;

        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == valider) {
                validerActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }

private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    List<String> categoryList;

    guiBuilderBindComponentListeners();
    setLayout(new com.codename1.ui.layouts.BorderLayout());
    setTitle("Payer");
    setName("Payer");
    addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
    gui_Container_1.setScrollableY(true);
    gui_Container_1.setName("Container_1");

    // Create a center-aligned box layout for the container
    gui_Container_1.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    gui_Container_1.getStyle().setAlignment(Component.CENTER);

    // Create a container to hold the text fields
    Container fieldsContainer = new Container(new FlowLayout(Component.CENTER));
    fieldsContainer.setName("Fields_Container");

    fieldsContainer.addComponent(numCarte);
    fieldsContainer.addComponent(nomCarte);
    fieldsContainer.addComponent(cvCode);

    fieldsContainer.addComponent(prixTot);

    // Add the fields container to the main container
    gui_Container_1.addComponent(fieldsContainer);

    // Create a container for the button
    Container buttonContainer = new Container(new FlowLayout(Component.CENTER));
    buttonContainer.setName("Button_Container");

    buttonContainer.addComponent(valider);

    // Add the button container to the main container
    gui_Container_1.addComponent(buttonContainer);

    numCarte.setText("numCarte");
    nomCarte.setText("nomCarte");
    cvCode.setText("cvCode");
    
    prixTot.setText("prixTot");
    gui_Label_1.setUIID("CenterLabel");
    gui_Label_1.setName("Label_1");
    valider.setText("Payer");

}
 // </editor-fold> // </editor-fold>

    public void validerActionEvent(com.codename1.ui.events.ActionEvent ev) {
        Paiment r = new Paiment();
        r.setPrixTot(Integer.parseInt(prixTot.getText()));
        r.setNomCarte(nomCarte.getText());
         r.setNumCarte(Integer.parseInt(numCarte.getText()));
        r.setCvCode(Integer.parseInt(cvCode.getText()));
             

        if (ServicePaiment.addPaiment(r)) {
            Dialog.show("Success", "paiment a été effectué", "OK", null);

       
        } else {
            Dialog.show("Error", "Failed to add paiment", "OK", null);
        }

    }

//-- DON'T EDIT ABOVE THIS LINE!!!
//    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
//        new InboxForm().show();
//    }
}

