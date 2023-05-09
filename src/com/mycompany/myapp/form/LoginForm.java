/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.form;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.Session;
import java.io.IOException;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class LoginForm extends Form{
    
        Form h = this;
    Resources theme;
        String url = "http://127.0.0.1/cnx.php";

    public LoginForm() {
        super( BoxLayout.y());

        TextField txtn, txtpass;
        Button btnvalid, btninscrire;

        Label logi = new Label("ACCOUNT LOGIN");
        Label labUser = new Label("USERNAME");
        txtn = new TextField("", "Username", 5, TextArea.ANY);
        Label labpASSWORD = new Label("PASSWORD");
        txtpass = new TextField("", "Password", 5, TextArea.PASSWORD);
        btnvalid = new Button("Valider");
        btninscrire = new Button("Sign up");

        Label a = new Label("                                      ");
        Label b = new Label("    ");
        //   b.setUIID("bb");

        this.add(logi).add(labUser).add(txtn).add(labpASSWORD).add(txtpass).add(b).add(btnvalid).add(a).add(btninscrire);
                //.add(new ScaleImageLabel(GifImage.decode(getResourceAsStream("accueil.gif"), 1177720)));

       

        btninscrire.addActionListener(l
                -> {
            new SignUpForm().show();
        }
        );

        btnvalid.addActionListener(e -> {
            ConnectionRequest cnreq = new ConnectionRequest();
            cnreq.setPost(false);
            cnreq.setUrl(url);
            cnreq.addArgument("name", txtn.getText());
            cnreq.addArgument("password", txtpass.getText());
            cnreq.addResponseListener(ev -> {
                String chaine = new String(cnreq.getResponseData());
                System.out.println(chaine);
                if (chaine.equalsIgnoreCase("-1")) {

                    Dialog.show("Erreur", "Verifier votre USername and password", "OK", null);

                } else {
                    int id = Integer.valueOf(chaine);
                    Session.start(id);
                    System.out.println(Session.get());
                    if (Session.get().getBlock()== 1) {

                        if (Session.get().getRoles().equals("[ROLE_CLIENT, ROLE_USER]")) {
                            System.out.println("Hello User");
                           new AcceuilUserForm(this).show();

                        } 
                          
                        else {
                            System.out.println("Hello Admin");

                            new AcceuilAdminForm().show();

                        }

                    } else {
                        Dialog.show("Erreur", "Compte Desactive", "OK", null);
                    }

                }

            });
            NetworkManager.getInstance().addToQueueAndWait(cnreq);
        });

    }
}
