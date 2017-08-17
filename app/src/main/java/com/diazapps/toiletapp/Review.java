package com.diazapps.toiletapp;

class Review {
    public Review(double rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    private double rating;
    private String comment;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
