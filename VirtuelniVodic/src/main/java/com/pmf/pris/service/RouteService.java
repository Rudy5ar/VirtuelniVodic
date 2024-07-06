package com.pmf.pris.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Tura;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Service
public class RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);
    private final TuraService turaService;

    private String apiKey = "5b3ce3597851110001cf62487ffb754b71cc46e79354c1777b9efdd1";
    private final String baseUrl = "https://api.openrouteservice.org/v2/directions/driving-car";

    public RouteService(TuraService turaService) {
        this.turaService = turaService;
    }

    public double getDistance(String prviLat, String prviLong, String drugiLat, String drugiLong) {
        int R = 6371; // Radius of the earth in kilometers

        double latDistance = Math.toRadians(Double.parseDouble(drugiLat) - Double.parseDouble(prviLat));
        double lonDistance = Math.toRadians(Double.parseDouble(drugiLong) - Double.parseDouble(prviLong));
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(Double.parseDouble(prviLat))) * Math.cos(Math.toRadians(Double.parseDouble(drugiLat)))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }


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
