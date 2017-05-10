package com.example.emilstepanian.justhandworker.shared.model;

/**
 * Created by Kasper on 07/05/2017.
 */

public class Bid {

    private int id;
    private int jobId;
    private double price;
    //TODO: Skulle denne fjernes fra databasen, og så var en evt comment bare en autogeneret message?
    //private String comment;
    private int userId;
    private boolean isAccepted;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bid() {}
}
