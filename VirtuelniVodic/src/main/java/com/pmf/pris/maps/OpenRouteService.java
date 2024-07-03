package com.pmf.pris.maps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pmf.pris.model.dto.UmetnickoDeloDTO;
import model.Umetnickodelo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenRouteService {

    private final String API_KEY = "5b3ce3597851110001cf62487ffb754b71cc46e79354c1777b9efdd1";
    private final String BASE_URL = "https://api.openrouteservice.org/v2/directions/driving-car";
    private final String DISTANCE_URL = "https://api.openrouteservice.org/v2/matrix/driving-car";

    private final RestTemplate restTemplate;

    public OpenRouteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getOptimizedRoute(List<UmetnickoDeloDTO> delos) {
        String jsonPayload = buildJsonPayload(delos);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, entity, String.class);
        return response.getBody();
    }

    private String buildJsonPayload(List<UmetnickoDeloDTO> delos) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        ArrayNode coordinatesArray = objectMapper.createArrayNode();

        for (UmetnickoDeloDTO delo : delos) {
            ArrayNode point = objectMapper.createArrayNode();
            point.add(delo.getLongitude());
            point.add(delo.getLatitude());
            coordinatesArray.add(point);
        }

        requestBody.set("coordinates", coordinatesArray);

        try {
            return objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            throw new RuntimeException("Failed to build JSON payload", e);
        }
    }

    public double getDistance(Umetnickodelo delo1, Umetnickodelo delo2) {
        String jsonPayload = buildDistancePayload(delo1, delo2);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(DISTANCE_URL, entity, String.class);
        return parseDistanceResponse(response.getBody());
    }

    private String buildDistancePayload(Umetnickodelo delo1, Umetnickodelo delo2) {
        return String.format(
                "{\"locations\": [[%f, %f], [%f, %f]], \"metrics\": [\"distance\"]}",
                delo1.getGeografskaDuzina(), delo1.getGeografskaSirina(),
                delo2.getGeografskaDuzina(), delo2.getGeografskaSirina()
        );
    }

    private double parseDistanceResponse(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode distancesNode = rootNode.path("distances");
            return distancesNode.get(0).get(1).asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse distance response", e);
        }
    }
}
