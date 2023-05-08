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

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.gui.SplashForm;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class recForm extends com.codename1.ui.Form {
private ServiceReclamation ServiceReclamation;
  
    private Container imageContainer;
    


    public recForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        ServiceReclamation = ServiceReclamation.getInstance();
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
    private com.codename1.ui.TextField Commentaire = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField typeReclamation = new com.codename1.ui.TextField();
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

        gui_Component_Group_1.addComponent(Commentaire);
        gui_Component_Group_1.setName("commentaireField");
        gui_Component_Group_1.addComponent(typeReclamation);
        gui_Component_Group_1.setName("typereclamationField");
      
        imageContainer = new Container();
        imageContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));



        Commentaire.setText("commentaire");
        typeReclamation.setText("type reclamation");
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Component_Group_1.setName("Component_Group_1");
         valider.setText("ADD ");
        gui_Container_1.addComponent(valider);

    } // </editor-fold> // </editor-fold>

    public void validerActionEvent(com.codename1.ui.events.ActionEvent ev) {
        Reclamation r = new Reclamation();
        r.setCommentaire(Commentaire.getText());
        r.setTypereclamation(typeReclamation.getText());

        if (ServiceReclamation.addReclamation(r)) {
            Dialog.show("Success", "reclamation added successfully", "OK", null);
            
       
        } else {
            Dialog.show("Error", "Failed to add reclamation", "OK", null);
        }

    }

//-- DON'T EDIT ABOVE THIS LINE!!!
//    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
//        new InboxForm().show();
//    }
}



