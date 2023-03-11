package com.safar.pccoehackathon;

public class Plate {
    String plateName;
    int price;

    String id;

    public Plate(String id, String plateName, int price) {
        this.plateName = plateName;
        this.price = price;
        this.id = id;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
