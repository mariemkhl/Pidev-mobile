/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.mycompany.myapp.utils.DataSource;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entity.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;



/**
 *
 * @author Abderrazekbenhamouda
 */
public class User_Service {
    
    
    
  private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<User> users;
    
    

    public User_Service() {
        request = DataSource.getInstance().getRequest();
    }

   public boolean addUser(User user) {
     
        String url = Statics.BASE_URL + "api/user/adduser?nom=" + user.getNom()+ "&email=" + user.getEmail()+ "&password=" + user.getPassword()+  "&role=" + user.getRoles()+ "&adresse=" + user.getAdresse()+ "&domaine=" + user.getDomaine()+ "&num=" + user.getNumTel();

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
   public boolean ModifierUser(User user,int id) {
     
        String url = Statics.BASE_URL + "api/user/modifierUser?nom=" + user.getNom()+ "&email=" + user.getEmail()+ "&domaine=" + user.getDomaine()+ "&role=" + user.getRoles()+ "&adresse=" + user.getAdresse()+ "&domaine=" + user.getDomaine()+ "&num=" + user.getNumTel()+ "&id=" +id;

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
   
    public ArrayList<User> getAllusers() {
        String url = Statics.BASE_URL + "api/user/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    users = parseType(new String(request.getResponseData()));
                } catch (ParseException ex) {
                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
 
    public ArrayList<User> parseType(String jsonText) throws ParseException {
        try {
            users = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ;
                int id = (int) Float.parseFloat(obj.get("id").toString());

                String email = obj.get("Email").toString();
                String nom = obj.get("username").toString();
                String role = obj.get("roles").toString();
                String password = obj.get("Password").toString();
                String prenom = obj.get("domaine").toString();
                String adresse = obj.get("Adresse").toString();
                String bloquer = obj.get("isActive").toString();

                int numTel = (int)Float.parseFloat(obj.get("NumTel").toString());
                

                

                User v = new User();
                v.setAdresse(adresse);
                v.setNom(nom);
                v.setEmail(email);
                v.setDomaine(prenom);
                v.setPassword(password);
                v.setNumTel(numTel);
                if (bloquer.equals("true"))
                v.setBlock(1);
                else
                v.setBlock(0);                        
                v.setRoles(role);
              //  v.setDescription(commentaire);
                v.setId(id);
             //   v.setDure(datedebut);  
              //  v.setVeterinaire(veterinaire);

                
                users.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return users;
    }

      public User  get_User(int id)
      {
          for( User u :getAllusers()  )
          {
              if (u.getId()==id)
              {
                  return u;
              }
          }
          return null;
  
      }
      public boolean DesactiverUser(int id) 
      {
            String url = Statics.BASE_URL + "api/block?id=" + id;

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

      
       public boolean activerUser(int id) 
      {
            String url = Statics.BASE_URL + "api/unblock?id=" + id;

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
