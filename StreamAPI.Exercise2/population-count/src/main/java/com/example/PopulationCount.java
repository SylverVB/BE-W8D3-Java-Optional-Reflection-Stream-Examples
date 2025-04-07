// Exercise: Population Count App.

// In this exercise, there are two domain classes: Country and City. Each city belongs to a country defined 
// by the attribute, countryCode. Each country has a unique code and has many cities.

// Task 1:
// Write a Java program to find the highest populated city of each country.

// Task 2:
// Write a Java program to find the highest populated city of each continent.

package com.example;

import java.util.*;
import java.util.stream.Collectors;

import com.example.Model.City;
import com.example.Model.Country;

public class PopulationCount {
    public static void main(String[] args) {
        // Sample data
        List<Country> countries = List.of(
            new Country("US", "United States", "North America", 331000000, 9833517, 21000000, 0),
            new Country("IN", "India", "Asia", 1380000000, 3287263, 2875000, 0)
        );

        List<City> cities = List.of(
            new City(1, "New York", "US", 8419600),
            new City(2, "Los Angeles", "US", 3980400),
            new City(3, "Mumbai", "IN", 12478447),
            new City(4, "Delhi", "IN", 11034555)
        );

        // Task 1: Find the highest populated city of each country
        System.out.println("Exercise 1: Highest populated city of each country:");
        Map<String, Optional<City>> highestPopulatedCityByCountry = cities.stream()
            .collect(Collectors.groupingBy(
                City::getCountryCode, 
                Collectors.maxBy(Comparator.comparingInt(City::getPopulation))
            ));

        highestPopulatedCityByCountry.forEach((countryCode, city) ->
            System.out.println("Country Code: " + countryCode + ", City: " + city.orElse(null))
        );

        System.out.println();

        // Task 2: Find the highest populated city of each continent
        System.out.println("Exercise 2: Highest populated city of each continent:");
        Map<String, Country> countryMap = countries.stream()
            .collect(Collectors.toMap(Country::getCode, country -> country));

        Map<String, Optional<City>> highestPopulatedCityByContinent = cities.stream()
            .collect(Collectors.groupingBy(
                city -> countryMap.get(city.getCountryCode()).getContinent(),
                Collectors.maxBy(Comparator.comparingInt(City::getPopulation))
            ));

        highestPopulatedCityByContinent.forEach((continent, city) ->
            System.out.println("Continent: " + continent + ", City: " + city.orElse(null))
        );
    }
}