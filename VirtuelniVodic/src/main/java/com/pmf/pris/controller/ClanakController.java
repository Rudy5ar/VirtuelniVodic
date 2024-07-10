package com.pmf.pris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.service.ClanakService;

import jakarta.servlet.http.HttpServletRequest;
import model.Clanak;

@Controller
@RequestMapping("clanak")
public class ClanakController {

    @Autowired
    private ClanakService clanakService;

    @PostMapping("kreirajClanak")
    public String kreirajClanak(HttpServletRequest request, @RequestParam("naziv") String naziv, @RequestParam("tekst") String tekst, Model model) {
        Clanak createdClanak = clanakService.kreirajClanak(naziv, tekst, 1);
        if (createdClanak == null) {
            request.setAttribute("uspeo", "Nije kreirana tura");
            return "clanak/clanakNijeSacuvan";
        }
        model.addAttribute("clanak", createdClanak);
        return "clanak/prikaziSacuvanClanak";
    }

    @GetMapping("prikaziClanke")
    public String prikaziClanke(HttpServletRequest request) {
        request.setAttribute("sviClanci", clanakService.sviClanci());
        return "clanak/prikaziClanke";
    }

    @GetMapping("detaljiClanka")
    public String detaljiClanka(HttpServletRequest request, @RequestParam("idClanka") int idClanka) {
        request.setAttribute("clanak", clanakService.detaljiClanka(idClanka));
        return "clanak/prikazClanka";
    }
}
