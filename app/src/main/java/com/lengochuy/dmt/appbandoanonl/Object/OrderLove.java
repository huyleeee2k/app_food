package com.lengochuy.dmt.appbandoanonl.Object;

public class OrderLove {
    private int id;
    private String username;
    private String resourceId;
    private String nameFood;
    private String price;

    public OrderLove(int id,String username, String resourceId, String nameFood, String price) {
        this.id = id;
        this.username = username;
        this.resourceId = resourceId;
        this.nameFood = nameFood;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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
