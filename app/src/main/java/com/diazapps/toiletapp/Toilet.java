package com.diazapps.toiletapp;

import java.util.ArrayList;

public class Toilet {

    private String location_name;
    private String location_address;
    private double rating;
    private ArrayList<Comments> comments;


    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
