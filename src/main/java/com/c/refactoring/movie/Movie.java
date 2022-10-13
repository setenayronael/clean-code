package com.c.refactoring.movie;

public class Movie {

    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public boolean isValidRating() {
        if (this.getRating() != null) {
            return this.getRating().matches("(A[0-9]{2}|B[1-4])");
        }
        return false;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
