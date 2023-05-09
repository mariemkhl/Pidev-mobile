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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceCommande {
    private ConnectionRequest req;
    private static ServiceCommande instance =null;
    private boolean resultOK;
    private ArrayList<Commande> commandes;

    public ServiceCommande() {
        req = new ConnectionRequest();
        commandes = new ArrayList<>();
    }

    public static ServiceCommande getInstance() {
        if (instance == null)
            instance = new ServiceCommande();
        return instance;
    }

    public boolean addCommande(Commande commande) {

      
        String url = Statics.BASE_URL + "/commandemobile/new?payment=" + commande.getPayment()+ "&prixTot=" + commande.getPrixTot();
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
      
   
    public List<String> getCommandes() {
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


public ArrayList<Commande> parseCommande(String jsonText) {
        try {
            commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> mapListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

           
                   
                    List<Map<String, Object>>  listOfMaps = (List<Map<String, Object>>) mapListJson.get("root");
                    for(Map<String, Object> obj : listOfMaps) {
                        Commande re = new Commande();
                        
                        //dima id fi codename one float 5outhouha
                       float idcommande = Float.parseFloat(obj.get("idcommande").toString());
                        
                        String userid = obj.get("userid").toString();
                        String payment = obj.get("payment").toString();
                        int prixTot = Integer.parseInt(obj.get("prixTot").toString());
                       
                       String dateExStr = obj.get("date").toString();
                      Date date = null;

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as per your needs

try {
    date = dateFormat.parse(dateExStr);
} catch (ParseException e) {
    // Handle the parse exception if necessary
    e.printStackTrace();
}
                        
                        
                        re.setIdcommande((int)idcommande);
                        re.setPayment(payment);
                        re.setDate(date);
                        re.setUserid(userid);
                        re.setPrixTot(prixTot);
                     //   re.setCat_p(categ);
                                  
                        
                        //insert data into ArrayList result
                        commandes.add(re);
                       
                    
                    }

         } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }


public ArrayList<Commande> affichageCommande() {
               ArrayList<Commande> result = new ArrayList<>();
        Commande re = new Commande();

String url = "https://127.0.0.1:8000/commandemobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
                @Override
            public void actionPerformed(NetworkEvent evt) {
               commandes = parseCommande(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }





    
}
