package com.mycompany.entity;

import java.util.Date;

public class Reservations {

    private int id;
    private String name;
    private Date date;

    public Reservations() {
    }

    public Reservations(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Reservations(String name, Date date) {
        this.name = name;
        this.date = date;
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


}