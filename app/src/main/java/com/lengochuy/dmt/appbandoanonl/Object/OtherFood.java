package com.lengochuy.dmt.appbandoanonl.Object;

import java.io.Serializable;

public class OtherFood implements Serializable {
    private int id;
    private String resourceID;
    private String nameRestaurant;
    private String nameFood;
    private String price;

    public OtherFood(int id, String resourceID, String nameRestaurant, String nameFood, String price) {
        this.id = id;
        this.resourceID = resourceID;
        this.nameRestaurant = nameRestaurant;
        this.nameFood = nameFood;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
