package com.lengochuy.dmt.appbandoanonl.Object;

import java.io.Serializable;

public class FoodSelling implements Serializable {
    private int id;
    private String resourceID;
    private String nameRestaurant;
    private String address;

    public FoodSelling(int id, String resourceID, String nameRestaurant, String address) {
        this.id = id;
        this.resourceID = resourceID;
        this.nameRestaurant = nameRestaurant;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
