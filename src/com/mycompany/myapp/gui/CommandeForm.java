/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.ServiceCommande;
import java.util.List;


/**
 * A simple details form
 *
 * @author Shai Almog
 */
public class CommandeForm extends BaseForm {
    
    private ServiceCommande ServiceCommande;

    public CommandeForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public CommandeForm(com.codename1.ui.util.Resources resourceObjectInstance) {
     ServiceCommande = ServiceCommande.getInstance();
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("commander");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "commander", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("commander");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    public com.codename1.ui.TextField payment = new com.codename1.ui.TextField();
    public com.codename1.ui.TextField prixTot = new com.codename1.ui.TextField();

    private com.codename1.ui.Button valider = new com.codename1.ui.Button();
        private com.codename1.ui.Button payer = new com.codename1.ui.Button();

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
    setTitle("passer une commande");
    setName("passer une commande");
    addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
    gui_Container_1.setScrollableY(true);
    gui_Container_1.setName("Container_1");

    // Create a center-aligned box layout for the container
    gui_Container_1.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    gui_Container_1.getStyle().setAlignment(Component.CENTER);

    // Create a container to hold the text fields
    Container fieldsContainer = new Container(new FlowLayout(Component.CENTER));
    fieldsContainer.setName("Fields_Container");

    fieldsContainer.addComponent(payment);
    fieldsContainer.addComponent(prixTot);

    // Add the fields container to the main container
    gui_Container_1.addComponent(fieldsContainer);

    // Create a container for the button
    Container buttonContainer = new Container(new FlowLayout(Component.CENTER));
    buttonContainer.setName("Button_Container");

    buttonContainer.addComponent(valider);
     buttonContainer.addComponent(payer);

    // Add the button container to the main container
    gui_Container_1.addComponent(buttonContainer);

    payment.setText("payment");  
    prixTot.setText("prixTot");
    gui_Label_1.setUIID("CenterLabel");
    gui_Label_1.setName("Label_1");
    valider.setText("ajouter la commande");
    payer.setText("passer au paiment");
}
 // </editor-fold> // </editor-fold>

    public void validerActionEvent(com.codename1.ui.events.ActionEvent ev) {
        Commande r = new Commande();
        r.setPrixTot(Integer.parseInt(prixTot.getText()));
        r.setPayment(payment.getText());
         

        if (ServiceCommande.addCommande(r)) {
            Dialog.show("Success", "la commande a été bien ajoutée", "OK", null);

       
        } else {
            Dialog.show("Error", "Erreur", "OK", null);
        }

    }
    
        public void payerActionEvent(com.codename1.ui.events.ActionEvent ev) {
    checkoutForm checkoutForm = new checkoutForm();
              checkoutForm.show();

    }

//-- DON'T EDIT ABOVE THIS LINE!!!
//    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
//        new InboxForm().show();
//    }
}


