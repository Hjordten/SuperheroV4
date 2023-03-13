package com.example.superherov4.model;

public class Superhero {
    private String superhero_id;
    private String hero_name;
    private String real_name;
    private String creation_year;


    public Superhero(String superhero_id, String hero_name, String real_name, String creation_year) {
        this.superhero_id = superhero_id;
        this.hero_name = hero_name;
        this.real_name = real_name;
        this.creation_year = creation_year;
    }

    public Superhero(String hero_name, String real_name, String creation_year) {
        this.hero_name = hero_name;
        this.real_name = real_name;
        this.creation_year = creation_year;
    }

    public String getSuperhero_id() {
        return superhero_id;
    }

    public String getHero_name() {
        return hero_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public String getCreation_year() {
        return creation_year;
    }


    public void setSuperhero_id(String superhero_id) {
        this.superhero_id = superhero_id;
    }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public void setCreation_year(String creation_year) {
        this.creation_year = creation_year;
    }


}