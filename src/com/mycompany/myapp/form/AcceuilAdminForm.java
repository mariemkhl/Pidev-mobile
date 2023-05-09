/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.form;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entity.User;
import com.mycompany.myapp.service.User_Service;
import java.io.IOException;



/**
 *
 * @author Abderrazekbenhamouda
 */
public class AcceuilAdminForm  extends Form{
    
    
        Resources theme;

    public AcceuilAdminForm()  {
        super(BoxLayout.y());
        this.getToolbar();
        Label logi = new Label("LES UTILISATEURS");
        this.add(logi);

        theme = UIManager.initFirstTheme("/theme");

        for (User c : new User_Service().getAllusers()) {

            try {
                this.add(addItem(c));
            } catch (IOException ex) {
            }
           

        }

        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            new AcceuilAdminForm().show();
        });
    }

    public Container addItem(User c) throws IOException {
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Container photos = new Container(BoxLayout.y());
        try {
    Image lineImage = Image.createImage("/sepf.png"); // replace with your line image filename
    ScaleImageLabel sep = new ScaleImageLabel(lineImage);
    sep.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    sep.setPreferredSize(new Dimension(Display.getInstance().getDisplayWidth(), 80)); // set the height of the separator
    photos.add(sep);
} catch (IOException ex) {
    // handle the exception
}
        
        Label lab = new Label(c.getNom());
        Button btn = new Button(c.getEmail());
        


         cn2.add(lab).add(btn).add(photos);
        cn1.add(BorderLayout.WEST, cn2);

        btn.addActionListener(e -> {

            Form f2 = new Form(BoxLayout.y());
            Label AJOUT = new Label("DETAILS");
            AJOUT.setUIID("login");
            f2.add(AJOUT);

            Label USERNAME = new Label("USERNAME");
            Label lname = new Label(c.getNom());
            Label USERMAIL = new Label("USERMAIL");
            Label lemail = new Label(c.getEmail());
            Label USERNUMBER = new Label("USERNUMBER");
            Label lnum = new Label(String.valueOf(c.getNumTel()));
            Label USERAGE = new Label("ADRESS");
            Label lage = new Label(String.valueOf(c.getAdresse()));

           

            


            Button btn_desactiver_activer;
            
            if (c.getBlock()== 1) {
                btn_desactiver_activer = new Button("DESACTIVER");
            } else {
                btn_desactiver_activer = new Button("ACTIVER");
            }
            
            btn_desactiver_activer.addActionListener(l
                    -> {
                if (c.getBlock()== 1) {

                    new User_Service().DesactiverUser(c.getId());
                    Dialog.show("Desactivation", "Desactivation aves succsess ", "OK", null);

                    new AcceuilAdminForm().show();

                } else {
                    new User_Service().activerUser(c.getId());
                    Dialog.show("activation", "Activation aves succsess ", "OK", null);

                    new AcceuilAdminForm().show();

                }

            }
            );
            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.show();
            });
           
            f2.add(USERNAME).add(lname).add(USERMAIL).add(lemail).add(USERNUMBER).add(lnum).add(USERAGE).add(lage).add(btn_desactiver_activer);
            f2.show();

        });
this.getToolbar().addCommandToOverflowMenu("back", null, ev->{

    new AcceuilAdminForm().show();
});
        cn1.setLeadComponent(btn);
        return cn1;

    }
    
}
