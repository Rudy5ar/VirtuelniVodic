package com.pmf.pris.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.repository.EpohaRepository;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.UmetnikRepository;
import com.pmf.pris.service.EpohaService;
import com.pmf.pris.service.UmetnickoDeloService;
import com.pmf.pris.service.UmetnikService;

import jakarta.servlet.http.HttpServletRequest;
import model.Epoha;
import model.Umetnickodelo;
import model.Umetnik;

@Controller
@RequestMapping("umetnickoDelo")
public class UmetnickoDeloController {

    private final UmetnickoDeloService umetnickoDeloService;
    private final EpohaRepository epohaRepository;
    private final UmetnikRepository umetnikRepository;
    private final UmetnikService umetnikService;
    private final EpohaService epohaService;
    private final TuraRepository turaRepository;
    
    public UmetnickoDeloController(UmetnickoDeloService umetnickoDeloService, EpohaRepository epohaRepository, UmetnikRepository umetnikRepository, UmetnikService umetnikService, EpohaService epohaService, TuraRepository turaRepository) {
        this.umetnickoDeloService = umetnickoDeloService;
        this.epohaRepository = epohaRepository;
        this.umetnikRepository = umetnikRepository;
        this.umetnikService = umetnikService;
        this.epohaService = epohaService;
        this.turaRepository = turaRepository;
    }

    @GetMapping("svaDela")
    public String svaDela(HttpServletRequest request) {
        request.setAttribute("svaDela", umetnickoDeloService.getDela());
        return "umetnickoDelo/prikazUmetnickihDela";
    }

    @GetMapping("delaUTuri")
    public String delaUTuri(HttpServletRequest request, @RequestParam("idTure") int idTure) {
        request.setAttribute("delaUTuri", umetnickoDeloService.getDelaUTuri(idTure));
        request.setAttribute("tura", turaRepository.findById(idTure).get());
        return "pregledTure";
    }

    @PostMapping("kreirajUmetnickoDelo")
    public String kreirajTuru(HttpServletRequest request, @RequestParam("naziv") String naziv,
                              @RequestParam("opis") String opis, @RequestParam("datum") Date datum,
                              @RequestParam("geografskaDuzina") double geografskaDuzina, @RequestParam("geografskaSirina") double geografskaSirina,
                              @RequestParam("umetnikId") int umetnikId, @RequestParam("opstost1") String opstost1,
                              @RequestParam("opstost2") String opstost2, @RequestParam("opstost3") String opstost3) {

        if (!umetnickoDeloService.kreirajUmetnickoDelo(naziv, opis, datum, geografskaDuzina, geografskaSirina, umetnikId, opstost1, opstost2, opstost3)) {
            request.setAttribute("uspelo", "Nije kreirano umetnicko delo");
            return "umetnickoDeloNijeSacuvano";
        }
        request.setAttribute("uspelo", "Kreirano umetnicko delo");
        return "prikaziSacuvanoUmetnickoDelo";
    }

    @PostMapping("izmeniOpisUmetnickogDela")
    public String izmeniOpis(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo, @RequestParam("noviOpis") String noviOpis) {
        if (umetnickoDeloService.izmeniOpisUmetnickogDela(idUmetnickoDelo, noviOpis)) {
            request.setAttribute("poruka", "Opis je uspesno azuriran");
            return "uspesnoAzuriranOpis";
        } else {
            request.setAttribute("poruka", "Greska pri azuriranju opisa");
            return "greskaPriAzuriranju";
        }
    }

    @PostMapping("izmeniAutoraUmetnickogDela")
    public String izmeniAutora(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo, @RequestParam("noviUmetnikId") int noviUmetnikId) {
        if (umetnickoDeloService.izmeniAutoraUmetnickogDela(idUmetnickoDelo, noviUmetnikId)) {
            request.setAttribute("poruka", "Autor je uspesno azuriran");
            return "uspesnoAzuriranAutor";
        } else {
            request.setAttribute("poruka", "Greska pri azuriranju autora");
            return "greskaPriAzuriranju";
        }
    }

    @PostMapping("izmeniGodinuNastankaUmetnickogDela")
    public String izmeniGodinuNastanka(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo, @RequestParam("novaGodinaNastanka") Date novaGodinaNastanka) {
        if (umetnickoDeloService.izmeniGodinuNastankaUmetnickogDela(idUmetnickoDelo, novaGodinaNastanka)) {
            request.setAttribute("poruka", "Godina nastanka je uspešno ažurirana");
            return "uspesnoAzuriranaGodinaNastanka";
        } else {
            request.setAttribute("poruka", "Greška pri ažuriranju godine nastanka");
            return "neuspesnoAzuriranaGodineNastanka";
        }
    }

    @GetMapping("prikazUmetnickogDela")
    public String detaljiUmetnickogDela(HttpServletRequest request, @RequestParam("idUmetnickoDelo") int idUmetnickoDelo) {
        Umetnickodelo umetnickoDelo = umetnickoDeloService.getDetaljiUmetnickogDela(idUmetnickoDelo);
        if (umetnickoDelo == null) {
            request.setAttribute("poruka", "Umetničko delo nije pronađeno");
            return "greska";
        }
        request.setAttribute("umetnickoDelo", umetnickoDelo);
        return "prikaziDetaljeZaUmetnickoDelo";
    }
    
    @GetMapping("/edit/{id}")
    public String editUmetnickoDelo(@PathVariable("id") int id, Model model) {
        Umetnickodelo umetnickoDelo = umetnickoDeloService.findById(id);
        List<Umetnik> allUmetnici = umetnikRepository.findAll();
        List<Epoha> allEpohe = epohaRepository.findAll();

        model.addAttribute("umetnickoDelo", umetnickoDelo);
        model.addAttribute("allUmetnici", allUmetnici);
        model.addAttribute("allEpohe", allEpohe);

        return "umetnickoDelo/urediUmetnickoDelo";
    }

    @PostMapping("/edit/{id}")
    public String saveUmetnickoDelo(@PathVariable("id") int id,
                                    @RequestParam("naziv") String naziv,
                                    @RequestParam("opis") String opis,
                                    @RequestParam("datum") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datum,
                                    @RequestParam("geografskaDuzina") double geografskaDuzina,
                                    @RequestParam("geografskaSirina") double geografskaSirina,
                                    @RequestParam("umetnikId") int umetnikId,
                                    @RequestParam("epoheIds") List<Integer> epoheIds) {

        Umetnickodelo umetnickoDelo = umetnickoDeloService.findById(id);
        umetnickoDelo.setNaziv(naziv);
        umetnickoDelo.setOpis(opis);
        umetnickoDelo.setDatum(datum);
        umetnickoDelo.setGeografskaDuzina(geografskaDuzina);
        umetnickoDelo.setGeografskaSirina(geografskaSirina);

        Umetnik umetnik = umetnikService.findById(umetnikId);
        umetnickoDelo.setUmetnik(umetnik);

        List<Epoha> epohe = epoheIds.stream()
                                    .map(epohaId -> epohaService.findById(epohaId))
                                    .collect(Collectors.toList());
        umetnickoDelo.setEpohas(epohe);

        umetnickoDeloService.save(umetnickoDelo);
        return "redirect:/umetnickoDelo/svaDela";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
