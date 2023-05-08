/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;



import java.util.Objects;

/**
 *
 * @author Nour Benkairia
 */
public class Category {
     public int id;
    public String nom;
    

    public Category() {
    }

    public Category(String nom) {
        this.nom = nom;
        
    }

    public Category(int id, String nom) {
        this.id = id;
        this.nom = nom;
       
    }

    public int getId_cat() {
        return id;
    }

    public void setId_cat(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return  nom ;
    }

  

   

   

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
