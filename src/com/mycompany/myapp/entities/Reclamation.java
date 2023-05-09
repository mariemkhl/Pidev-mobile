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
    String typeReclamation;

    public Reclamation(int numero, String Commentaire, String typereclamation) {
        this.numero = numero;
        this.Commentaire = Commentaire;
        this.typeReclamation = typeReclamation;
    }

     public Reclamation( String Commentaire, String typeReclamation) {
       
        this.Commentaire = Commentaire;
        this.typeReclamation = typeReclamation;
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
        return typeReclamation;
    }

    public void setTypereclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }
    
}
