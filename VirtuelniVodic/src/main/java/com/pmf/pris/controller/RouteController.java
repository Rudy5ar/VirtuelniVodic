package com.pmf.pris.controller;

import com.pmf.pris.service.RouteService;
import com.pmf.pris.service.TuraService;
import model.Tura;
import model.Umetnickodelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RouteController {

    private final RouteService routeService;
    private final TuraService turaService;

    @Autowired
    public RouteController(RouteService routeService, TuraService turaService) {
        this.routeService = routeService;
        this.turaService = turaService;
    }

    @GetMapping("/route")
    public String getRoute(Model model, @RequestParam int turaId) {
        Tura tura = turaService.getById(turaId);
        List<Umetnickodelo> dela = tura.getUmetnickodelos();
        List<String> places = new ArrayList<>();

        for(Umetnickodelo u : dela){
            places.add(u.getGeografskaDuzina() + "," + u.getGeografskaSirina());
        }

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
        String route = routeService.getRoute(coordinatesJson.toString());
        model.addAttribute("routeData", route);

        return "ture/routeMultiple";
    }
}
