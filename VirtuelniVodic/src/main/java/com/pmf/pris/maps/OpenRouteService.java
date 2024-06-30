package com.pmf.pris.maps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OpenRouteService {

    private static final String API_KEY = "5b3ce3597851110001cf62487ffb754b71cc46e79354c1777b9efdd1";
    private static final String MATRIX_API_URL = "https://api.openrouteservice.org/v2/matrix/";

    public int getDistance(String prviLat, String prviLong, String drugiLat, String drugiLong, String mode) {
        // Validate mode
        if (!"driving-car".equals(mode) && !"foot-walking".equals(mode)) {
            throw new IllegalArgumentException("Mode must be either 'driving-car' or 'foot-walking'");
        }

        String url = MATRIX_API_URL + mode + "?api_key=" + API_KEY + "&start=" + prviLong + "," + prviLat + "&end=" + drugiLong + "," + drugiLat;

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        // Send request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Parse JSON response
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(response.getBody());
            JsonNode distanceNode = rootNode
                    .path("features")
                    .get(0)
                    .path("properties")
                    .path("segments")
                    .get(0)
                    .path("distance");

            double distanceInMeters = distanceNode.asDouble();
            // Convert distance from meters to kilometers and then to integer
            return (int) (distanceInMeters / 1000);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

    // Method to get the durations matrix from OpenRouteService
    public double[][] getDurationsMatrix(List<double[]> locations, String profile) {
        String url = MATRIX_API_URL + profile;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", API_KEY);

        // Construct JSON body for the request
        String body = constructRequestBody(locations);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        // Send request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        // Parse the response to extract the durations matrix
        return parseDurationsMatrix(response.getBody());
    }

    // Method to find the shortest route based on the durations matrix (TSP Solver)
    public List<Integer> findShortestRoute(double[][] durations) {
        int n = durations.length;
        List<Integer> shortestRoute = new ArrayList<>();
        double shortestDuration = Double.MAX_VALUE;

        // Generate all permutations of routes (brute force approach for small n)
        List<List<Integer>> permutations = generatePermutations(n);

        for (List<Integer> route : permutations) {
            double currentDuration = calculateRouteDuration(route, durations);
            if (currentDuration < shortestDuration) {
                shortestDuration = currentDuration;
                shortestRoute = new ArrayList<>(route);
            }
        }

        // Add the start point to the end to complete the loop
        shortestRoute.add(shortestRoute.get(0));

        return shortestRoute;
    }

    // Helper method to construct JSON body for the matrix request
    private String constructRequestBody(List<double[]> locations) {
        StringBuilder body = new StringBuilder();
        body.append("{\"locations\":[");
        for (int i = 0; i < locations.size(); i++) {
            double[] location = locations.get(i);
            body.append("[").append(location[1]).append(",").append(location[0]).append("]");
            if (i < locations.size() - 1) {
                body.append(",");
            }
        }
        body.append("]}");
        return body.toString();
    }

    // Helper method to parse the durations matrix from the JSON response
    private double[][] parseDurationsMatrix(String responseBody) {
        // Use a JSON parsing library like Jackson to parse the durations array from the response
        // Assuming the "durations" array is at the top level of the response JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(responseBody);
            JsonNode durationsNode = root.path("durations");
            int n = durationsNode.size();
            double[][] durations = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    durations[i][j] = durationsNode.get(i).get(j).asDouble();
                }
            }
            return durations;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse durations matrix from response", e);
        }
    }

    // Helper method to generate all permutations of route indices
    private List<List<Integer>> generatePermutations(int n) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i);
        }
        generatePermutationsHelper(nums, 0, permutations);
        return permutations;
    }

    // Recursive helper method to generate permutations
    private void generatePermutationsHelper(List<Integer> nums, int index, List<List<Integer>> permutations) {
        if (index == nums.size() - 1) {
            permutations.add(new ArrayList<>(nums));
            return;
        }
        for (int i = index; i < nums.size(); i++) {
            Collections.swap(nums, i, index);
            generatePermutationsHelper(nums, index + 1, permutations);
            Collections.swap(nums, i, index);
        }
    }

    // Helper method to calculate the total duration of a given route
    private double calculateRouteDuration(List<Integer> route, double[][] durations) {
        double totalDuration = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDuration += durations[route.get(i)][route.get(i + 1)];
        }
        // Add the duration to return to the starting point
        totalDuration += durations[route.get(route.size() - 1)][route.get(0)];
        return totalDuration;
    }
}