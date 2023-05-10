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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.gui.SplashForm;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.List;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.Display;


/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */public class recForm extends BaseForm {
     
         Container imageContainer;

    Reclamation r = new Reclamation();
    private ServiceReclamation ServiceReclamation;


    public recForm(com.codename1.ui.util.Resources resourceObjectInstance) {
          guiBuilderBindComponentListeners();
    setLayout(new com.codename1.ui.layouts.BorderLayout());
    setTitle("ajouter une réclamation");
    setName("ajouter une réclamation");
  ServiceReclamation = ServiceReclamation.getInstance();
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("reclamation");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "reclamation", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("reclamation");
        
    }



//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    public com.codename1.ui.TextField commentaire = new com.codename1.ui.TextField();
    public com.codename1.ui.TextField typeReclamation = new com.codename1.ui.TextField();
    private com.codename1.ui.Button ajouter = new com.codename1.ui.Button();
    
   
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    recForm() {
    }
   
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        ajouter.addActionListener(callback);
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

            if (sourceComponent == ajouter) {
                ajouterActionEvent(ev);
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

    // Create a center-aligned box layout for the container
    gui_Container_1.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    gui_Container_1.getStyle().setAlignment(Component.CENTER);

    // Create a container to hold the text fields
    Container fieldsContainer = new Container(new FlowLayout(Component.CENTER));
    fieldsContainer.setName("Fields_Container");

    fieldsContainer.addComponent(commentaire);
    fieldsContainer.addComponent(typeReclamation);
   

    // Add the fields container to the main container
    gui_Container_1.addComponent(fieldsContainer);

    // Create a container for the button
    Container buttonContainer = new Container(new FlowLayout(Component.CENTER));
    buttonContainer.setName("Button_Container");

    buttonContainer.addComponent(ajouter);



    // Add the button container to the main container
    gui_Container_1.addComponent(buttonContainer);

    commentaire.setText("c'est quoi votre problème");
    typeReclamation.setText("dites-nous plus");

    

    gui_Label_1.setUIID("CenterLabel");
    gui_Label_1.setName("Label_1");
    ajouter.setText("ajouter votre réclamation");


}
 // </editor-fold> // </editor-fold>

 public void ajouterActionEvent(com.codename1.ui.events.ActionEvent ev) {
    Reclamation r = new Reclamation();
    r.setCommentaire(commentaire.getText());
    r.setTypereclamation(typeReclamation.getText());

    if (ServiceReclamation.addReclamation(r)) {
        Dialog.show("Success", "Your Reclamation has been submitted", "OK", null);
        new Display(commentaire.getText(), typeReclamation.getText()).show();
    } else {
        Dialog.show("Error", "Failed to add Reclamation", "OK", null);
    }
}


    }

//-- DON'T EDIT ABOVE THIS LINE!!!
//    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
//        new InboxForm().show();
//    }
