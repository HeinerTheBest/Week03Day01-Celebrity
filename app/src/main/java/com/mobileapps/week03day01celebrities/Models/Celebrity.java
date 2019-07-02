package com.mobileapps.week03day01celebrities.Models;

public class Celebrity
{
    private long id;
    private String name;
    private String category;
    private String birthDate;
    private String bornCountry;
    private String bio;

    public Celebrity()
    {
    }

    public Celebrity(String name, String category, String birthDate, String bornCountry, String bio) {
        this.name = name;
        this.category = category;
        this.birthDate = birthDate;
        this.bornCountry = bornCountry;
        this.bio = bio;
    }

    public Celebrity(String id, String name, String category, String birthDate, String bornCountry, String bio)
    {
        this.id = Long.parseLong(id);
        this.name = name;
        this.category = category;
        this.birthDate = birthDate;
        this.bornCountry = bornCountry;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBornCountry() {
        return bornCountry;
    }

    public void setBornCountry(String bornCountry) {
        this.bornCountry = bornCountry;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
