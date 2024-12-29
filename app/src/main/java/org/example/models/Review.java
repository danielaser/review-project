package org.example.models;

public class Review {
    private String targetType;
    private Double rating;
    private String comment;

    public Review(String targetType, Double rating, String comment) {
        this.targetType = targetType;
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

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
