package com.pmf.pris.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.service.UmetnickoDeloService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("umetnickoDelo")
public class UmetnickoDeloController {
    
    @Autowired
    UmetnickoDeloService uds;
    
    @GetMapping("delaUTuri")
    public String delaUTuri(HttpServletRequest request, @RequestParam("idTure") int idTure) {
        request.setAttribute("delaUTuri", uds.getDelaUTuri(idTure));
        return "pregledPredmeta";
    }
    
    @GetMapping("svaDela")
    public String svaDela(HttpServletRequest request) {
        request.setAttribute("svaDela", uds.getDela());
        return "home";
    }
    
	@PostMapping("kreirajUmetnickoDelo")
	public String kreirajTuru(HttpServletRequest request, @RequestParam("naziv") String naziv, 
			@RequestParam("opis") String opis, @RequestParam("datum") Date datum,
			@RequestParam("geografskaDuzina") double geografskaDuzina, 	@RequestParam("geografskaSirina") double geografskaSirina) {
		
		
		// Promeniti 1 u request.getAttribute(idKorisnika) kada security bude implementiran
		if(uds.kreirajUmetnickoDelo(naziv, opis, datum, geografskaDuzina, geografskaSirina, 1) == false) {
			request.setAttribute("uspelo", "Nije kreirano umetnicko delo");
			System.out.println();
			return "umetnickoDeloNijeSacuvano";
		}
		request.setAttribute("uspelo", "Kreirano umetnicko delo");
		return "prikaziSacuvanoUmetnickoDelo";
	}
}

