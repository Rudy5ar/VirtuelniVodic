package com.pmf.pris.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.model.dto.TuraDTO;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.UmetnickoDeloRepository;
import com.pmf.pris.service.TuraService;
import com.pmf.pris.service.UmetnickoDeloService;

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
	UmetnickoDeloRepository umetnickoDeloRepository;

	@Autowired
	UmetnickoDeloService  umetnickoDeloService;

	@Autowired
	TuraRepository tr;

	@GetMapping("/kreirajTuru")
    public String showCreateTuraForm(Model model) {
        model.addAttribute("umetnickaDela", umetnickoDeloService.getDela());
        model.addAttribute("tura", new TuraDTO());
        return "kreirajTuru";
    }

    @PostMapping("/kreirajTuru")
    public String createTura(@ModelAttribute TuraDTO tura, HttpServletRequest request) {
    	// Promeniti 1 u request.getAttribute(idKorisnika) kada security bude implementiran
        Tura t = ts.save(tura);
        request.setAttribute("tura", t);
        request.setAttribute("umetnickaDela", t.getUmetnickodelos());
        return "ture/prikaziSacuvanuTuru";
    }

	@GetMapping("getUmetnickaDela")
    public String getUmetnickaDela(HttpServletRequest request) {
    	request.setAttribute("umetnickaDela", umetnickoDeloService.getDela());
    	request.setAttribute("tura", new TuraDTO());
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
		if(privatne == null){
			request.setAttribute("listaPrivatnih", new ArrayList<>());
			return "privatneTure";
		}
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
        return "ture/sortirajPoRazdaljini";
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

	@GetMapping("/kopiranje")
	public String kopiranje(@RequestParam Integer idTura, Model model) {
		Tura tura = ts.getById(idTura);
		TuraDTO dto = new TuraDTO();
		dto.setNaziv(tura.getNaziv());
		dto.setOpis(tura.getOpis());
		dto.setTip("privatna");
		dto.setUmetnickodelos(tura.getUmetnickodelos());
		
		model.addAttribute("umetnickaDela", umetnickoDeloService.getDela());
        model.addAttribute("tura", dto);
        return "kreiranjeTure";
	}
}