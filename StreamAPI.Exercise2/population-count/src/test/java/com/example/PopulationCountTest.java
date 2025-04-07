package com.example;

import org.junit.jupiter.api.Test;

import com.example.Model.City;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for PopulationCount class.
 */
public class PopulationCountTest {

    /**
     * Test for checking highest populated city of each country
     */
    @Test
    public void shouldFindHighestPopulatedCityByCountry() {
        // Setup: sample data
        List<City> cities = List.of(
            new City(1, "New York", "US", 8419600),
            new City(2, "Los Angeles", "US", 3980400),
            new City(3, "Mumbai", "IN", 12478447),
            new City(4, "Delhi", "IN", 11034555)
        );

        // Action: find highest populated city by country
        Map<String, Optional<City>> highestPopulatedCityByCountry = cities.stream()
            .collect(Collectors.groupingBy(
                City::getCountryCode, 
                Collectors.maxBy(Comparator.comparingInt(City::getPopulation))
            ));

        // Assertion: Verify expected highest populated city by country
        highestPopulatedCityByCountry.forEach((countryCode, city) -> {
            if (countryCode.equals("US")) {
                assertEquals("New York", city.orElse(null).getName());
            } else if (countryCode.equals("IN")) {
                assertEquals("Mumbai", city.orElse(null).getName());
            }
        });
    }

    /**
     * Basic test example
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}