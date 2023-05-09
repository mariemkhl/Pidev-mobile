/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.form;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entity.User;
import com.mycompany.myapp.utils.Session;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class AcceuilUserForm extends Form{
    
    
        Resources theme = UIManager.initFirstTheme("/theme");

    public AcceuilUserForm(Form previous) {
          super( BoxLayout.y());
           this.getToolbar().setUIID("tb");
        Label logi = new Label("PROFIL");
        logi.setUIID("login");
        this.add(logi);
          User u = Session.get();
          Label LASTNAME = new Label("DOMAINE");
            Label lname = new Label( u.getDomaine());
             Label FIRSTNAME = new Label("FIRSTNAME");
            Label fname = new Label( u.getNom());
             Label USERMAIL = new Label("USERMAIL");
            Label lemail = new Label( u.getEmail());
             Label USERNUMBER = new Label("USERNUMBER");
            Label lnum = new Label(String.valueOf(u.getEmail()));

            Button btn = new Button("Edit");
             btn.setUIID("mod");
             
            LASTNAME.setUIID("type1");
            FIRSTNAME.setUIID("type1");
            USERMAIL.setUIID("type1");
            USERNUMBER.setUIID("type1");
            lname.setUIID("type2");
            fname.setUIID("type2");
            lemail.setUIID("type2");
            lnum.setUIID("type2");

             
  
            this.add(LASTNAME).add(lname).add(FIRSTNAME).add(fname).add(USERMAIL).add(lemail).add(USERNUMBER).add(lnum).add(btn);

            btn.addActionListener(ln
                    -> {

               new EditProfilForm(this).show();
            }
            );

            this.getToolbar().setUIID("tb");
             this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            previous.showBack();
        });
    }
    
    
}
