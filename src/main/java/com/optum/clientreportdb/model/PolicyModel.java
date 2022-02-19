package com.optum.clientreportdb.model;

import java.util.Date;

public class PolicyModel {

    private int id;
    private String client;
    private Date startDate;
    private Date endDate;
    private String lprNumber;
    private String policyNumber;
    private int isActive;

    public PolicyModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLprNumber() {
        return lprNumber;
    }

    public void setLprNumber(String lprNumber) {
        this.lprNumber = lprNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "PolicyModel{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", lprNumber='" + lprNumber + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
