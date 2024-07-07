package com.pmf.pris.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.maps.OpenRouteService;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.service.TuraService;
import com.pmf.pris.service.UmetnickoDeloService;

import jakarta.servlet.http.HttpServletRequest;
import model.Tura;
import model.Umetnickodelo;

@Controller
@RequestMapping("tura")
public class TuraController {
	
	@Autowired
	TuraService ts;

	@Autowired
	OpenRouteService openRouteService;
	
	@Autowired
	UmetnickoDeloService  umetnickoDeloService;
	
	@Autowired
	TuraRepository tr;
	
	@GetMapping("/kreirajTuru")
    public String showCreateTuraForm(Model model) {
        model.addAttribute("umetnickaDela", umetnickoDeloService.getDela());
        return "kreirajTuru";
    }

    @PostMapping("/kreirajTuru")
    public String createTura(@RequestParam String naziv, @RequestParam String opis, 
                             @RequestParam List<Integer> umetnickaDela, @RequestParam String tip, Model model) {
    	// Promeniti 1 u request.getAttribute(idKorisnika) kada security bude implementiran
        ts.kreirajTuru(naziv, opis, tip, 1, umetnickaDela);
        return "redirect:/home.jsp";
    }
	
//	@PostMapping("kreirajTuru")
//    public String kreirajTuru(HttpServletRequest request, @RequestParam("naziv") String naziv, @RequestParam("opis") String opis, @RequestParam("tip") String tip, @RequestParam("umetnickaDela") List<Integer> umetnickaDelaIds) {
//        Tura t = ts.kreirajTuru(naziv, opis, tip, 1, umetnickaDelaIds);
//        if (t == null) {
//            request.setAttribute("uspelo", "Nije kreirana tura");
//            return "ture/turaNijeSacuvana";
//        }
//        List<Umetnickodelo> umetnickaDela = t.getUmetnickodelos();
//        request.setAttribute("tura", t);
//        request.setAttribute("umetnickaDela", umetnickaDela);
//        request.setAttribute("uspelo", "Kreirana tura");
//        return "ture/prikaziSacuvanuTuru";
//    }
	
//	@PostMapping("kreirajTuru")
//	public String kreirajTuru(HttpServletRequest request, @RequestParam("naziv") String naziv, @RequestParam("opis") String opis, @RequestParam("tip") String tip, @RequestParam("umetnickaDela") List<Integer> umetnickaDelaIds) {
//		// Promeniti 1 u request.getAttribute(idKorisnika) kada security bude implementiran
//		Tura t = ts.kreirajTuru(naziv, opis, tip, 1, umetnickaDelaIds);
//		if(t == null) {
//			request.setAttribute("uspelo", "Nije kreirana tura");
//			System.out.println();
//			return "ture/turaNijeSacuvana";
//		}
//		List<Umetnickodelo> umetnickaDela = t.getUmetnickodelos();
//		request.setAttribute("tura", t);
//		request.setAttribute("umetnickaDela", umetnickaDela);
//		request.setAttribute("uspelo", "Kreirana tura");
//		return "ture/prikaziSacuvanuTuru";
//	}
	
	@GetMapping("getUmetnickaDela")
    public String getUmetnickaDela(HttpServletRequest request) {
    	request.setAttribute("umetnickaDela", umetnickoDeloService.getDela());
    	return "kreiranjeTure";
    }
	
	@GetMapping("proslediTuru/{idTura}")
	public String urediTuru(@PathVariable("idTura") Integer idTura, Model model) {
		Tura tura = ts.findById(idTura);
	    if (tura != null) {
	        model.addAttribute("tura", tura);
	        model.addAttribute("umetnickaDela", umetnickoDeloService.getDela()); // Assuming this fetches the correct list
	        return "ture/urediTuru"; // Return your JSP name without .jsp extension
	    } else {
	        // Handle case where tura with given idTura is not found
	        return "error"; // Create a dedicated error page or handle as per your application flow
	    }
	}
	
	@PostMapping("promeniTuru/{idTura}")
	public String promeniTuru(HttpServletRequest request, 
							  @PathVariable("idTura") int idTure, 
							  @RequestParam("naziv") String naziv, 
							  @RequestParam("opis") String opis,
							  @RequestParam("umetnickaDela") List<Integer> delaIds, 
							  @RequestParam("tip") String tip) {
		Tura tura = ts.findById(idTure);
		tura.setNaziv(naziv);
		tura.setOpis(opis);
		tura.setTip(tip);
		
		List<Umetnickodelo> dela = delaIds.stream()
										  .map(deloId -> umetnickoDeloService.findById(deloId))
										  .collect(Collectors.toList());
		tura.setUmetnickodelos(dela);
		tr.save(tura);
		request.setAttribute("tura", tura);
		request.setAttribute("umetnickaDela", dela);
		return "ture/prikaziPromenjenuTuru";
	}
	
	//nesto sam mijenjala
	@PostMapping("objaviTuru")
	public String objaviTuru(HttpServletRequest request, @RequestParam("idTure") int idTure) {
		if(!ts.objaviTuru(idTure)) {
			request.setAttribute("uspelo", "Nije promenjena tura");
			return "ture/turaNijePromenjena";
		}
		request.setAttribute("uspelo", "Kreirana tura");
		return "ture/prikaziPromenjenuTuru";
	}
	
	// ovde zapravo treba prikazati privatne ture od trenutno ulogovanog korisnika
	@GetMapping("prikaziPrivatne")
	public String prikaziPrivatne(HttpServletRequest request) {
		List<Tura> privatne = ts.getPrivatne();
		request.setAttribute("listaPrivatnih", privatne);
		
		return "privatneTure";
	}

	@GetMapping("prikaziJavne")
	public String prikaziJavne(HttpServletRequest request) {
		List<Tura> javne = ts.getJavne();
		request.setAttribute("listaJavnih", javne);
		
		return "javneTure";
	}
	
	@GetMapping("prikaziDetaljeTure")
	public String prikaziDetaljeTure(HttpServletRequest request, @RequestParam("idTure") int idTure) {
		// Kada bude bio security, dodati informaciju o korisniku
		// int idKorisnika = (int) request.getSession().getAttribute("idKorisnika");
		
		int idKorisnika = 1;
		Tura detalji = ts.prikaziDetaljeTure(idTure, idKorisnika);
		request.setAttribute("detalji", detalji);


        return "ture/prikaziPromenjenuTuru";
    }

    @GetMapping("sortirajPoDatumu")
    public String sortirajPoDatumu(HttpServletRequest request, @RequestParam("tura") Tura tura) {
        request.setAttribute("sortiranaPoDatumu", ts.sortirajPoDatumu(tura));
        return "ture/sortirajPoDatumu";
    }

    @GetMapping("sortirajPoRazdaljini")
    public String sortirajPoRazdaljini(HttpServletRequest request, @RequestParam("idTure") int idTure) {

        Tura tura = ts.sortirajPoRazdaljini(idTure);
        request.setAttribute("sortiranaPoRazdaljini", tura);
        for (Umetnickodelo u : tura.getUmetnickodelos()) {
            System.out.println(u);
        }
        return "ture/sortirajPoRazdaljini";
    }

    @GetMapping("razdaljinaDvaDela")
    public int getDistance(
            @RequestParam String prviLat,
            @RequestParam String prviLong,
            @RequestParam String drugiLat,
            @RequestParam String drugiLong,
            HttpServletRequest request) {
        int distance = openRouteService.getDistance(prviLat, prviLong, drugiLat, drugiLong);
        request.setAttribute("distance", distance);
        return distance;
    }

}