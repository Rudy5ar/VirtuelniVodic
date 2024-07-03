package com.pmf.pris.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmf.pris.maps.OpenRouteService;
import com.pmf.pris.model.dto.UmetnickoDeloDTO;
import com.pmf.pris.repository.UmetnickoDeloRepository;
import model.Umetnickodelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pmf.pris.service.TuraService;

import jakarta.servlet.http.HttpServletRequest;
import model.Tura;

@RestController
@RequestMapping("tura")
public class TuraController {
	
	@Autowired
	TuraService ts;

	@Autowired
	OpenRouteService openRouteService;

	@Autowired
	UmetnickoDeloRepository umetnickoDeloRepository;

	@PostMapping("kreirajTuru")
	public String kreirajTuru(HttpServletRequest request, @RequestParam("naziv") String naziv, @RequestParam("opis") String opis) {
		
		// Promeniti 1 u request.getAttribute(idKorisnika) kada security bude implementiran
		if(!ts.kreirajTuru(naziv, opis, 1)) {
			request.setAttribute("uspelo", "Nije kreirana tura");
			System.out.println();
			return "ture/turaNijeSacuvana";
		}
		request.setAttribute("uspelo", "Kreirana tura");
		return "ture/prikaziSacuvanuTuru";
	}
	
	@PostMapping("promeniTuru")
	public String promeniTuru(HttpServletRequest request, @RequestParam("idTure") int idTure, @RequestParam("naziv") String naziv, @RequestParam("opis") String opis) {
		if(!ts.promeniTuru(idTure, naziv, opis)) {
			request.setAttribute("uspelo", "Nije promenjena tura");
			return "ture/turaNijePromenjena";
		}
		request.setAttribute("uspelo", "Kreirana tura");
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
    public double razdaljinaDvaDela(
            @RequestParam Umetnickodelo prviLat,
            @RequestParam Umetnickodelo prviLong,
            @RequestParam String drugiLat,
            @RequestParam String drugiLong,
			@RequestParam String mode,
            HttpServletRequest request) {
        double distance = openRouteService.getDistance(prviLat, prviLong);
        request.setAttribute("distance", distance);
        return distance;
    }

	@GetMapping("napraviRutu")
	public List<UmetnickoDeloDTO> napraviRutu() {
		try {
			UmetnickoDeloDTO pom = new UmetnickoDeloDTO(null, null, 0, 0);
			UmetnickoDeloDTO dela1 = pom.toDto(umetnickoDeloRepository.findById(7).get());
			UmetnickoDeloDTO dela2 = pom.toDto(umetnickoDeloRepository.findById(8).get());
			UmetnickoDeloDTO dela3 = pom.toDto(umetnickoDeloRepository.findById(9).get());
			UmetnickoDeloDTO dela4 = pom.toDto(umetnickoDeloRepository.findById(6).get());
			List<UmetnickoDeloDTO> dela = List.of(dela1, dela2, dela3, dela4);
			String response = openRouteService.getOptimizedRoute(dela);
			return parseAndReorderDelos(response, dela);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get optimized route", e);
		}
	}

	private List<UmetnickoDeloDTO> parseAndReorderDelos(String response, List<UmetnickoDeloDTO> delos) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(response);

		List<UmetnickoDeloDTO> orderedDelos = new ArrayList<>();

		// Process each feature node if present and has correct structure
		rootNode.path("features").forEach(featureNode -> {
			JsonNode geometryNode = featureNode.path("geometry");
			if (geometryNode.isMissingNode()) {
				return; // Skip if 'geometry' node is missing
			}

			JsonNode coordinatesNode = geometryNode.path("coordinates");
			if (!coordinatesNode.isArray() || coordinatesNode.size() < 2) {
				return; // Skip if 'coordinates' array is missing or incomplete
			}

			double longitude = coordinatesNode.get(0).asDouble();
			double latitude = coordinatesNode.get(1).asDouble();

			// Find corresponding UmetnickoDeloDTO based on coordinates
			delos.stream()
					.filter(d -> d.getLatitude() == latitude && d.getLongitude() == longitude)
					.findFirst()
					.ifPresent(orderedDelos::add);
		});

		return orderedDelos;
	}

}