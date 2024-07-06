package com.pmf.pris.controller;

import com.pmf.pris.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/route")
    public String getRoute(Model model) {
        // Define the coordinates here (example coordinates)
        String lovcenac = "19.6917,45.6819";
        String vrbas = "19.6489,45.5681";


        // Delegate the API call to the service
        String routeData = routeService.getRoute(lovcenac, vrbas);

        // Add the route data to the model
        model.addAttribute("routeData", routeData);

        return "ture/route"; // Assuming "ture/route.jsp" or "ture/route.html" exists
    }

    @GetMapping("/routeMultiple")
    public String getRouteMultiple(Model model) {
        // Define the coordinates for each place
        String lovcenac = "19.6644,45.7069";
        String vrbas = "19.6489,45.5681";
        String topola = "19.6301,45.8129";
        String maliIdjos = "19.6644,45.7069";

        // Create a list to hold the coordinates
        List<String> places = Arrays.asList(lovcenac, vrbas, topola);

        // Build the coordinates in the required format for the API
        StringBuilder coordinatesJson = new StringBuilder();
        coordinatesJson.append("{\"coordinates\":[");

        for (int i = 0; i < places.size(); i++) {
            String[] coord = places.get(i).split(",");
            coordinatesJson.append("[").append(coord[0]).append(",").append(coord[1]).append("]");

            if (i != places.size() - 1) {
                coordinatesJson.append(",");
            }
        }
        coordinatesJson.append("]}");
        String route = routeService.getRouteMultiple(coordinatesJson.toString());
        model.addAttribute("routeData", route);
        // Normally, you would send this JSON to the API in a POST request, but for now, just return it
        // For the purpose of this example, we assume you will use this JSON string to send to the API later
        return "ture/routeMultiple";
    }


}
