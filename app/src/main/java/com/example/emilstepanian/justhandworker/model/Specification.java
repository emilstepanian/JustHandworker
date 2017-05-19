package com.example.emilstepanian.justhandworker.model;

/**
 * Created by emilstepanian on 06/05/2017.
 */

public class Specification {

    int id, categoryId;
    String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
