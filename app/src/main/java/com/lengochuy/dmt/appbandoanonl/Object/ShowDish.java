package com.lengochuy.dmt.appbandoanonl.Object;

import java.io.Serializable;

public class ShowDish implements Serializable {
    private String resourceID;
    private String nameRestaurant;
    private String nameFood;
    private String price;

    public ShowDish(){

    }

    public ShowDish(String resourceID, String nameRestaurant, String nameFood, String price) {
        this.resourceID = resourceID;
        this.nameRestaurant = nameRestaurant;
        this.nameFood = nameFood;
        this.price = price;
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
