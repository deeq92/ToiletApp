package com.diazapps.toiletapp;

import java.util.ArrayList;

public class Toilet {

    private String location_name;
    private String location_address;
    private String description;
    private double location_long;
    private double location_lat;
    private double rating;

    Toilet(String title, double rating, String address, String comment){
        location_name = title;
        this.rating = rating;
        location_address = address;
        this.description = comment;
    }

    public String getComment() {
        return description;
    }

    public void setComment(String comment) {
        this.description = comment;
    }

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

    public double getLocation_long() {
        return location_long;
    }

    public void setLocation_long(double location_long) {
        this.location_long = location_long;
    }

    public double getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(double location_lat) {
        this.location_lat = location_lat;
    }


}
