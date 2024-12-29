package org.example.models;

public class Review {
    private Object target;
    private Double rating;
    private String comment;

    public Review(Object target, Double rating, String comment) {
        this.target = target;
        this.rating = rating;
        this.comment = comment;
    }

    public Review() {}

    // Getters and setters
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

    public Object getTarget() {
        return target;
    }
}
