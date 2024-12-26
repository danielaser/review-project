package org.example.models;

public class Review {
    private Double rating;
    private String comment;

    public Review(Double rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Review() {
    }

    //getters and setters
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
