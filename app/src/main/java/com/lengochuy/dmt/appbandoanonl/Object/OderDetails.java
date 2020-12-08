package com.lengochuy.dmt.appbandoanonl.Object;

public class OderDetails {
    private String id;
    private String nameRestaurant;
    private String nameFood;
    private String priceFood;

    public OderDetails(String id, String nameRestaurant, String nameFood, String priceFood) {
        this.id = id;
        this.nameRestaurant = nameRestaurant;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
