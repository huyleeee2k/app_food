package com.lengochuy.dmt.appbandoanonl.Object;

import java.io.Serializable;

public class FoodSale implements Serializable {
    private int id;
    private String resourceID;
    private String nameRestaurant;
    private String nameFood;
    private String priceFood;
    private String sale;
    private String freeShip;

    public FoodSale(int id,String resourceID, String nameRestaurant, String nameFood, String priceFood, String sale, String freeShip) {
        this.id = id;
        this.resourceID = resourceID;
        this.nameRestaurant = nameRestaurant;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
        this.sale = sale;
        this.freeShip = freeShip;
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

    public String getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(String priceFood) {
        this.priceFood = priceFood;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getFreeShip() {
        return freeShip;
    }

    public void setFreeShip(String freeShip) {
        this.freeShip = freeShip;
    }
}
