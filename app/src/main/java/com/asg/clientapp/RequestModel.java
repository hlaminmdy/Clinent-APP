package com.asg.clientapp;

public class RequestModel {

    public String name;
    public String imagelink;
    public String createAd;

    public String getCreateAd() {
        return createAd;
    }

    public void setCreateAd(String createAd) {
        this.createAd = createAd;
    }

    public RequestModel(String name, String imagelink, String createAd) {
        this.name = name;
        this.imagelink = imagelink;
        this.createAd = createAd;
    }

    public RequestModel(String name, String imagelink) {
        this.name = name;
        this.imagelink = imagelink;
    }

    public RequestModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
