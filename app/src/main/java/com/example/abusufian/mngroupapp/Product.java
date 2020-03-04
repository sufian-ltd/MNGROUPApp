package com.example.abusufian.mngroupapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable{

    private static final long serialVersionUID=1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Product(String name, String description, int debit, int credit) {
        this.name = name;
        this.description = description;
        this.debit = debit;
        this.credit = credit;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

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

    @SerializedName("debit")
    private int debit;

    @Override
    public String toString() {
        return "Product{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user='" + user + '\'' +
                ", id=" + id +
                '}';
    }

    @SerializedName("credit")
    private int credit;
    @SerializedName("loc")
    private String loc;

    public Product(int id,String fullName, String username, String password, String user) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.user = user;
    }

    @SerializedName("contact")
    private String contact;
    private int qtn;
    @SerializedName("paid")
    private int paid;
    @SerializedName("due")
    private int due;

    @SerializedName("date")
    private String date;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Product(int id,String name, String description, int debit, int credit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.debit = debit;
        this.credit = credit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("fullName")
    private String fullName;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("user")
    private String user;
    @SerializedName("id")
    private int id;
}