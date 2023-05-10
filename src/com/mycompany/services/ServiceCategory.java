/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.mycompany.entity.Category;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Nour Benkairia
 */
public class ServiceCategory {
  
    private static ServiceCategory instance;

    private ServiceCategory() {
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }

    public void getAllCategories(Callback<ArrayList<Category>> callback) {
        // Perform the network request to fetch categories from the server
        // Adjust the code below to match your actual network request implementation

        // Create a ConnectionRequest
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(Statics.BASE_URL + "/api/categorys");
        request.setPost(false);

        // Handle the response
        request.addResponseListener(event -> {
            NetworkEvent networkEvent = (NetworkEvent) event;
            byte[] responseData = networkEvent.getConnectionRequest().getResponseData();
            if (responseData != null) {
                try {
                    // Parse the JSON response and retrieve the categories
                    JSONParser parser = new JSONParser();
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(responseData), "UTF-8"));
                    ArrayList<Category> categories = parseCategories(response);
                    callback.onSuccess(categories);
                } catch (IOException e) {
                    callback.onError(e);
                }
            } else {
                callback.onError(new IOException("Empty response data"));
            }
        });

        // Send the request
        NetworkManager.getInstance().addToQueue(request);
    }

    private ArrayList<Category> parseCategories(Map<String, Object> response) {
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Map<String, Object>> categoryList = (ArrayList<Map<String, Object>>) response.get("categories");
        if (categoryList != null) {
            for (Map<String, Object> categoryData : categoryList) {
                int id = (int) categoryData.get("id");
                String nom = (String) categoryData.get("nom");
                Category category = new Category(id, nom);
                categories.add(category);
            }
        }
        return categories;
    }
}

    

