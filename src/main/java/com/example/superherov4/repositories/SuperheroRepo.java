package com.example.superherov4.repositories;

import com.example.superherov4.dto.CitiesWithHeroesDTO;
import com.example.superherov4.dto.CityDTO;
import com.example.superherov4.dto.SuperheropowerDTO;
import com.example.superherov4.dto.SuperpowerCountDTO;
import com.example.superherov4.model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class SuperheroRepo {
    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    public List<Superhero> getSuperheroes() {
        List<Superhero> superheroList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT superhero_id, hero_name, real_name, creation_year FROM superhero;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Superhero superhero = new Superhero(
                        rs.getString("superhero_id"),
                        rs.getString("hero_name"),
                        rs.getString("real_name"),
                        rs.getString("creation_year")
                );
                superheroList.add(superhero);
            }
            return superheroList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Superhero> searchHero(String heroName) {
        List<Superhero> superheroList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT superhero_id, hero_name, real_name, creation_year FROM superhero WHERE hero_name = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, heroName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Superhero superhero = new Superhero(
                        rs.getString("hero_name"),
                        rs.getString("real_name"),
                        rs.getString("creation_year"),
                        rs.getString("creation_year")
                );
                superheroList.add(superhero);
            }
            return superheroList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<CityDTO> getCityAndHeroes(String name) {
        List<CityDTO> superheroesCity = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT city, hero_name FROM city JOIN superhero " +
                    "WHERE superhero.city_ID = city.city_ID AND city =?";


            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            String currentCity = "";
            CityDTO heroCity = null;

            while (rs.next()) {
                String city = rs.getString("city");
                String heroName = rs.getString("hero_name");

                if (city.equals(currentCity)) {
                    heroCity.addSuperhero(heroName);
                } else {
                    heroCity = new CityDTO(city, new ArrayList<>(List.of(heroName)));
                    superheroesCity.add(heroCity);
                    currentCity = city;
                }
            }
            return superheroesCity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<SuperpowerCountDTO> countPowersList() {
        List<SuperpowerCountDTO> superpowerCountDTOList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, COUNT(superheropower.superhero_id) AS count FROM superhero JOIN superheropower USING(superhero_id) GROUP BY superhero_id;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                int count = rs.getInt("count");

                superpowerCountDTOList.add(new SuperpowerCountDTO(heroName, realName, count));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return superpowerCountDTOList;
    }

    public SuperpowerCountDTO getAllHeroesWithPowerCount(String name) {
            try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
                SuperpowerCountDTO superpowerCount = null;
                String SQL = "SELECT superheropower.superhero_id, hero_name, real_name, COUNT(superheropower.superhero_id) AS count " +
                        "FROM superhero JOIN superheropower WHERE superheropower.superhero_id = superhero.superhero_id AND hero_name =?;";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String heroName = rs.getString("hero_name");
                    String realName = rs.getString("real_name");
                    int count = rs.getInt("count");

                    superpowerCount = new SuperpowerCountDTO(heroName, realName, count);
                }
                return superpowerCount;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public SuperheropowerDTO getHeroSuperpower(String name) {
        SuperheropowerDTO superheroSuperpower = null;

        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, superpower FROM superhero " +
                    "JOIN superpower JOIN superheropower ON superpower.superpower_id = superheropower.superpower_id " +
                    "AND superhero.superhero_id = superheropower.superhero_id AND hero_name = ?;";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            String currentHeroName = "";
            while (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String superpower = rs.getString("superpower");

                if (heroName.equals(currentHeroName)) {
                    superheroSuperpower.addToList(superpower);
                } else {
                    superheroSuperpower = new SuperheropowerDTO(heroName, realName, new ArrayList<>(List.of(superpower)));
                    currentHeroName = heroName;
                }
            }
            return superheroSuperpower;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
