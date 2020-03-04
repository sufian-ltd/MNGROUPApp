package com.example.abusufian.mngroupapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Market implements Serializable {

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtn() {
        return qtn;
    }

    public void setQtn(int qtn) {
        this.qtn = qtn;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    private static final long serialVersionUID=1L;
    @SerializedName("name")
    private String name;
    @SerializedName("loc")
    private String loc;
    @SerializedName("contact")
    private String contact;
    @SerializedName("description")
    private String description;
    @SerializedName("qtn")
    private int qtn;
    @SerializedName("paid")
    private int paid;
    @SerializedName("due")
    private int due;
}
