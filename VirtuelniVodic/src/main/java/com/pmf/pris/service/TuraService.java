package com.pmf.pris.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pmf.pris.maps.OpenRouteService;
import com.pmf.pris.repository.KorisnikRepository;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.UmetnickoDeloRepository;

import jakarta.persistence.EntityNotFoundException;
import model.Tura;
import model.Umetnickodelo;

@Service
public class TuraService {

	private final TuraRepository tr;
	private final KorisnikRepository kr;
	private final OpenRouteService routeService;
	private final UmetnickoDeloService umetnickoDeloService;
	private final UmetnickoDeloRepository udr;

    public TuraService(TuraRepository tr, KorisnikRepository kr, OpenRouteService routeService, UmetnickoDeloService umetnickoDeloService, UmetnickoDeloRepository udr) {
        this.tr = tr;
        this.kr = kr;
        this.routeService = routeService;
        this.umetnickoDeloService = umetnickoDeloService;
        this.udr = udr;
    }
    
    public Tura kreirajTuru(String naziv, String opis, String tip, int i, List<Integer> umetnickaDelaIds) {
        Tura novaTura = new Tura();
        novaTura.setKorisnik(kr.findById(i).orElseThrow(() -> new EntityNotFoundException("Korisnik " + i + " ne postoji")));
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

//    public Tura kreirajTuru(String naziv, String opis, String tip, int i, List<Integer> umetnickaDelaIds) {
//		Tura novaTura = new Tura();
//		novaTura.setKorisnik(kr.findById(i).orElseThrow(() -> new EntityNotFoundException("Korisnik " + i + " ne postoji")));
//		novaTura.setNaziv(naziv);
//		novaTura.setOpis(opis);
//		novaTura.setTip(tip);
//		
//		List<Umetnickodelo> umetnickaDela = umetnickoDeloService.findAllById(umetnickaDelaIds);
//	    novaTura.setUmetnickodelos(umetnickaDela);
//		
//		try {
//			tr.save(novaTura);
//		} catch (Exception e) {
//			System.out.println("Nije dobro sacuvano");
//		    return null;
//		}
//
//		return novaTura;
//	}
	
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
        return tr.findByTip("privatna");
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

		Map<Umetnickodelo, Integer> deloDistances = new HashMap<>();
		for (Umetnickodelo delo : delos) {
			int totalDistance = calculateTotalDistance(delo, delos);
			deloDistances.put(delo, totalDistance);
		}

		List<Umetnickodelo> sortedDelos = delos.stream()
				.sorted(Comparator.comparing(deloDistances::get))
				.collect(Collectors.toList());

		tura.setUmetnickodelos(sortedDelos);
		return tura;
	}

	private int calculateTotalDistance(Umetnickodelo currentDelo, List<Umetnickodelo> allDelos) {
		String currentLat = String.valueOf(currentDelo.getGeografskaSirina());
		String currentLong = String.valueOf(currentDelo.getGeografskaDuzina());

		return allDelos.stream()
				.filter(delo -> !delo.equals(currentDelo))
				.mapToInt(delo -> routeService.getDistance(currentLat, currentLong, String.valueOf(delo.getGeografskaSirina()), String.valueOf(delo.getGeografskaDuzina())))
				.sum();
	}


}
