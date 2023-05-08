/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class Reclamation {
    int numero;
    String Commentaire;
    String typereclamation;

    public Reclamation(int numero, String Commentaire, String typereclamation) {
        this.numero = numero;
        this.Commentaire = Commentaire;
        this.typereclamation = typereclamation;
    }

     public Reclamation( String Commentaire, String typereclamation) {
       
        this.Commentaire = Commentaire;
        this.typereclamation = typereclamation;
    }

    public Reclamation() {
       
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public String getTypereclamation() {
        return typereclamation;
    }

    public void setTypereclamation(String typereclamation) {
        this.typereclamation = typereclamation;
    }
    
}
