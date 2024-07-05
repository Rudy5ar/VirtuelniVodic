package com.pmf.pris.service;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import model.Umetnickodelo;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.KorisnikRepository;
import com.pmf.pris.repository.TuraRepository;

import model.Tura;

@Service
public class TuraService {

	private final TuraRepository tr;
	private final KorisnikRepository kr;

    public TuraService(TuraRepository tr, KorisnikRepository kr) {
        this.tr = tr;
        this.kr = kr;
    }

    public boolean kreirajTuru(String naziv, String opis, int i) {
		Tura novaTura = new Tura();
		novaTura.setKorisnik(kr.findById(i).orElseThrow(() -> new EntityNotFoundException("Korisnik " + i + " ne postoji")));
		novaTura.setNaziv(naziv);
		novaTura.setOpis(opis);
		try {
			tr.save(novaTura);
		} catch (Exception e) {
			System.out.println("Nije dobro sacuvano");
		    return false;
		}

		return true;
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

}
