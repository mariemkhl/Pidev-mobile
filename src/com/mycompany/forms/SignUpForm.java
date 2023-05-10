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
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entity.User;
import com.mycompany.services.User_Service;
import java.util.Date;


/**
 *
 * @author Abderrazekbenhamouda
 */
public class SignUpForm extends Form{
    Resources theme;
    public SignUpForm() {
        super( BoxLayout.y());
       // String url = "http://127.0.0.1/bike/mobile/cnx.php";
        TextField FIRSTNAME = new TextField("", "First Name", 20, TextArea.TEXT_CURSOR);
        TextField LASTNAME = new TextField("", "DOMAINE", 20, TextArea.ANY);
        Label logi = new Label("ACCOUNT REGISTRATION ");
        TextField USERNUMBER = new TextField("", "Number", 20, TextArea.ANY);
        TextField USER_ADRESS = new TextField("", "Adresse", 80, TextArea.NUMERIC);
        TextField EMAIL = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField Password = new TextField("", "Password", 20, TextArea.PASSWORD);

        
        Label labFIRSTNAME = new Label("FIRSTNAME");
        Label labLASTNAME = new Label("DOMAINE");
        Label labEMAIL = new Label("EMAIL");
        Label labUSERAGE = new Label("ADRESSE");
        Label labUSERNAME = new Label("USERNAME");
        Label labPASSWORD = new Label("PASSWORD");
        Label labUSERNUMBER = new Label("USERNUMBER");



        this.add(labFIRSTNAME).add(FIRSTNAME);
        this.add(labLASTNAME).add(LASTNAME);
        this.add(labEMAIL).add(EMAIL);
        this.add(labUSERAGE).add(USER_ADRESS);
        this.add(labPASSWORD).add(Password);
        this.add(labUSERNUMBER).add(USERNUMBER);

       

                    Button btn = new Button("Submit");
                    this.add(btn);
                    this.revalidate();
                    btn.addActionListener(l
                            -> {
                        
                        // val firstname
                        Validator val_firstname = new Validator();
                        val_firstname.addConstraint(FIRSTNAME, new LengthConstraint(8));
                        String text_saisir_des_caracteres = "^[0-9]+$";
                        val_firstname.addConstraint(FIRSTNAME, new RegexConstraint(text_saisir_des_caracteres, ""));
                        // val lastname
                        Validator val_lastname = new Validator();
                        val_lastname.addConstraint(LASTNAME, new LengthConstraint(8));
                        val_lastname.addConstraint(LASTNAME, new RegexConstraint(text_saisir_des_caracteres, ""));
                        
                        String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                        
                        // val mail
                        Validator val_mail = new Validator();
                        val_mail.addConstraint(EMAIL, new LengthConstraint(8));
                        val_mail.addConstraint(EMAIL, new RegexConstraint(text_mail, ""));
                        // val age
                        Validator val_age = new Validator();
                        // valnumber
                        Validator val_number = new Validator();
                        val_number.addConstraint(USERNUMBER, new LengthConstraint(8));
                        val_number.addConstraint(USERNUMBER, new RegexConstraint(text_saisir_des_caracteres, ""));
                        
                        if (FIRSTNAME.getText().equals("")) {
                            Dialog.show("Erreur", "Champ vide de firstname ", "OK", null);
                            
                        } else if (val_firstname.isValid()) {
                            Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null); 
                            
                        } else if (LASTNAME.getText().equals("")) {
                            Dialog.show("Erreur", "Champ vide de lastname ", "OK", null);
                            
                        }else if (val_lastname.isValid()) {
                            Dialog.show("Erreur LASTNAME !", "il faut saisir des caracteres  !", "OK", null); 
                            
                        }
                        
                        
                        else if (EMAIL.getText().equals("")) {
                            Dialog.show("Erreur", "Champ vide de email ", "OK", null);
                            
                        } else if (!val_mail.isValid()) {
                            Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null); 
                            
                        }
                        
                        else if (USER_ADRESS.getText().equals("")) {
                            Dialog.show("Erreur", "Champ vide de age ", "OK", null);
                            
                        } else if (!val_age.isValid()) {
                            Dialog.show("Erreur age !", "il faut saisir des numbers", "OK", null);
                            
                        }
                        
                        
                         else if (Password.getText().equals("")) {
                            Dialog.show("Erreur", "Champ vide de password ", "OK", null);
                            
                        } else if (USERNUMBER.getText().equals("")) {
                            Dialog.show("Erreur", "Champ vide de Number ", "OK", null);
                            
                        }
                        else if (!val_number.isValid()) {
                            Dialog.show("Erreur number !", "il faut saisir des numbers", "OK", null);
                            
                        }
                        
                        else if (USERNUMBER.getText().length() != 8) {
                            Dialog.show("Erreur", "il faut 8 chiffres ", "OK", null);
                            
                        }
                        
                        else {
                            User u = new User();
                            u.setAdresse(USER_ADRESS.getText());
                            u.setEmail(EMAIL.getText());
                            u.setNumTel(Integer.parseInt(USERNUMBER.getText()));
                            u.setNom(FIRSTNAME.getText());
                            u.setDomaine(LASTNAME.getText());
                            u.setPassword(Password.getText());
                            if (new User_Service().addUser(u) == true) {
                                Dialog.show("Sign UP", "Sign Up aves success ", "OK", null);
                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                                //cnreq.addArgument("name", USERNAME.getText());
                                cnreq.addArgument("password", Password.getText());
                                // cnreq.setUrl(url);
                                cnreq.addResponseListener(ev -> {
                                    String chaine = new String(cnreq.getResponseData());
                                    
                                    
                                    
                                    new LoginForm().showBack();
                                    
                                });
                                
                            } else {
                                Dialog.show("Erreur", "Compte existe ", "OK", null);
                            }

                        }

                    }
                    );

      

    }
}
