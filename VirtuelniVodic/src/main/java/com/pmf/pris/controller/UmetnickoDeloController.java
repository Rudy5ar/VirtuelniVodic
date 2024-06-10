package com.pmf.pris.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.service.TuraService;
import com.pmf.pris.service.UmetnickoDeloService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import model.Tura;

@Controller
@RequestMapping("umetnickoDelo")
public class UmetnickoDeloController {
	
	@Autowired
	UmetnickoDeloService uds;
	
	@Autowired
	TuraService ts;
	
	@GetMapping("delaUTuri")
	public String delaUTuri(HttpServletRequest request, @RequestParam("idTure") int idTure) {
		Tura t = ts.getById(idTure);
		request.setAttribute("tura", t);
		request.setAttribute("delaUTuri", uds.getDelaUTuri(idTure));
		return "pregledTure";
	}
	
	@PostMapping("opstost")
	public String getOpstost(HttpServletRequest request, @RequestParam("opstost") String opstost) {
		request.setAttribute("opstost", opstost);
		return "pregledPredmeta";
	}
	
	@GetMapping("svaDela")
	public String svaDela(HttpServletRequest request) {
		request.setAttribute("svaDela", uds.getDela());
		return "home";
	}
	
	@PostMapping("dodajUmetnickoDelo")
    public String dodajUmetnickoDelo(HttpServletRequest request,@RequestParam("id") int id, @RequestParam("naziv") String naziv, @RequestParam("opis") String opis) {
        if(uds.dodajUmetnickoDelo(naziv, opis)) {
            request.setAttribute("uspelo", "Umetničko delo dodato");
            return "pregledPredmeta";
        } else {
            request.setAttribute("uspelo", "Nije dodato umetničko delo");
            return "greska";
        }
    }
    
    @PostMapping("promeniOpisUmetnickogDela")
    public String promeniOpisUmetnickogDela(HttpServletRequest request, @RequestParam("idDela") int idDela, @RequestParam("noviOpis") String noviOpis) {
        if(uds.promeniOpisUmetnickogDela(idDela, noviOpis)) {
            request.setAttribute("uspelo", "Opis umetničkog dela promenjen");
            return "pregledPredmeta";
        } else {
            request.setAttribute("uspelo", "Nije promenjen opis umetničkog dela");
            return "greska";
        }
    }
    
    @PostMapping("updateOpisi")
    @Transactional
    public String updateOpisi(HttpServletRequest request, @RequestParam Map<String, String> allParams) {
        allParams.forEach((key, value) -> {
            if (key.startsWith("opis_")) {
                int id = Integer.parseInt(key.substring(5));
                uds.promeniOpisUmetnickogDela(id, value);
            }
        });
        return "redirect:/umetnickoDelo/delaUTuri?idTure=" + request.getParameter("idTure");
    }
 
	
}
