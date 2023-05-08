/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceReclamation {
 
    private ConnectionRequest req;
    private static ServiceReclamation instance =null;
    private boolean resultOK;
    private ArrayList<Reclamation> reclamations;

    public ServiceReclamation() {
        req = new ConnectionRequest();
        reclamations = new ArrayList<>();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null)
            instance = new ServiceReclamation();
        return instance;
    }

    public boolean addReclamation(Reclamation reclamation) {
//         String nom = product.getNom();
      
        String url = Statics.BASE_URL + "/reclamationmobile/new/?commentaire=" + reclamation.getCommentaire() + "&typereclamation=" + reclamation.getTypereclamation();
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
      
   
    public List<String> getCategoriesFromDatabase() {
 List<String> categoryList = new ArrayList<>();

    String url = Statics.BASE_URL + "Mobilecategory/api/categorys/names";
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setPost(false);

    request.addResponseListener((NetworkEvent evt) -> {
        byte[] responseData = request.getResponseData();
        if (responseData != null) {
            String response = new String(responseData);
           
            JSONParser parser = new JSONParser();
            try {
                Map<String, Object> categoriesData = parser.parseJSON(new CharArrayReader(response.toCharArray()));
                List<Map<String, Object>> categories = (List<Map<String, Object>>) categoriesData.get("root");
                for (Map<String, Object> category : categories) {
                    String categoryName = (String) category.get("nom");
                    categoryList.add(categoryName);
                  
                }
                
                  System.out.println(categoryList);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(request);

    return categoryList;
}


public ArrayList<Reclamation> parseReclamation(String jsonText) {
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> mapListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

           
                   
                    List<Map<String, Object>>  listOfMaps = (List<Map<String, Object>>) mapListJson.get("root");
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                        //dima id fi codename one float 5outhouha
                       float numero = Float.parseFloat(obj.get("numero").toString());
                        
                        String commentaire = obj.get("commentaire").toString();
                        String typereclamation = obj.get("typereclamation").toString();
                        
                        
                       re.setNumero((int)numero);
                        re.setCommentaire(commentaire);
                        re.setTypereclamation(typereclamation);
                       
                     
                        
                        //insert data into ArrayList result
                        reclamations.add(re);
                       
                    
                    }

         } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }


public ArrayList<Reclamation> affichageReclamation2() {
               ArrayList<Reclamation> result = new ArrayList<>();
        Reclamation re = new Reclamation();
//        String url = Statics.BASE_URL+"/Mobileproduct/api/products";
String url = "https://127.0.0.1:8000/reclamationmobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
                @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
public boolean deleteReclamation(int numero) {
//    String url = Statics.BASE_URL + "/api/deleteproducts/"+ productId;http:
     String url =  "http://127.0.0.1:8000/reclamationmobile/delete/" + numero;
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return resultOK;
}
}
