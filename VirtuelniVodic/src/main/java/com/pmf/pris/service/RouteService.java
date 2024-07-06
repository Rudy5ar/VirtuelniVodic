package com.pmf.pris.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    private String apiKey = "5b3ce3597851110001cf62487ffb754b71cc46e79354c1777b9efdd1";
    private final String baseUrl = "https://api.openrouteservice.org/v2/directions/driving-car";

    public String getRoute(String coordinates){
        String url = "https://api.openrouteservice.org/v2/directions/driving-car";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", apiKey);
        System.out.println(coordinates);
        HttpEntity<String> request = new HttpEntity<String>(coordinates, headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

}
