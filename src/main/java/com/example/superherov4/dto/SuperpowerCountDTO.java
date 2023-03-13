package com.example.superherov4.dto;

import java.util.List;

public class SuperpowerCountDTO {
    private String heroName;
    private String realName;
    private int numberOfPowers;

    public SuperpowerCountDTO(String heroName, String realName, int numberOfPowers) {
        this.heroName = heroName;
        this.realName = realName;
        this.numberOfPowers = numberOfPowers;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public int getNumberOfPowers() {
        return numberOfPowers;
    }
}