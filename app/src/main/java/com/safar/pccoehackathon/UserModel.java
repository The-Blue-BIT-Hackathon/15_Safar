package com.safar.pccoehackathon;

public class UserModel {
    private String name,messname,ownerphone,monthlyrate,email,password;

    public UserModel() {
    }

    public UserModel(String name, String messname, String ownerphone, String monthlyrate, String email, String password) {
        this.name = name;
        this.messname = messname;
        this.ownerphone = ownerphone;
        this.monthlyrate = monthlyrate;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

