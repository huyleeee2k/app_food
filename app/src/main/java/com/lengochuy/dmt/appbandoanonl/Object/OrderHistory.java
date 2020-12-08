package com.lengochuy.dmt.appbandoanonl.Object;

import java.io.Serializable;

public class OrderHistory implements Serializable {
    private String resourceID;
    private String nameFood;
    private String price;
    private String date;
    private String address;
    private String phoneNumber;


    public OrderHistory(String resourceID, String nameFood, String price, String date, String address, String phoneNumber) {
        this.resourceID = resourceID;
        this.nameFood = nameFood;
        this.price = price;
        this.date = date;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public OrderHistory() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
