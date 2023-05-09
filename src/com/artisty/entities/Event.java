package com.artisty.entities;

import java.util.Date;

public class Event {

    private int id;
    private String name;
    private Date date;
    private String location;
    private Utilisateur utilisateur;
    private String categorie;
    private int nbplacetotal;
    private String image;
    private String description;

    public Event() {
    }

    public Event(int id, String name, Date date, String location, Utilisateur utilisateur, String categorie, int nbplacetotal, String image, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.nbplacetotal = nbplacetotal;
        this.image = image;
        this.description = description;
    }

    public Event(String name, Date date, String location, Utilisateur utilisateur, String categorie, int nbplacetotal, String image, String description) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.nbplacetotal = nbplacetotal;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbplacetotal() {
        return nbplacetotal;
    }

    public void setNbplacetotal(int nbplacetotal) {
        this.nbplacetotal = nbplacetotal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}