/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

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

/**
 *
 * @author Nour Benkairia
 */
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.l10n.ParseException;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import static com.codename1.ui.events.ActionEvent.Type.Log;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.scene.Scene;
import com.codename1.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
//import java.beans.Statement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;

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
    String url = "http://127.0.0.1:8000/Mobileproduct/api/updateproducts/" + product.getId_p() + "?nom=" + product.getNom() + "&description=" + product.getDescription() + "&prix=" + product.getPrix() + "&img=" + product.getImg() + "&categ=" + product.getCat_p() + "&user=" + product.getUser_id() + "&url=" + product.getUrl();

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




 
   public void generateQRCode(String productId, Callback<EncodedImage> callback) {
        String url = Statics.BASE_URL + "/Mobileproduct/qr-code/" + productId;

        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] responseData = request.getResponseData();
                try {
                    String json = new String(responseData, "UTF-8");
                    JSONParser parser = new JSONParser();
                    Map<String, Object> qrCodeData = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(responseData), "UTF-8"));

                    String qrCodeImageUrl = (String) qrCodeData.get("img");
                    // You can also access other QR code variations using qrCodeData map

                    if (qrCodeImageUrl != null) {
                        EncodedImage qrCodeImage = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(1, 1), false),
                                "qrCode_" + productId, qrCodeImageUrl);
                        callback.onSucess(qrCodeImage);
                    } else {
//                        callback.onError("QR code URL not found in the response.");
                    }
                } catch (IOException ex) {
//                    callback.onError(ex.getMessage());
                }
            }
        });

        NetworkManager.getInstance().addToQueue(request);
    }


public double convertToEuros(double priceInDinars) {
    // Conversion logic from dinars to euros
    double conversionRate = 0.0095; // Replace with the actual conversion rate
    return priceInDinars * conversionRate;
}

//
//private Image generateQRCode(String data, int size, int margin) {
//    try {
//        // Generate the QR code using the provided data, size, and margin
//        byte[] qrCodeBytes = Util.getQRCode(data, size, size, margin);
//        Image qrCodeImage = Image.createImage(qrCodeBytes, 0, qrCodeBytes.length);
//
//        return qrCodeImage;
//    } catch (Exception e) {
//        e.printStackTrace();
//        return null;
//    }
//}

   
//  public byte[] generateQRCode(String data, int size, int margin) throws WriterException, IOException {
//    Map<EncodeHintType, Object> hints = new HashMap<>();
//    hints.put(EncodeHintType.MARGIN, margin);
//
//    QRCode qrCode = Encoder.encode(data, ErrorCorrectionLevel.L, hints);
//    int qrCodeWidth = qrCode.getMatrix().getWidth();
//    BufferedImage image = new BufferedImage(qrCodeWidth, qrCodeWidth, BufferedImage.TYPE_INT_RGB);
//    image.createGraphics();
//
//    Graphics2D graphics = (Graphics2D) image.getGraphics();
//    graphics.setColor(Color.WHITE);
//    graphics.fillRect(0, 0, qrCodeWidth, qrCodeWidth);
//    graphics.setColor(Color.BLACK);
//
//    for (int x = 0; x < qrCodeWidth; x++) {
//        for (int y = 0; y < qrCodeWidth; y++) {
//            if (qrCode.getMatrix().get(x, y)) {
//                graphics.fillRect(x, y, 1, 1);
//            }
//        }
//    }
//
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    ImageIO.write(image, "png", baos);
//    baos.flush();
//    byte[] qrCodeImageBytes = baos.toByteArray();
//    baos.close();
//
//    return qrCodeImageBytes;
//}

   








}

    
    
    
    
    
    
    
    
    

