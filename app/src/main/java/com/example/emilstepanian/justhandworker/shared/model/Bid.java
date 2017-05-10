package com.example.emilstepanian.justhandworker.shared.model;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by Kasper on 07/05/2017.
 */

public class Bid {
    //Model members
    String name;
    int jobId;
    double price;
    String comment;
    int userId;
    boolean isAccepted;
    Date date;

    //View members
    int imageId;

    public int getJobId() {
        return jobId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bid(String name) {
        this.name = name;
    }
}
