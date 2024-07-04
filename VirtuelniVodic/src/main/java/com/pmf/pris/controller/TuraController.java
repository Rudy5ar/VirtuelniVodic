package com.pmf.pris.controller;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.maps.OpenRouteService;
import com.pmf.pris.service.TuraService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import model.Tura;
import model.Umetnickodelo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("tura")
public class TuraController {
	
	@Autowired
	TuraService ts;

	@Autowired
	OpenRouteService openRouteService;
	
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
	public String prikaziJavne(Model model) {
		List<Tura> javne = ts.getJavne();
		model.addAttribute("listaJavnih", javne);
		
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
    
    @SneakyThrows
    @GetMapping("/pdf")
    public void getTuraPdf(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idTura) {
    	JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("reports/reportTure.jrxml").getInputStream());
    	
    	Tura tura = ts.getById(idTura);
    	
    	Map<String, Object> param = new HashMap<>();
    	param.put("nazivTure", tura.getNaziv());
    	param.put("datum", new Date());
    	
    	JRDataSource dataSource = new JRBeanCollectionDataSource(tura.getUmetnickodelos());
    	
    	JasperPrint print = JasperFillManager.fillReport(report, param, dataSource);
    	
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=tura.pdf");
    	
    	OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(print, out);
    }

}