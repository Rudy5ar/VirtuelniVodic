package com.pmf.pris.controller;

import com.pmf.pris.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }
    String maliIdjos = "19.6644,45.7069";
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

}
