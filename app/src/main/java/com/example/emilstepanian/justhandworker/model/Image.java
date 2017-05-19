package com.example.emilstepanian.justhandworker.model;

/**
 * Created by emilstepanian on 06/05/2017.
 */

public class Image {

    private int id, jobId;
    private String imageTitle;

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

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
