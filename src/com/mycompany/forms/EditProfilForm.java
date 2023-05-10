/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.forms;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.User;
import com.mycompany.services.User_Service;
import com.mycompany.utils.Session;
import java.util.Date;


/**
 *
 * @author Abderrazekbenhamouda
 */
public class EditProfilForm extends Form{
    
    Resources theme = UIManager.initFirstTheme("/theme");
    public EditProfilForm(Form previous) {
        super( BoxLayout.y());
        Label logi = new Label("EDIT Profil");
        this.add(logi);
        String url = "http://127.0.0.1/bike/mobile/cnx.php";
        AutoCompleteTextField FIRSTNAME = new AutoCompleteTextField(Session.get().getNom());
        FIRSTNAME.setMinimumElementsShownInPopup(1);
         FIRSTNAME.setUIID("txtn");

        AutoCompleteTextField LASTNAME = new AutoCompleteTextField(Session.get().getDomaine());
        LASTNAME.setMinimumElementsShownInPopup(1);
         LASTNAME.setUIID("txtn");

        AutoCompleteTextField USERNUMBER = new AutoCompleteTextField(String.valueOf(Session.get().getNumTel()));
        USERNUMBER.setMinimumElementsShownInPopup(1);
         USERNUMBER.setUIID("txtn");

                 AutoCompleteTextField ADRESS = new AutoCompleteTextField(Session.get().getAdresse());
        ADRESS.setMinimumElementsShownInPopup(1);
         ADRESS.setUIID("txtn");

  
        AutoCompleteTextField EMAIL = new AutoCompleteTextField(Session.get().getEmail());
        EMAIL.setMinimumElementsShownInPopup(1);
        EMAIL.setUIID("txtn");
             Picker  DATE = new Picker ();


        Button btn = new Button("Submit");
        
        Label des = new Label("FIRSTNAME");
                this.add(des).add(FIRSTNAME);

                Label ty = new Label("DOMAINE");
                this.add(ty).add(LASTNAME);

                Label QU = new Label("USERNUMBER");
                this.add(QU).add(USERNUMBER);

                   Label ad = new Label("ADRESSE");
                ad.setUIID("pass");

                
                Label LS1 = new Label("EMAIL");
                this.add(LS1).add(EMAIL);
                this.add("Date de Naissance : ").add(DATE);

       

        
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            previous.showBack();
        });
        
        
        
        
        
        

                       

                                Button submit = new Button("Submit");
                                this.add(submit);

                                this.revalidate();
this.getToolbar().addCommandToOverflowMenu("back", null, ev->{

    new AcceuilUserForm(previous).show();
});

                                submit.addActionListener(lll
                                        -> {
                                   
                                    User u = new User();
                                    u.setAdresse(ADRESS.getText());
                                    u.setEmail(EMAIL.getText());
                                    u.setNumTel(Integer.parseInt(USERNUMBER.getText()));
                                    u.setNom(FIRSTNAME.getText());
                                    u.setDomaine(LASTNAME.getText());
                                    if (new User_Service().ModifierUser(u, Session.get().getId()) == true) {
                                        
                                        new AcceuilUserForm(previous).show();
                                    } else {
                                        Dialog.show("Erreur", "Compte existe ", "OK", null);
                                    }

                                }
                                );

                            

                        

                
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
       

    }

    
}
