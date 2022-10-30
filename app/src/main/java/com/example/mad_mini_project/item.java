package com.example.mad_mini_project;

import android.media.Image;

public class item {

    private  String name;
    private  String price;
    private  String url;

    public item() {

    }

    public item(String name, String price, String url, Image cart) {
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
