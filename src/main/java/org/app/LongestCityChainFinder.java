package org.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestCityChainFinder {
    public static void main(String[] args) {
        String filename = "cities.txt";

        List<String> cities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cities.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String longestCityChain = findLongestCityChain(cities);

        System.out.println(longestCityChain);
    }

    private static String findLongestCityChain(List<String> cities) {
        String longestChain = "";

        for (String city : cities) {
            Set<String> visitedCities = new HashSet<>();
            String chain = buildCityChain(city, cities, visitedCities);

            if (chain.length() > longestChain.length()) {
                longestChain = chain;
            }
        }

        return longestChain;
    }

    private static String buildCityChain(String currentCity, List<String> cities, Set<String> visitedCities) {
        visitedCities.add(currentCity);

        String chain = currentCity;

        for (String city : cities) {
            if (!visitedCities.contains(city) && city.toLowerCase().charAt(0) == currentCity.charAt(currentCity.length() - 1)) {
                String subChain = buildCityChain(city, cities, visitedCities);

                if (subChain.length() > chain.length()) {
                    chain = currentCity + " " + subChain;
                }
            }
        }

        visitedCities.remove(currentCity);

        return chain;
    }
}