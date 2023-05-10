package com.mycompany.pi_art;



import com.codename1.components.SplitPane;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entity.map;
import com.mycompany.services.servicemap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;





/**
 *
 * @author Chadi
 */
public class MapForm {
public Form f = new Form();
  MapContainer cnt = null;
  public String s;
  map m=new map();
  Dialog d2= new Dialog();
  
  Dialog d= new Dialog();
@SuppressWarnings("UnusedAssignment")
public MapForm() {
         d2.setUIID("d2");
         
          servicemap sm= new servicemap();
        List map=sm.getAllmap();
    try{
        cnt = new MapContainer("AIzaSyCy-fMWerzvXcPCV0FDI07hW2DAzs_mnpY");
    }catch(Exception ex) {
        ex.printStackTrace();
    }

        Button btnMoveCamera = new Button("Mon Pays");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(36.8189700, 10.1657900));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));
        
        
    
for(int i=0;i<map.size();i++){
            map m=(map) map.get(i);
            
            Coord cord= new Coord(m.getLatitude(),m.getLongitude());
            cnt.addMarker(
                        EncodedImage.createFromImage(markerImg.scaled(50,50), false),
                         cord ,
                        ""+cnt.getCameraPosition().toString(),
                        "",
                        e3->{
                           d2= new Dialog() ;
                
                    System.out.println(m.getImage());
                try {
                    /*    EncodedImage placeholder = EncodedImage.createFromImage(
                    Image.createImage(80, 100, 0x00000000), false);
                    
                    URLImage urlImage = URLImage.createToStorage(placeholder, "cacheKey","file:/C:/Users/iheb debbech/Desktop/projetartistysymfony/projectartistysymfony/public/assets/img/" + m.getImage(),
                    URLImage.RESIZE_SCALE_TO_FILL);
                    */
                    Image image = Image.createImage("file:/C:/Users/iheb debbech/Desktop/projetartistysymfony/projectartistysymfony/public/assets/img/" + m.getImage());
                Label imageLabel = new Label(image.scaled(150, 170));
                  
                    
                    d2.add(BoxLayout.encloseXCenter(imageLabel));
              
                } catch (IOException ex) {
                    System.out.println("error");
                   }
           
                            Label n1=new Label("nomplace:");
               // d2.add(n1);
                Label n2=new Label(m.getNomplace());
                n1.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
               // d2.add(n2);
                d2.add(BoxLayout.encloseX(n1,n2));
                          Label n3=new Label("Description:");
                             n3.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));

               
                Label n4=new Label(m.getDescription());
                d2.add(BoxLayout.encloseX(n3,n4));
                 Label n5=new Label("Categorie:");
                n5.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
                Label n6=new Label(m.getCategorie());
                d2.add(BoxLayout.encloseX(n5,n6));
                 Label n7=new Label("nbLikes:");
                n7.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
                Label n8=new Label(Integer.toString(m.getNblikes()));
                d2.add(BoxLayout.encloseX(n7,n8));
                           Button b1=new Button("ok");
               
               
                b1.addActionListener((e4) ->{
                 d2.dispose();
                 
                });
                Button b2=new Button("like");
               
               
                b2.addActionListener((e4) ->{
                    sm.modifiermap(m);
                    m.setNblikes(m.getNblikes()+1);
                 d2.dispose();
                });
                Button b3=new Button("dislike");
               
               
                b3.addActionListener((e4) ->{
                     sm.deletelike(m);
                     m.setNblikes(m.getNblikes()-1);
                 d2.dispose();
                });
                d2.add(BorderLayout.centerTotalBelowEastWest(b2,b1,b3));
                d2.setLayout(BoxLayout.y());
                                     d2.show();
                                    
            //ToastBar.showMessage("You clicked "+cnt.getName(), FontImage.MATERIAL_PLACE);
                        }
                );
            Point screen=cnt.getScreenCoordinate(cord);
            cnt.getComponentAt(screen.getX(), screen.getY()).setUIID("marker");
        }


        cnt.addTapListener(e->{
            if( cnt.getComponentAt(e.getX(), e.getY()-90).getUIID() != "marker"){
                
            d=new Dialog();
                //  cnt.getComponentAt(0)
            System.out.println("pssssssssst");
               
                Command ok = new Command("ok");
                Label n=new Label("nomplace");
                TextField tx= new TextField("","give a name");
                
                              d.add(BoxLayout.encloseX(n,tx));

        
                 Label n2=new Label("description");
                TextField tx2= new TextField("","give a description");
                
              
             
                              d.add(BoxLayout.encloseX(n2,tx2));
                
                Label n3=new Label("categorie");
                TextField tx3= new TextField("","categorie?");
                
                
             
                              d.add(BoxLayout.encloseX(n3,tx3));
                Label n4=new Label("link");
                TextField tx4= new TextField("","give a link");
                
              
             
                              d.add(BoxLayout.encloseX(n4,tx4));
                               Button importerBtn = new Button("Importer");
         importerBtn.addActionListener((ei) -> {   
          Display.getInstance().openGallery(new ActionListener() {
        @Override
               
            public void actionPerformed(ActionEvent evt) {
                // Handle the selected image
            if (evt != null && evt.getSource() != null) {
                String fileName = null;
                if (evt != null && evt.getSource() != null) {
                    String fileUrl = (String) evt.getSource();
                    int lastIndex = fileUrl.lastIndexOf('/');
                    if (lastIndex >= 0 && lastIndex < fileUrl.length() - 1) {
                        fileName = fileUrl.substring(lastIndex + 1);
                        String storagePath = "file:/C:/Users/iheb debbech/Desktop/projetartistysymfony/projectartistysymfony/public/assets/img/" + fileName;

                        try {
                            InputStream is = FileSystemStorage.getInstance().openInputStream(fileUrl);
                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(storagePath);
                            Util.copy(is, os);
                            Util.cleanup(is);
                            Util.cleanup(os);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // Récupérer le nom du fichier sans le timestamp
                        int tsIndex = fileName.indexOf("_");
                        if (tsIndex >= 0) {
                            fileName = fileName.substring(tsIndex + 1);
                        }
                        // Stocker le nom du fichier dans votre objet Livraison
                        m.setImage(fileName);
                    }}}
            }
        }, Display.GALLERY_IMAGE);
              
});
         d.add(importerBtn);
                Button b2=new Button(ok);
               
                b2.addActionListener((e2) ->{
                    
                 
                    System.out.println(tx.getText());
                 m.setNomplace(tx.getText());
                  m.setDescription(tx2.getText());
                  m.setCategorie(tx3.getText());
                  m.setLien(tx4.getText());
                  m.setLatitude(cnt.getCoordAtPosition(e.getX(), e.getY()-90).getLatitude());
                  m.setLongitude(cnt.getCoordAtPosition(e.getX(), e.getY()-90).getLongitude());
                    System.out.println(m);
                    if(m.getCategorie() != "cinema" || m.getCategorie() != "theatre" || m.getCategorie() != "monument"){
                        new Dialog().show("","please select a valid category","okay","cancel");
                    }
                    else if(m.getCategorie() == "cinema" || m.getCategorie() == "theatre" || m.getCategorie() == "monument"){sm.addmap(m);
                    map.add(m);
                     cnt.addMarker(
                        EncodedImage.createFromImage(markerImg.scaled(50,50), false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()-90),
                        ""+cnt.getCameraPosition().toString(),
                        "",
                        e3->{
                              
                           d2= new Dialog() ;
                
                    System.out.println(m.getImage());
                try {
                    /*    EncodedImage placeholder = EncodedImage.createFromImage(
                    Image.createImage(80, 100, 0x00000000), false);
                    
                    URLImage urlImage = URLImage.createToStorage(placeholder, "cacheKey","file:/C:/Users/iheb debbech/Desktop/projetartistysymfony/projectartistysymfony/public/assets/img/" + m.getImage(),
                    URLImage.RESIZE_SCALE_TO_FILL);
                    */
                    Image image = Image.createImage("file:/C:/Users/iheb debbech/Desktop/projetartistysymfony/projectartistysymfony/public/assets/img/" + m.getImage());
                Label imageLabel = new Label(image.scaled(150, 170));
                  
                    
                    d2.add(BoxLayout.encloseXCenter(imageLabel));
              
                } catch (IOException ex) {
                    System.out.println("error");
                }
           
                            Label n1=new Label("nomplace:");
               // d2.add(n1);
                Label n10=new Label(m.getNomplace());
                n1.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
               // d2.add(n2);
                d2.add(BoxLayout.encloseX(n1,n10));
                          Label n11=new Label("Description:");
                             n3.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));

               
                Label n12=new Label(m.getDescription());
                d2.add(BoxLayout.encloseX(n11,n12));
                 Label n5=new Label("Categorie:");
                n5.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
                Label n6=new Label(m.getCategorie());
                d2.add(BoxLayout.encloseX(n5,n6));
                 Label n7=new Label("nbLikes:");
                n7.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
                Label n8=new Label(Integer.toString(m.getNblikes()));
                d2.add(BoxLayout.encloseX(n7,n8));
                           Button b1=new Button("ok");
               
               
                b1.addActionListener((e4) ->{
                 d2.dispose();
                 
                });
                Button b4=new Button("like");
               
               
                b4.addActionListener((e4) ->{
                 d2.dispose();
                });
                Button b5=new Button("dislike");
               
               
                b5.addActionListener((e4) ->{
                 d2.dispose();
                });
                d2.add(BorderLayout.centerTotalBelowEastWest(b4,b1,b5));
                d2.setLayout(BoxLayout.y());
                                     d2.show();
                                    
            //ToastBar.showMessage("You clicked "+cnt.getName(), FontImage.MATERIAL_PLACE);
                        }
                );
                     }
                    
                    d.dispose();
            Point screen=cnt.getScreenCoordinate(e.getX(), e.getY()-90);
            cnt.getComponentAt(screen.getX(), screen.getY()).setUIID("marker");
                }     ); 
                
                 Button b3=new Button("cancel");
                              d.add(BoxLayout.encloseX(b2,b3));
               
                b3.addActionListener((e3) ->{
                 d.dispose();
                });
                
                if(d2.getComponentCount()!=2)
                {
                d2 = new Dialog();
                    System.out.println(d2.getComponentCount());
                }
                else{
                    d.setLayout(BoxLayout.y());
                 d.show();
                }
                
             ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://maps.google.com/maps/api/geocode/json?latlng="+cnt.getCameraPosition().getLatitude()+","+cnt.getCameraPosition().getLongitude()+"&oe=utf8&sensor=false");
                     NetworkManager.getInstance().addToQueueAndWait(r);

            JSONParser jsonp = new JSONParser();
         try {
               java.util.Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(r.getResponseData()).toCharArray()));
                              System.out.println("roooooot:" +tasks.get("results"));
                              List<java.util.Map<String, Object>> list1 = (List<java.util.Map<String, Object>>)tasks.get("results");
//                              java.util.Map<String, Object> list = (java.util.Map<String, Object>) list1.get(0);

  //                             List<java.util.Map<String, Object>> listf = (List<java.util.Map<String, Object>>) list.get("address_components");
//String ch="";
  //                       for (java.util.Map<String, Object> obj : listf) {
    //             ch=ch+obj.get("long_name").toString();
      //                   }
                       //
                         // b.setAdresse(ch);

                        

           } catch (IOException ex) {
               
           }

            
            
        }
        
              //  if(d.show("Confirm", "Do you want to proceed?", "OK", "Cancel")){
        }  );
        
        
        
       // Container root = new Container();
       
       /* Label l=new Label("mapssss");
        
        root.add(l);
        l.setX(100);
        */
        // f.setLayout(new BorderLayout());
         
     /* SplitPane splitPane = new SplitPane(SplitPane.VERTICAL_SPLIT, 
        root,
        cnt, "40px","100px","350px");
   */
         Container root = new Container();
         f.setLayout(new BorderLayout());
         f.addComponent(BorderLayout.CENTER, cnt);
         f.addComponent(BorderLayout.SOUTH, btnMoveCamera);
//f.show();
 //f.getToolbar().addCommandToRightBar("back", null, (ev)->{ new AjoutReclamationForm(f).show()});

    
    }
    
    
    
    
    

    
}