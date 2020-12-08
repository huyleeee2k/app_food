package com.lengochuy.dmt.appbandoanonl.Object;

public class NewOrder {
    private String resourceID;
    private String nameFood;
    private String price;
    private int amount;

    public NewOrder(String resourceID, String nameFood, String price, int amount) {
        this.resourceID = resourceID;
        this.nameFood = nameFood;
        this.price = price;
        this.amount = amount;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
