package com.pmf.pris.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.pmf.pris.repository.ClanakRepository;
import com.pmf.pris.repository.KorisnikRepository;

import jakarta.persistence.EntityNotFoundException;
import model.Clanak;

@Service
public class ClanakService {
	
	private final ClanakRepository cr;
	private final KorisnikService ks;
	
	public ClanakService(ClanakRepository cr, KorisnikService ks) {
		this.cr = cr;
		this.ks = ks;
	}
	
	public Clanak kreirajClanak(String naziv, String tekst, int id) {
		Clanak noviClanak = new Clanak();
		noviClanak.setKorisnik(ks.getCurrentUser());
		noviClanak.setNaziv(naziv);
		noviClanak.setTekst(tekst);
		noviClanak.setDatumKreiranja(new Date());
		try {
			return cr.save(noviClanak);
		} catch (Exception e) {
			System.out.println("Clanak nije dobro sacuvan");
			return null;
		}
	}
}
