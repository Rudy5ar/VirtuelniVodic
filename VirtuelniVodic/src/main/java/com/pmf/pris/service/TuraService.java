package com.pmf.pris.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.KorisnikRepository;
import com.pmf.pris.repository.TuraRepository;

import model.Tura;

@Service
public class TuraService {

	@Autowired
	TuraRepository tr;
	
	@Autowired
	KorisnikRepository kr;
	
	public boolean kreirajTuru(String naziv, String opis, int i) {
		Tura novaTura = new Tura();
		novaTura.setKorisnik(kr.findById(i).get());
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
            if(!naziv.equals("")) {
            	tura.setNaziv(naziv);
            }
            if(!opis.equals("")) {
            	tura.setOpis(opis);
            }
            tr.save(tura);
            return true;
        } else {
        	return false;
        }
	}

}
