package com.example.superherov4.dto;

import java.util.List;

public class CitiesWithHeroesDTO {
    private String cityName;
    private List<String> superheroName;

    public CitiesWithHeroesDTO(String cityName, List<String> superheroName) {
        this.cityName = cityName;
        this.superheroName = superheroName;
    }

    public String getCityName() {
        return cityName;
    }

    public List<String> getSuperheroName() {
        return superheroName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setSuperheroName(List<String> superheroName) {
        this.superheroName = superheroName;
    }


}