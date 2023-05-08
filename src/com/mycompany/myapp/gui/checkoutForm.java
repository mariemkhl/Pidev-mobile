/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import com.mycompany.myapp.entities.Paiment;
import com.mycompany.myapp.services.ServicePaiment;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bhk
 */
public class checkoutForm extends com.codename1.ui.Form {
private ServicePaiment ServicePaiment;


    public checkoutForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        ServicePaiment = ServicePaiment.getInstance();
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("AddFormTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "AddFormTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("Ajouter une réclamation");

    }



//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField numCarte = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField nomCarte = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField cvCode = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField prixTot = new com.codename1.ui.TextField();
      private com.codename1.ui.TextField dateEx = new com.codename1.ui.TextField();
//    private com.codename1.ui.TextField dateachatField = new com.codename1.ui.TextField();
    private com.codename1.ui.Button valider = new com.codename1.ui.Button();
    

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
        setTitle("ajouter une réclamation");
        setName("ajouter une réclamation");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        gui_Component_Group_1.setName("Component_Group_1");

        gui_Component_Group_1.addComponent(numCarte);
        gui_Component_Group_1.setName("numCarte");
        gui_Component_Group_1.addComponent(nomCarte);
        gui_Component_Group_1.setName("nomCarte");
        gui_Component_Group_1.addComponent(cvCode);
        gui_Component_Group_1.setName("cvCode");
        gui_Component_Group_1.addComponent(dateEx);
        gui_Component_Group_1.setName("dateEx");
        gui_Component_Group_1.addComponent(prixTot);
        gui_Component_Group_1.setName("prixTot");


        numCarte.setText("numCarte");
        nomCarte.setText("nomCarte");
        cvCode.setText("cvCode");
        dateEx.setText("dateEx");
        prixTot.setText("prixTot");
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Component_Group_1.setName("Component_Group_1");
         valider.setText("ADD ");
        gui_Container_1.addComponent(valider);

    } // </editor-fold> // </editor-fold>

    public void validerActionEvent(com.codename1.ui.events.ActionEvent ev) {
        Paiment r = new Paiment();
        r.setPrixTot(Integer.parseInt(prixTot.getText()));
        r.setNomCarte(nomCarte.getText());
         r.setNumCarte(Integer.parseInt(numCarte.getText()));
        r.setCvCode(Integer.parseInt(cvCode.getText()));
        String dateStr = dateEx.getText();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as per your needs
try {
    Date date = dateFormat.parse(dateStr);
    r.setDateEx(date);
} catch (ParseException e) {
    e.printStackTrace(); // Handle the parse exception if necessary
}

      

        if (ServicePaiment.addPaiment(r)) {
            Dialog.show("Success", "paiment added successfully", "OK", null);
       
        } else {
            Dialog.show("Error", "Failed to add paiment", "OK", null);
        }

    }

//-- DON'T EDIT ABOVE THIS LINE!!!
//    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
//        new InboxForm().show();
//    }
}

