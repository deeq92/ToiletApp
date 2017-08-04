package com.diazapps.toiletapp;

import java.util.ArrayList;

public class Toilet {

    private String location_name;
    private String location_description;
    private double rating;
    private ArrayList<Comments> comments;


    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_description() {
        return location_description;
    }

    public void setLocation_description(String location_description) {
        this.location_description = location_description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
