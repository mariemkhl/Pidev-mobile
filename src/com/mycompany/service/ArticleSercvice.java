/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entite.Article;
import com.mycompany.utils.DataSource;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ArticleSercvice {
    
    
       
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Article> comments;

    public ArticleSercvice() {
        request = DataSource.getInstance().getRequest();

    }
    
    
    public boolean addArticle(Article r) {

        String url = Statics.BASE_URL + "/api/addarticle?categorie=" + r.getCategorie() + "&content=" + r.getContent() + "&image=" + r.getImage()+ "&titre=" + r.getTitre();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
    
    public ArrayList<Article> getAllArticle() {
        String url = Statics.BASE_URL + "/api/article";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    comments = parseType(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return comments;
    }
    
    
    
    
    public ArrayList<Article> parseType(String jsonText) throws ParseException {
        try {
            comments = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ;
                int id = (int) Float.parseFloat(obj.get("idArticle").toString());

                String titre = obj.get("titreArticle").toString();
                String content = obj.get("contentArticle").toString();
                String image = obj.get("imageArticle").toString();
                String categorie = obj.get("categoryArticle").toString();
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(obj.get("dateArticle").toString());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(s);

                Article v = new Article();
                v.setCategorie(categorie);
                v.setId(id);
                v.setContent(content);  
                v.setImage(image);
                v.setTitre(titre);
                

                
                comments.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return comments;
    }
    
    
    
        public boolean DeleteArticle(int id) {

        String url = Statics.BASE_URL + "/api/suprimerarticle/" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
        
        
       public boolean ModifierArticle(Article r) {

        String url = Statics.BASE_URL + "/api/articlemodifierConsultation?categorie=" + r.getCategorie() + "&content=" + r.getContent() + "&image=" + r.getImage()+ "&titre=" + r.getTitre()+ "&id=" + r.getId();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
            
           
}
