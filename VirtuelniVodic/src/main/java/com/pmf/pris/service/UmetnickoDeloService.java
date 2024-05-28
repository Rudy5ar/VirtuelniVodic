package com.pmf.pris.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.UmetnickoDeloRepository;
import com.pmf.pris.repository.UmetnikRepository;

import model.Tura;
import model.Umetnickodelo;

@Service
public class UmetnickoDeloService {
    
    @Autowired
    UmetnickoDeloRepository udr;
    
    @Autowired
    TuraRepository tr;
    
    @Autowired
    UmetnikRepository ur;
    
    public List<Umetnickodelo> getDelaUTuri(int idTure) {
        Tura t = tr.findById(idTure).get();
        return t.getUmetnickodelos();
    }
    
    public List<Umetnickodelo> getDela() {
        return udr.findAll();
    }
    
	public boolean kreirajUmetnickoDelo(String naziv, String opis, Date datum,
			double geografskaDuzina, double geografskaSirina, int i) {
		Umetnickodelo novaUmetnickoDelo = new Umetnickodelo();
		novaUmetnickoDelo.setNaziv(naziv);
		novaUmetnickoDelo.setOpis(opis);
		novaUmetnickoDelo.setDatum(datum);
		novaUmetnickoDelo.setGeografskaDuzina(geografskaDuzina);
		novaUmetnickoDelo.setGeografskaSirina(geografskaSirina);
		novaUmetnickoDelo.setUmetnik(ur.findById(i).get());
		
		try {
			udr.save(novaUmetnickoDelo);
		} catch (Exception e) {
			System.out.println("Nije dobro sacuvano");
		    return false;
		}

		return true;
	}
}
