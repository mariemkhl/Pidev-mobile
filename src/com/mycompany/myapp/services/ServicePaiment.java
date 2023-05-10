package com.mycompany.myapp.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.entities.Paiment;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
//import java.beans.Statement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

public class ServicePaiment {
    private ConnectionRequest req;
    private static ServicePaiment instance =null;
    private boolean resultOK;
    private ArrayList<Paiment> paiments;

    public ServicePaiment() {
        req = new ConnectionRequest();
        paiments = new ArrayList<>();
    }

    public static ServicePaiment getInstance() {
        if (instance == null)
            instance = new ServicePaiment();
        return instance;
    }

    public boolean addPaiment(Paiment paiment) {

      
String url = Statics.BASE_URL + "/paimentmobile/addpayment?nom_carte=" + paiment.getNomCarte()
        + "&num_carte=" + paiment.getNumCarte()
        + "&prix_tot=" + paiment.getPrixTot()
        + "&CV_code=" + paiment.getCvCode();
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
      
   
    public List<String> getPaiments() {
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


public ArrayList<Paiment> parsepaiment(String jsonText) {
        try {
            paiments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> mapListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

           
                   
                    List<Map<String, Object>>  listOfMaps = (List<Map<String, Object>>) mapListJson.get("root");
                    for(Map<String, Object> obj : listOfMaps) {
                        Paiment re = new Paiment();
                        
                        //dima id fi codename one float 5outhouha
                       float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nomCarte = obj.get("nom_carte").toString();
                       int cvCode = Integer.parseInt(obj.get("cvCode").toString());
                        int prixTot = Integer.parseInt(obj.get("prixTot").toString());
                        int numCarte = Integer.parseInt(obj.get("numCarte").toString());
                        int commande = Integer.parseInt(obj.get("commande").toString());
                       String dateExStr = obj.get("dateEx").toString();
                      Date dateEx = null;

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as per your needs

try {
    dateEx = dateFormat.parse(dateExStr);
} catch (ParseException e) {
    // Handle the parse exception if necessary
    e.printStackTrace();
}
                        
                        
                        re.setId((int)id);
                        re.setCvCode(cvCode);
                        re.setDateEx(dateEx);
                        re.setNomCarte(nomCarte);
                        re.setNumCarte(numCarte);
                        re.setPrixTot(prixTot);
                     //   re.setCat_p(categ);
                        re.setCommande(commande);                
                        
                        //insert data into ArrayList result
                        paiments.add(re);
                       
                    
                    }

         } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return paiments;
    }


public ArrayList<Paiment> affichagePaiment() {
               ArrayList<Paiment> result = new ArrayList<>();
        Paiment re = new Paiment();

String url = "https://127.0.0.1:8000/Mobileproduct";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
                @Override
            public void actionPerformed(NetworkEvent evt) {
                paiments = parsepaiment(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return paiments;
    }
public static void supprimerPaiment(int prixTot) {
    String url = "https://127.0.0.1:8000/paimentmobile/remove?prixTot=" + prixTot;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("DELETE");
    req.addResponseListener((NetworkEvent evt) -> {
        int responseCode = req.getResponseCode();
        if (responseCode == 200) {
            // Deletion successful
            Dialog.show("Success", "Paiment deleted successfully", "OK", null);
        } else {
            // Deletion failed
            Dialog.show("Error", "Failed to delete Paiment", "OK", null);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}


}

    
    
    
    
    
    
    
    
    

