package com.example.walkinclinicv01;

public class Feedback {
    private double rating;
    private int numberOfReviewers;
    //private String comment;

    public Feedback(double rating, int numberOfReviewers){
        this.rating = rating;
        this.numberOfReviewers = numberOfReviewers;
        //this.comment = comment;
    }
    public Feedback(){};
    public double getRating(){
        return rating;
    }

    public int getNumberOfReviewers(){
        return numberOfReviewers;
    }

    /*public String getComment(){
        return comment;
    }*/

}
