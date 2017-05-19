package com.example.emilstepanian.justhandworker.model;

/**
 * Created by emilstepanian on 10/05/2017.
 */

public class Category {

    private int id, professionId;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
