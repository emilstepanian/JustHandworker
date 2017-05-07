package com.example.emilstepanian.justhandworker.shared.model;

/**
 * Created by emilstepanian on 06/05/2017.
 */

public class RequiredInfoValue {
private int id, jobId, requiredInfoId;
    private String value;

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

    public int getRequiredInfoId() {
        return requiredInfoId;
    }

    public void setRequiredInfoId(int requiredInfoId) {
        this.requiredInfoId = requiredInfoId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
