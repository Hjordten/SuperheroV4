package com.example.superherov4.dto;

import java.util.List;

public class SuperheropowerDTO {
    private String hero_id;
    private String superpower_id;
    private List<String> heroPowers;

    public SuperheropowerDTO(String hero_id, String superpower_id, List<String> heroPowers) {
        this.hero_id = hero_id;
        this.superpower_id = superpower_id;
        this.heroPowers = heroPowers;
    }


    public String getHero_id() {
        return hero_id;
    }

    public String getSuperpower_id() {
        return superpower_id;
    }

    public List<String> getHeroPowers() {
        return heroPowers;
    }

    public void addToList(String heroPower){
        heroPowers.add(heroPower);
    }
}