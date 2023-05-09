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

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class conf_reclamation extends BaseForm {
    Container imageContainer;
    


    public conf_reclamation(com.codename1.ui.util.Resources resourceObjectInstance) {
   
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
        getToolbar().getTitleComponent().setUIID("Recevoir une notification");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "Recevoir une notification", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("");
    }



//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.Label labelEmail= new com.codename1.ui.Label();
    private com.codename1.ui.TextField Email = new com.codename1.ui.TextField();
    
//    private com.codename1.ui.TextField dateachatField = new com.codename1.ui.TextField();
    private com.codename1.ui.Button valider = new com.codename1.ui.Button();
     private com.codename1.ui.Button revenir = new com.codename1.ui.Button();

    conf_reclamation() {
        
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

        @Override
        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == valider) {
                validerActionEvent();
            }
        }

        @Override
        public void dataChanged(int type, int index) {
        }
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        List<String> categoryList;

        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Confirmation de reclamation");
        setName("Confirmation de reclamation");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(labelEmail);
        gui_Component_Group_1.setName("Email de confirmation");
        gui_Component_Group_1.addComponent(Email);
        gui_Component_Group_1.setName("");
        

      
        imageContainer = new Container();
        imageContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));



        Email.setText("");
        
        labelEmail.setUIID("");
        gui_Label_1.setName("Email de confirmation");
        gui_Component_Group_1.setName("Component_Group_1");
         valider.setText("Valider ");
        gui_Container_1.addComponent(valider);
           revenir.setText("page de réclamation ");
        gui_Container_1.addComponent(revenir);
    
    

    } // </editor-fold> // </editor-fold>
    
          public void RevenirActionEvent() {
            
            recForm recForm = new recForm();
              recForm.show();
          }

    public void validerActionEvent() {
        
}

    }




