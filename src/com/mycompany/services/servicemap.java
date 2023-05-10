/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.map;
import com.mycompany.utils.staticp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author iheb debbech
 */
public class servicemap {
    public ArrayList<map> map;

    public static servicemap instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public servicemap() {
        req = new ConnectionRequest();
    }
    public ArrayList<map> parsemap(String jsonText) {
        try {
            map = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> mapListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) mapListJson.get("root");
            for (Map<String, Object> obj : list) {
                map t = new map();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNomplace(obj.get("nomplace").toString());
                t.setLien(obj.get("lien").toString());
                t.setLongitude(Double.valueOf(obj.get("longitude").toString()));
                t.setLatitude(Double.valueOf(obj.get("latitude").toString()));
                t.setImage(obj.get("image").toString());
                t.setDescription(obj.get("description").toString());
                t.setCategorie(obj.get("categorie").toString());
                t.setNblikes((int) Float.parseFloat(obj.get("nblikes").toString()));
                map.add(t);
            }

         } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return map;
    }
 public boolean addmap(map m) {

       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = "http://127.0.0.1:8000/map/" + "addjson/" + m.getNomplace() + "/" + m.getDescription()  + "/" + m.getLien()  + "/" + m.getImage()  + "/" + m.getLongitude()  + "/" + m.getLatitude()  + "/" + m.getCategorie() +"/";

        req.setUrl(url);
      
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<map> getAllmap() {
        String url = "http://127.0.0.1:8000/map/mapdata";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                map = parsemap(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return map;
    }
     public boolean modifiermap(map m) {
int userid=66;
       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = "http://127.0.0.1:8000/map/" + "modifjson/" + m.getId() + "/" +userid ;

        req.setUrl(url);
      
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean deletelike(map m) {
int userid=66;
       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = "http://127.0.0.1:8000/map/" + "deletejson/" + m.getId() + "/" +userid ;

        req.setUrl(url);
      
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
