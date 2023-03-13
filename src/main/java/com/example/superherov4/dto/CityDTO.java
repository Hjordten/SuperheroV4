package com.example.superherov4.dto;

import java.util.List;

public class CityDTO {
    private String city;
    private List<String> cityList;

    public CityDTO(String city, List<String> heroList) {
        this.city = city;
        this.cityList = heroList;
    }

    public String getCity() {
        return city;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void addSuperhero(String name){
        cityList.add(name);
    }
}