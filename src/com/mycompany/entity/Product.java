/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;



import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Nour Benkairia
 */
public class Product {
     private int id;
    
    private String nom;
    private String description;
    private double prix;
    private String img;
    private String categ;
    private int user;
    private String url;
     private Date dateA;
    

    public Product() {
    }

    public Product(int id, String nom, String description, double prix, String img, String categ, int user, String url, Date dateA) {
        this.id = id;
        
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.categ = categ;
        this.user = user;
        this.url = url;
        this.dateA = dateA;
    }
    

    public Product(int id, String nom, String description, double prix, String img, String categ, int user, String url) {
        this.id = id;
       
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.categ = categ;
        this.user = user;
        this.url = url;
        
    }

    
    
    public Product(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Product(int id, String nom, double prix, String img, String url) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.img = img;
        this.url = url;
    }
    
    

    public Product(String nom, String description, double prix, String categ, int user) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categ = categ;
        this.user = user;
    }
    

    public Product(String nom, String description, double prix, String img, String categ, int user) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.categ = categ;
        this.user = user;
    }

    public Product(int id, String nom, String description, double prix, String img, Category cat_p, int user) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.categ = categ;
        this.user = user;
    }

//    public Product(int id, String nom, String description, double prix, String img, String categ, int user, String url) {
//        this.id = id;
//        this.nom = nom;
//        this.description = description;
//        this.prix = prix;
//        this.img = img;
//        this.categ = categ;
//        this.user = user;
//        this.url = url;
//    }
    
    

    public int getId_p() {
        return id;
    }

    public void setId_p(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCat_p() {
        return categ;
    }

    public void setCat_p(String categ) {
        this.categ = categ;
    }

   
    public int getUser_id() {
        return user;
    }

    public void setUser_id(int user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDateA() {
        return dateA;
    }

    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", img=" + img + ", categ=" + categ + ", user=" + user + ", url=" + url + ", dateA=" + dateA + '}';
    }
    
    

  
    
    
    

  
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
