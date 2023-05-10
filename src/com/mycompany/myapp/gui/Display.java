
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServicePaiment;
import java.util.List;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.confpaimentForm;
import com.mycompany.myapp.gui.Display;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



/**
 * GUI builder created Form
 * Confirmation de reclamation
 */
public class Display extends BaseForm {
    Container imageContainer;
    
    public Display(com.codename1.ui.util.Resources resourceObjectInstance) {

        initGuiBuilderComponents(resourceObjectInstance);
        guiBuilderBindComponentListeners();
setLayout(new com.codename1.ui.layouts.BorderLayout());
    setTitle("Confirmation de reclamation");
    setName("Confirmation de reclamation");
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("Payer");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "AddFormTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("ajouter reclamation");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    Display() {

    }
public Display(String commentaire, String typeReclamation) {
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    Label commentaireLabel = new Label("Commentaire: " + commentaire);
    Label typeReclamationLabel = new Label("Type de réclamation: " + typeReclamation);
    Button confirmationButton = new Button("Confirmation");
    TextField number = new TextField("votre numéro");
    Button revenir = new Button("revenir");

    Container container = getContentPane();
    container.addComponent(commentaireLabel);
    container.addComponent(typeReclamationLabel);
    container.addComponent(number);
    container.addComponent(confirmationButton);
    container.addComponent(revenir);

    confirmationButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                String accountSid = "ACe8e0b1a487f1f33d73e1e603879ed810";
                String authToken = "56070a2e2f3eaeff6c038e1523846add";
                String fromNumber = "+21654057529";
                String toNumber = number.getText(); // Get the value from the TextField
                String message = "Hello from Twilio!";

                // Initialize the Twilio client
                Twilio.init(accountSid, authToken);

                // Send the SMS
                Message.creator(new PhoneNumber(toNumber), new PhoneNumber(fromNumber), message).create();

                // Show a success message
                Dialog.show("SMS Sent", "The SMS has been sent successfully.", "OK", null);
            } catch (Exception e) {
                e.printStackTrace();
                Dialog.show("Error", "Failed to send SMS: " + e.getMessage(), "OK", null);
            }
        }
    });
}




    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();

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


        }
        
        

        @Override
        public void dataChanged(int type, int index) {
        }
    }
private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    List<String> categoryList;
   
    // Initialize the container and components
    gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    gui_Label_1 = new com.codename1.ui.Label();
    gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();

   

    addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
    gui_Container_1.setScrollableY(true);
    gui_Container_1.setName("Container_1");
    gui_Container_1.addComponent(gui_Label_1);
    gui_Container_1.addComponent(gui_Component_Group_1);

    gui_Component_Group_1.setName("Component_Group_1");
    imageContainer = new Container();
    imageContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

    gui_Component_Group_1.setName("Component_Group_1");

}


}
