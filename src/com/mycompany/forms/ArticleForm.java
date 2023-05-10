/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.forms;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Article;
import com.mycompany.services.ArticleSercvice;
import java.io.IOException;
import java.util.Date;


/**
 *
 * @author Abderrazekbenhamouda
 */
public class ArticleForm extends  Form{
    
    
    
         String file ;
          Resources theme;
    
     public ArticleForm() {
                super("Article", BoxLayout.y());
            theme = UIManager.initFirstTheme("/theme");
     this.getToolbar().setUIID("tb");
        Label logi = new Label("LES Articles");
        this.add(logi);
            this.getToolbar().addCommandToRightBar(null, theme.getImage("log.png"), ev -> {
           // new LoginForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Article", null, ev->{
        Form addEvent = new Form("Ajouter Article",BoxLayout.y());
            Label AJOUT = new Label("ADD Evenements");
            addEvent.add(AJOUT);
        TextField TITRE = new TextField("", "Titre", 20, TextArea.TEXT_CURSOR);
        TextField Categorie = new TextField("", "Lieu", 20, TextArea.TEXT_CURSOR);
        TextField Content = new TextField("", "Discription", 20, TextArea.TEXT_CURSOR);
 
        TextField NOMBREPlace = new TextField("", "Nombre De Place", 20, TextArea.NUMERIC); 
         NOMBREPlace.setUIID("txtn");
        Button upload = new Button("Upload Image");
        upload.setUIID("vtnvalid");
        Button save = new Button("Ajouter");
        save.setUIID("vtnvalid");
        addEvent.add("Titre : ").add(TITRE);
        addEvent.add("Categorie : ").add(Categorie);
        addEvent.add("Content : ").add(Content);

        addEvent.add(upload);
        addEvent.add(save);
        
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://127.0.0.1/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
    
        save.addActionListener(l
                                -> {

                            if (TITRE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de firstname ", "OK", null);

                            }  else if (Categorie.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Categorie ", "OK", null);

                            } else if (Content.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Content ", "OK", null);

                            } 
                            
                         else {
                           
                                Article e = new Article();
                                e.setCategorie(Categorie.getText());
                                e.setContent(Content.getText());
                                e.setImage(file);
                                e.setTitre(TITRE.getText());
                                System.out.println("forms.addEvet.addItem()"+e);
                                if (new ArticleSercvice().addArticle(e) == true) {
                                    Dialog.show("Ajouter Evenemet", "Ajouter Evenement aves success ", "OK", null);
                                    
                                    
                                } else {
                                    Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                }

                            }

                        }
                        );
 addEvent.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        addEvent.show();
 });
              for(Article c:new ArticleSercvice().getAllArticle()){
           
 
                    try {    
                        this.add(addItem(c));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Article e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label titre=new Label(e.getTitre());
          Label libelle_titre = new Label("Titre");
        Label nbrparticipant=new Label(e.getCategorie());
        Label libelle_nbrparticipant = new Label("Categorie :");
        Label nbrPlace=new Label(String.valueOf(e.getCategorie()));
         Label libelle_nbrPlace = new Label("Content");
        Label lieu=new Label(e.getContent());
        String image = new String("http://127.0.0.1//Upload/");
        EncodedImage enc = EncodedImage.create("/sepf.png");
        Image im = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       

        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");

        cn2.add(libelle_titre).add(titre);
        cn2.add(libelle_nbrparticipant).add(nbrparticipant);
        cn2.add(libelle_nbrPlace).add(nbrPlace);

              cn2.add(im);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getTitre());
       
        Label cat=new Label(e.getCategorie());
        Label contn=new Label(String.valueOf(e.getContent()));

        String imaga1 = new String("http://127.0.0.1/Upload/");
            try {
                EncodedImage enc1 = EncodedImage.create("/sepf.png");
            } catch (IOException ex) {
            }
        Image im1 = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       


     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");

     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Article", BoxLayout.y());
         
           Label modif = new Label("EDIT Article");
                fmodifier.add(modif);
         fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getTitre());
          
         titre2.setMinimumElementsShownInPopup(1);
         AutoCompleteTextField lieu2=  new AutoCompleteTextField(e.getCategorie());
         lieu2.setMinimumElementsShownInPopup(1);
         AutoCompleteTextField Description2=  new AutoCompleteTextField(e.getContent());
         Description2.setMinimumElementsShownInPopup(1);
         

          Label lib_titre = new Label("Titre");
         fmodifier.add(lib_titre).add(titre2);
              Label lib_Lieu = new Label("Categorie");
         fmodifier.add(lib_Lieu).add(lieu2);
              Label lib_Description = new Label("Content");
         fmodifier.add(lib_Description).add(Description2);


         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             Article a = new Article();
             a.setId(e.getId());
             a.setCategorie(lieu2.getText());
             a.setContent(Description2.getText());
              System.out.println("tttttt"+titre2.getText());
               System.out.println("tasw"+e.getImage());
             a.setTitre(titre2.getText());
             a.setImage(e.getImage());
             System.out.println("imaaaaaaage"+a.toString());
             if ( new ArticleSercvice().ModifierArticle(a) == true) {
                 Dialog.show("Modifier Article", "Article Modifier aves success ", "OK", null);
                 
                 
             } else {
                 Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
             }
             new ArticleForm().show();
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (new ArticleSercvice().DeleteArticle(e.getId())) {
                                        Dialog.show("Supprimer Article", "Article Supprimer aves success ", "OK", null);
                                        
                                        new ArticleForm().show();
                                    } else {
                                        Dialog.show("Erreur", " Erreur de suppression ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Titre");
      
              Label lib_Lieu = new Label("Categorie");
   
              Label lib_Description = new Label("Content");


                
            f2.add(im1).add(lib_titre).add(titrem).add(lib_Lieu).add(lib_Description).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
}
