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

@Service
public class OpenRouteService {

    private static final String API_KEY = "5b3ce3597851110001cf62487ffb754b71cc46e79354c1777b9efdd1";
    private static final String DISTANCE_MATRIX_API_URL = "https://api.openrouteservice.org/v2/directions/driving-car";

    public int getDistance(String prviLat, String prviLong, String drugiLat, String drugiLong) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", API_KEY);

        String url = DISTANCE_MATRIX_API_URL + "?start=" + prviLong + "," + prviLat + "&end=" + drugiLong + "," + drugiLat;

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

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
            System.out.println(distanceInMeters);
            // Convert distance from meters to kilometers and then to integer
            return (int) (distanceInMeters / 1000);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse JSON response");
        }
    }
}