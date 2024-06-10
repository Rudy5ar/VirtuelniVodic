package com.pmf.pris.controller;

import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.service.TuraService;
import com.pmf.pris.service.UmetnickoDeloService;

import jakarta.servlet.http.HttpServletRequest;
import model.Umetnickodelo;
import jakarta.transaction.Transactional;
import model.Tura;

@Controller
@RequestMapping("umetnickoDelo")
public class UmetnickoDeloController {
	
	private final UmetnickoDeloService umetnickoDeloService;

    public UmetnickoDeloController(UmetnickoDeloService umetnickoDeloService) {
        this.umetnickoDeloService = umetnickoDeloService;
    }

    @GetMapping("svaDela")
	public String svaDela(HttpServletRequest request) {
		request.setAttribute("svaDela", umetnickoDeloService.getDela());
		return "home";

    @GetMapping("delaUTuri")
    public String delaUTuri(HttpServletRequest request, @RequestParam("idTure") int idTure) {
        request.setAttribute("delaUTuri", uds.getDelaUTuri(idTure));
        return "pregledPredmeta";
    }

	@PostMapping("kreirajUmetnickoDelo")
	//treba proveriti da li sam uredjivac?
	public String kreirajTuru(HttpServletRequest request, @RequestParam("naziv") String naziv,
			@RequestParam("opis") String opis, @RequestParam("datum") Date datum,
			@RequestParam("geografskaDuzina") double geografskaDuzina, 	@RequestParam("geografskaSirina") double geografskaSirina,
			@RequestParam("umetnikId") int umetnikId, @RequestParam("opstost1") String opstost1,
			@RequestParam("opstost2") String opstost2, @RequestParam("opstost3") String opstost3) {

		if(uds.kreirajUmetnickoDelo(naziv, opis, datum, geografskaDuzina, geografskaSirina, umetnikId, opstost1, opstost2, opstost3) == false) {
			request.setAttribute("uspelo", "Nije kreirano umetnicko delo");
			System.out.println();
			return "umetnickoDeloNijeSacuvano"; // Naziv JSP stranice za grešku
		}
		request.setAttribute("uspelo", "Kreirano umetnicko delo");
		return "prikaziSacuvanoUmetnickoDelo"; // Naziv JSP stranice za uspeh
	}
	
	@PostMapping("izmeniOpisUmetnickogDela")
    public String izmeniOpis(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo, @RequestParam("noviOpis") String noviOpis) {
		//treba proveriti da li sam uredjivac?
        if (uds.izmeniOpisUmetnickogDela(idUmetnickoDelo, noviOpis)) {
            request.setAttribute("poruka", "Opis je uspesno azuriran");
            return "uspesnoAzuriranOpis";  // Naziv JSP stranice za uspeh
        } else {
            request.setAttribute("poruka", "Greska pri azuriranju opisa");
            return "greskaPriAzuriranju";  // Naziv JSP stranice za grešku
        }
    }

	@PostMapping("izmeniAutoraUmetnickogDela")
	//treba proveriti da li sam uredjivac?
    public String izmeniAutora(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo, @RequestParam("noviUmetnikId") int noviUmetnikId) {
        if (uds.izmeniAutoraUmetnickogDela(idUmetnickoDelo, noviUmetnikId)) {
            request.setAttribute("poruka", "Autor je uspesno azuriran");
            return "uspesnoAzuriranAutor";  // Naziv JSP stranice za uspeh
        } else {
            request.setAttribute("poruka", "Greska pri azuriranju autora");
            return "greskaPriAzuriranju";  // Naziv JSP stranice za grešku
        }
    }

    @PostMapping("izmeniGodinuNastankaUmetnickogDela")
	//treba proveriti da li sam uredjivac?
    public String izmeniGodinuNastanka(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo, @RequestParam("novaGodinaNastanka") Date novaGodinaNastanka) {
        if (uds.izmeniGodinuNastankaUmetnickogDela(idUmetnickoDelo, novaGodinaNastanka)) {
            request.setAttribute("poruka", "Godina nastanka je uspešno ažurirana");
            return "uspesnoAzuriranaGodinaNastanka";  // Naziv JSP stranice za uspeh
        } else {
            request.setAttribute("poruka", "Greška pri ažuriranju godine nastanka");
            return "neuspesnoAzuriranaGodineNastanka";  // Naziv JSP stranice za grešku
        }
    }

    @GetMapping("prikazUmetnickogDela")
    public String detaljiUmetnickogDela(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo) {
        Umetnickodelo umetnickoDelo = uds.getDetaljiUmetnickogDela(idUmetnickoDelo);
        if (umetnickoDelo == null) {
            request.setAttribute("poruka", "Umetničko delo nije pronađeno");
            return "greska"; // JSP stranica za grešku
        }
        request.setAttribute("umetnickoDelo", umetnickoDelo);
        return "prikaziDetaljeZaUmetnickoDelo"; // JSP stranica za prikaz detalja
    }

}

