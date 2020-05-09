package com.asg.clientapp;

public class MovieModel {



    public  String movieName;
    public String movieImage;
    public  String movievideo;
    public  String movieCategory;
    public int moviecount;
    public  String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public MovieModel(String movieName, String movieImage, String movievideo, String movieCategory, int moviecount, String createdAt) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movievideo = movievideo;
        this.movieCategory = movieCategory;
        this.moviecount = moviecount;
        this.createdAt = createdAt;
    }

    public MovieModel(String movieName, String movieImage, String movievideo, String movieCategory, int moviecount) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movievideo = movievideo;
        this.movieCategory = movieCategory;
        this.moviecount = moviecount;
    }

    public MovieModel(String movieName, String movieImage, String movievideo, String movieCategory) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movievideo = movievideo;
        this.movieCategory = movieCategory;
    }

    public MovieModel() {

    }

    public String getMovieName() {
        return movieName;

    }

    public int getMoviecount() {
        return moviecount;
    }

    public void setMoviecount(int moviecount) {
        this.moviecount = moviecount;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovievideo() {
        return movievideo;
    }

    public void setMovievideo(String movievideo) {
        this.movievideo = movievideo;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }
}
