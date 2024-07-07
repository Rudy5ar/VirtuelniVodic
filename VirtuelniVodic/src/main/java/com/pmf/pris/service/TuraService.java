package com.pmf.pris.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import model.Korisnik;
import model.Umetnickodelo;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.KorisnikRepository;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.UmetnickoDeloRepository;

import model.Tura;

@Service
public class TuraService {

	private final TuraRepository tr;
	private final KorisnikRepository kr;
	private final RouteService routeService;
	private final UmetnickoDeloRepository udr;
	private final KorisnikService korisnikService;

	public TuraService(TuraRepository tr, KorisnikRepository kr, RouteService routeService, UmetnickoDeloRepository udr, KorisnikService korisnikService) {
        this.tr = tr;
        this.kr = kr;
        this.routeService = routeService;
        this.udr = udr;
		this.korisnikService = korisnikService;
	}

    public Tura kreirajTuru(String naziv, String opis, String tip, List<Integer> umetnickaDelaIds) {
        Tura novaTura = new Tura();
        novaTura.setKorisnik(korisnikService.getCurrentUser());
        novaTura.setNaziv(naziv);
        novaTura.setOpis(opis);
        novaTura.setTip(tip);

        List<Umetnickodelo> umetnickaDela = udr.findAllById(umetnickaDelaIds);
        for (Umetnickodelo delo : umetnickaDela) {
            novaTura.addUmetnickodelo(delo);
        }

        try {
            tr.save(novaTura);
        } catch (Exception e) {
            System.out.println("Nije dobro sacuvano");
            return null;
        }

        return novaTura;
    }

	public boolean promeniTuru(int idTure, String naziv, String opis) {
		Optional<Tura> optionalEntity = tr.findById(idTure);
        if (optionalEntity.isPresent()) {
            Tura tura = optionalEntity.get();
            if(!naziv.isEmpty()) {
            	tura.setNaziv(naziv);
            }
            if(!opis.isEmpty()) {
            	tura.setOpis(opis);
            }
            tr.save(tura);
            return true;
        } else {
        	return false;
        }
	}

	public boolean objaviTuru(int idTure) {
		Optional<Tura> optionalEntity = tr.findById(idTure);
        if (optionalEntity.isPresent()) {
            Tura tura = optionalEntity.get();
            tura.setTip("javna");
            tr.save(tura);
            return true;
        } else {
        	return false;
        }
	}

	public List<Tura> getPrivatne() {
		Korisnik k = korisnikService.getCurrentUser();
		if(k == null){
			return null;
		}
        return tr.findByTipAndKorisnik("privatna", k);
	}
	
	public List<Tura> getJavne() {
        return tr.findByTip("javna");
	}

	public Tura prikaziDetaljeTure(int idTure, int idKorisnika) {
		Tura tura = tr.findById(idTure).orElseThrow(() -> new EntityNotFoundException("Tura " + idTure + " ne postoji"));
		if(tura.getKorisnik().getIdKorisnik() == idKorisnika || tura.getTip().equals("javna")) {
			return tura;
		}
		return null;
	}
	
	public Tura getById(int id) {
		return tr.findById(id).get();
	}

	public Tura sortirajPoDatumu(Tura tura){
		tura.setUmetnickodelos(tura.getUmetnickodelos().stream().sorted(Comparator.comparing(Umetnickodelo::getDatum)).toList());
		return tura;
	}

	public Tura sortirajPoRazdaljini(int idTure) {
		Tura tura = tr.findById(idTure).orElseThrow(() -> new RuntimeException("Ne postoji tura"));
		List<Umetnickodelo> delos = tura.getUmetnickodelos();
		System.out.println(delos);

		Map<Umetnickodelo, Double> deloDistances = new HashMap<>();
		for (Umetnickodelo delo : delos) {
			double totalDistance = routeService.calculateTotalDistance(delo, delos);
			deloDistances.put(delo, totalDistance);
		}

		List<Umetnickodelo> sortedDelos = delos.stream()
				.sorted(Comparator.comparing(deloDistances::get))
				.collect(Collectors.toList());

		tura.setUmetnickodelos(sortedDelos);
		return tura;
	}

	public Tura findById(int id) {
		return tr.findById(id).get();
	}

}
