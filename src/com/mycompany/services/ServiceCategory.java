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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.Category;
import com.mycompany.entity.Product;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;



import com.codename1.components.ImageViewer;
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



import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;




import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Callback;
import com.codename1.util.regex.RE;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import java.util.Map;
import java.util.HashMap;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;


import com.codename1.components.ScaleImageButton;
import com.codename1.io.Util;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

////import java.util.EnumMap;
import java.util.Map;



/**
 *
 * @author Nour Benkairia
 */
public class ServiceCategory {
      private ConnectionRequest req;
    private static ServiceCategory instance =null;
    private boolean resultOK;
    private ArrayList<Product> category;
        
    

    private ServiceCategory() {
         req = new ConnectionRequest();
        category = new ArrayList<>();
  
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }
    
    
    
    
      public boolean addCategory(Category cat) {
//         String nom = product.getNom();
      
        String url = Statics.BASE_URL + "/Mobileproduct/api/addcategorys?nom=" + cat.getNom() ;
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

    

