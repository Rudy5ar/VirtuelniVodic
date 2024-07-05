package com.pmf.pris.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    private String apiKey = "5b3ce3597851110001cf62487ffb754b71cc46e79354c1777b9efdd1";
    private final String baseUrl = "https://api.openrouteservice.org/v2/directions/driving-car";

    public String getRoute(String start, String end) {
        // Build the OpenRouteService API URL
        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + apiKey +
                "&start=" + start + "&end=" + end;

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Log the response for debugging
        logger.info("API Response: " + response.getBody());

        return response.getBody();
    }

}
