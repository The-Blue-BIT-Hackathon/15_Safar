package com.safar.pccoehackathon;

public class UserModel {
    private String id, name, messname, ownerphone, monthlyrate, email, location;

    public UserModel(String name, String messname, String ownerphone) {
        this.name = name;
        this.messname = messname;
        this.ownerphone = ownerphone;
        this.location = "NA";
    }

    public UserModel(String id, String name, String messname, String ownerphone, String monthlyrate, String email) {
        this.name = name;
        this.id = id;
        this.messname = messname;
        this.ownerphone = ownerphone;
        this.monthlyrate = monthlyrate;
        this.email = email;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessname() {
        return messname;
    }

    public void setMessname(String messname) {
        this.messname = messname;
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone;
    }

    public String getMonthlyrate() {
        return monthlyrate;
    }

    public void setMonthlyrate(String monthlyrate) {
        this.monthlyrate = monthlyrate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

