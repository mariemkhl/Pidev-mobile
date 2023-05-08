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

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Properties;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class conf_reclamation extends com.codename1.ui.Form {
    
private ServiceReclamation ServiceReclamation;
  
    
    


    public conf_reclamation(com.codename1.ui.util.Resources resourceObjectInstance) {
        ServiceReclamation = ServiceReclamation.getInstance();
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("AddFormTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "AddFormTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("Votre réclamation a été ajoutée");

    }



//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField Email = new com.codename1.ui.TextField();
   
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
        setTitle("Votre réclamation a été ajoutée");
        setName("Votre réclamation a été ajoutée");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        gui_Component_Group_1.setName("Component_Group_1");

        gui_Component_Group_1.addComponent(Email);
        gui_Component_Group_1.setName("Email");
              

        Email.setText("saissez votre mail");
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Component_Group_1.setName("Component_Group_1");
         valider.setText("ADD ");
        gui_Container_1.addComponent(valider);

    } // </editor-fold> // </editor-fold>

    public void validerActionEvent(com.codename1.ui.events.ActionEvent ev) {


    }

//
//    private final String username;
//    private final String password;
//    private final Properties properties;
//
//    public EmailAPI(String username, String password) {
//        this.username = username;
//        this.password = password;
//
//       
//    Properties props = new Properties();
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "true");
//    props.put("mail.smtp.host", "smtp.gmail.com");
//    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//    props.put("mail.smtp.ssl.protocols","TLSv1.2");
//    props.put("mail.smtp.port", "587");
//    
//  
//         String from = "yessmine.gsouri@esprit.tn";
//    final String username = "yessmine.gsouri@esprit.tn";
//    System.out.println("preparing to send email");
//           
//    String to = tfconf.getText();
//         
//    String myAccountEmail="yessmine.gsouri@esprit.tn";
//    String password="223AFT1624";
//    
//    Session session = Session.getInstance(props, new Authenticator() {
//        @Override
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(myAccountEmail, password);
//        }
//    });       
//    }
//
//    try {
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(from));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//        message.setSubject("Confirmation de récéption de réclamation"); 
//        
//        // Create a MimeMultipart to contain the content of the email
//        MimeMultipart multipart = new MimeMultipart();
//
//        // Create a new MimeBodyPart for the content with the desired font
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        String messageContent = "<p style=\"font-family: Arial, sans-serif;\">Votre Réclamation a été bien reçue. Nous allons la prendre en considération. <br> Merci pour votre patience <br> L'équipe de Artisty</p>";
//        messageBodyPart.setContent(messageContent, "text/html");
//
//        // Add the MimeBodyPart to the MimeMultipart
//        multipart.addBodyPart(messageBodyPart);
//
//        // Create a new MimeBodyPart for the image
//        MimeBodyPart imagePart = new MimeBodyPart();
//
//        // Set the image file location
//        String imagePath = "C:\\Users\\user\\Desktop\\logo.png";
//
//        // Add the image file to the MimeBodyPart
//        imagePart.attachFile(imagePath);
//
//        // Add the MimeBodyPart to the MimeMultipart
//        multipart.addBodyPart(imagePart);
//
//        // Set the content of the message to the MimeMultipart
//        message.setContent(multipart);
//
//        Transport.send(message);
//        JOptionPane.showMessageDialog(null, "Message sent successfully!");
//    } catch (MessagingException e) {
//        JOptionPane.showMessageDialog(null, "Error sending message: " + e.getMessage());
//    } catch (IOException e) {
//        JOptionPane.showMessageDialog(null, "Error attaching image: " + e.getMessage());
//    }
//}

}


