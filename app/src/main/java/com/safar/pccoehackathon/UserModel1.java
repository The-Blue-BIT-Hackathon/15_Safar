package com.safar.pccoehackathon;

public class UserModel1 {

    private String name,customerphone,email,password;

    public UserModel1() {
    }

    public UserModel1(String name, String customerphone, String email, String password) {
        this.name = name;
        this.customerphone = customerphone;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
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
