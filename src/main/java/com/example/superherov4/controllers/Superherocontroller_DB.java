package com.example.superherov4.controllers;

import com.example.superherov4.dto.CitiesWithHeroesDTO;
import com.example.superherov4.dto.CityDTO;
import com.example.superherov4.dto.SuperheropowerDTO;
import com.example.superherov4.dto.SuperpowerCountDTO;
import com.example.superherov4.model.Superhero;
import com.example.superherov4.repositories.SuperheroRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = {"/superhero"})
@Controller
public class Superherocontroller_DB {
    private SuperheroRepo superheroRepo;

    public Superherocontroller_DB(SuperheroRepo superheroRepo) {
        this.superheroRepo = superheroRepo;
    }

    @GetMapping("/herolist") //DONE, works as intended
    public ResponseEntity<List<Superhero>> heroList() {
        List<Superhero> heroList = superheroRepo.getSuperheroes();
        return new ResponseEntity<>(heroList, HttpStatus.OK);
    }

    @GetMapping(path = "herolist/{heroName}") //DONE, works as intended
    public ResponseEntity<List<Superhero>> searchHeroList(@PathVariable String heroName) {
        List<Superhero> heroList = superheroRepo.searchHero(heroName);
        return new ResponseEntity<>(heroList, HttpStatus.OK);
    }


    @GetMapping(path = "city/{cityName}") //DONE, works as intended
    public ResponseEntity<List<CityDTO>> searchByCity(@PathVariable String cityName) {
        List<CityDTO> cityDTO  = superheroRepo.getCityAndHeroes(cityName);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/count") //DONE, works as intended
    public ResponseEntity<List<SuperpowerCountDTO>> countSuperpowers() {
        List<SuperpowerCountDTO> countPowersList = superheroRepo.countPowersList();
        return new ResponseEntity<>(countPowersList, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/count/{name}") //DONE, works as intended
    public ResponseEntity<SuperpowerCountDTO> getAllHeroesWithPowerCount(@PathVariable String name){
        SuperpowerCountDTO superpowerCountDTO = superheroRepo.getAllHeroesWithPowerCount(name);
        return new ResponseEntity<>(superpowerCountDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/{name}")
    public ResponseEntity<SuperheropowerDTO> getOneHeroWithPower(@PathVariable String name){
        SuperheropowerDTO superheropowerDTO = superheroRepo.getHeroSuperpower(name);
        return new ResponseEntity<>(superheropowerDTO, HttpStatus.OK);
    }





}