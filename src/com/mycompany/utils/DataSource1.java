/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.codename1.io.ConnectionRequest;

/**
 *
 * @author aymen
 */
public class DataSource1 {
      private static DataSource1 instance;
    private ConnectionRequest request;

    public DataSource1() {
        request = new ConnectionRequest();
    }

    public static DataSource1 getInstance() {
        if (instance == null) {
            instance = new DataSource1();
        }
        return instance;
    }

    public ConnectionRequest getRequest() {
        return request;
    }
}
