package com.mobileapps.week03day01celebrities.Models;

public class Celebrity
{
    private long    id;
    private String  firstName;
    private String  lastName;
    private String  mostPopularMovie;
    private boolean isAlive;
    private String  mostRecentScandal;
    private boolean isFavorite;
    private byte[]  picture;

    public Celebrity()
    {
    }

    public Celebrity(String id, String firstName, String lastName, String mostPopularMovie, boolean isAlive, String mostRecentScandal, boolean isFavorite, byte[] picture) {
        this.id = Long.parseLong(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.mostPopularMovie = mostPopularMovie;
        this.isAlive = isAlive;
        this.mostRecentScandal = mostRecentScandal;
        this.isFavorite = isFavorite;
        this.picture = picture;
    }

    public Celebrity(String firstName, String lastName, String mostPopularMovie, boolean isAlive, String mostRecentScandal, boolean isFavorite, byte[] picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mostPopularMovie = mostPopularMovie;
        this.isAlive = isAlive;
        this.mostRecentScandal = mostRecentScandal;
        this.isFavorite = isFavorite;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMostPopularMovie() {
        return mostPopularMovie;
    }

    public void setMostPopularMovie(String mostPopularMovie) {
        this.mostPopularMovie = mostPopularMovie;
    }

    public String isAlive() {
        return String.valueOf(isAlive);
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getMostRecentScandal() {
        return mostRecentScandal;
    }

    public void setMostRecentScandal(String mostRecentScandal) {
        this.mostRecentScandal = mostRecentScandal;
    }

    public String isFavorite() {
        return String.valueOf(isFavorite);
    }

    public boolean isFavoriteBool(){return isFavorite;}

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
