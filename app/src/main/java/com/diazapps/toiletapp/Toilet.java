package com.diazapps.toiletapp;

import java.io.Serializable;

public class Toilet implements Serializable{

    private String id;
    private String location_name;
    private String location_address;
    private String comment;
    private double location_long;
    private double location_lat;
    private double rating;
    private double distance_away;

    Toilet(String title, double rating, String address, String comment,
           double location_lat, double location_long){
        location_name = title;
        this.rating = rating;
        location_address = address;
        this.comment = comment;
        this.location_lat = location_lat;
        this.location_long = location_long;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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


    public double getDistance_away() {
        return distance_away;
    }

    public void setDistance_away(double distance_away) {
        this.distance_away = distance_away;
    }
}
