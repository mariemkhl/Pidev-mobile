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

public class Paiment {
    int id;
    int commande;
    int numCarte;
    String nomCarte;
    Date dateEx;
    int cvCode;
    int prixTot;

    public Paiment() {
        // Parameterless constructor
    }

    public Paiment(int id, int commande, int numCarte, String nomCarte, Date dateEx, int cvCode, int prixTot) {
        this.id = id;
        this.commande = commande;
        this.numCarte = numCarte;
        this.nomCarte = nomCarte;
        this.dateEx = dateEx;
        this.cvCode = cvCode;
        this.prixTot = prixTot;
    }
    
    public Paiment(int id, int numCarte, String nomCarte, Date dateEx, int cvCode, int prixTot) {
        this.id = id;
        this.numCarte = numCarte;
        this.nomCarte = nomCarte;
        this.dateEx = dateEx;
        this.cvCode = cvCode;
        this.prixTot = prixTot;
    }

    public Paiment(int numCarte, String nomCarte, Date dateEx, int cvCode, int prixTot) {
        this.numCarte = numCarte;
        this.nomCarte = nomCarte;
        this.dateEx = dateEx;
        this.cvCode = cvCode;
        this.prixTot = prixTot;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public int getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(int numCarte) {
        this.numCarte = numCarte;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    public Date getDateEx() {
        return dateEx;
    }

    public void setDateEx(Date dateEx) {
        this.dateEx = dateEx;
    }

    public int getCvCode() {
        return cvCode;
    }

    public void setCvCode(int cvCode) {
        this.cvCode = cvCode;
    }

    public int getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(int prixTot) {
        this.prixTot = prixTot;
    }
    
    
}
