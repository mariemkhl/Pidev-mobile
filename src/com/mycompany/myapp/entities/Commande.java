/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Commande {
    int idcommande;
    float prixTot;
    String userid;
    String payment;
    Date date;

    public Commande(int idcommande, float prixTot, String userid, String payment, Date date) {
        this.idcommande = idcommande;
        this.prixTot = prixTot;
        this.userid = userid;
        this.payment = payment;
        this.date = date;
    }

    public Commande() {
    
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public float getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(float prixTot) {
        this.prixTot = prixTot;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
