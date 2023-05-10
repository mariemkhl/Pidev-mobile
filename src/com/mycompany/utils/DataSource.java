package com.mycompany.utils;


import com.codename1.io.ConnectionRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abderrazekbenhamouda
 */
public class DataSource {
    
   
      private static DataSource instance;
    private ConnectionRequest request;

    public DataSource() {
        request = new ConnectionRequest();
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public ConnectionRequest getRequest() {
        return request;
    }
}
