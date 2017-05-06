package com.example.emilstepanian.justhandworker.shared.model;

/**
 * Created by emilstepanian on 04/05/2017.
 */

import java.util.Date;

public class Job {

    private String title, description, location, date, mainImageTitle;
    private int id, userId, categoryId, mainImageResourceId;

    public String getMainImageTitle() {
        return mainImageTitle;
    }

    public void setMainImageTitle(String mainImageTitle) {
        this.mainImageTitle = mainImageTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMainImageResourceId() {
        return mainImageResourceId;
    }

    public void setMainImageResourceId(int mainImageResourceId) {
        this.mainImageResourceId = mainImageResourceId;
    }
}
