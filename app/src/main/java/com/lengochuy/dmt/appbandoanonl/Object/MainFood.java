package com.lengochuy.dmt.appbandoanonl.Object;

public class MainFood {
    private int resourceID;
    private String titleNameFood;

    public MainFood(int resourceID, String titleNameFood) {
        this.resourceID = resourceID;
        this.titleNameFood = titleNameFood;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getTitleNameFood() {
        return titleNameFood;
    }

    public void setTitleNameFood(String titleNameFood) {
        this.titleNameFood = titleNameFood;
    }
}
