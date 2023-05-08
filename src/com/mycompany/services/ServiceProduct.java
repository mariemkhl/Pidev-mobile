/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.Product;
import com.mycompany.utils.Statics;
import java.util.ArrayList;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nour Benkairia
 */
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
//import java.beans.Statement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;

import java.util.ArrayList;

public class ServiceProduct {
    private ConnectionRequest req;
    private static ServiceProduct instance =null;
    private boolean resultOK;
    private ArrayList<Product> products;

    public ServiceProduct() {
        req = new ConnectionRequest();
        products = new ArrayList<>();
    }

    public static ServiceProduct getInstance() {
        if (instance == null)
            instance = new ServiceProduct();
        return instance;
    }

    public boolean addProduct(Product product) {
//         String nom = product.getNom();
      
        String url = Statics.BASE_URL + "/Mobileproduct/api/addproducts?nom=" + product.getNom() + "&description=" + product.getDescription() + "&prix=" + product.getPrix() + "&img=" + product.getImg() + "&categ=" + product.getCat_p() + "&user=" + product.getUser_id() + "&url=" + product.getUrl();
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
    
    
    public boolean deleteProduct(int productId) {
//    String url = Statics.BASE_URL + "/api/deleteproducts/"+ productId;http:
     String url =  "http://127.0.0.1:8000/Mobileproduct/api/deleteproducts/" + productId;
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

      
    
    public boolean updateProduct(Product product) {
//    String url = Statics.BASE_URL + "/Mobileproduct/api/updateproducts/?id=" + product.getId_p() + "&nom=" + product.getNom() + "&description=" + product.getDescription() + "&prix=" + product.getPrix() + "&img=" + product.getImg() + "&categ=" + product.getCat_p() + "&user=" + product.getUser_id() + "&url=" + product.getUrl();
    String url = "http://127.0.0.1:8000/Mobileproduct/api/updateproducts/" + product.getId_p() + "&nom=" + product.getNom() + "&description=" + product.getDescription() + "&prix=" + product.getPrix() + "&img=" + product.getImg() + "&categ=" + product.getCat_p() + "&user=" + product.getUser_id() + "&url=" + product.getUrl();

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


public ArrayList<Product> parseproduct(String jsonText) {
        try {
            products = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> mapListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

           
                   
                    List<Map<String, Object>>  listOfMaps = (List<Map<String, Object>>) mapListJson.get("root");
                    for(Map<String, Object> obj : listOfMaps) {
                        Product re = new Product();
                        
                        //dima id fi codename one float 5outhouha
                       float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        String description = obj.get("description").toString();
                        String prix = obj.get("prix").toString();
                        String img = obj.get("img").toString();
                   //     String categ = obj.get("categ").toString();
                        float user = Float.parseFloat(obj.get("user").toString());
                        String url = obj.get("url").toString();
                        
                       re.setId_p((int)id);
                        re.setNom(nom);
                        re.setDescription(description);
                        re.setPrix(Double.parseDouble(prix));
                        re.setImg(img);
                     //   re.setCat_p(categ);
                        re.setUser_id((int) user);
                        re.setUrl(url);
                     
                        
                        //insert data into ArrayList result
                        products.add(re);
                       
                    
                    }

         } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }


public ArrayList<Product> affichageProduct2() {
               ArrayList<Product> result = new ArrayList<>();
        Product re = new Product();
//        String url = Statics.BASE_URL+"/Mobileproduct/api/products";
String url = "https://127.0.0.1:8000/Mobileproduct/api/products";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
                @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseproduct(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }




}

    
    
    
    
    
    
    
    
    

