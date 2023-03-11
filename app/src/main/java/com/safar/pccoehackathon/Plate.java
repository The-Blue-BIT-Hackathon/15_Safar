package com.safar.pccoehackathon;

public class Plate {
    String plateName, type, allergies, glutenFree, price;

    public Plate() {
    }

    public Plate(String plateName, String type, String allergies, String glutenFree, String price) {
        this.plateName = plateName;
        this.type = type;
        this.allergies = allergies;
        this.glutenFree = glutenFree;
        this.price = price;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(String glutenFree) {
        this.glutenFree = glutenFree;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
