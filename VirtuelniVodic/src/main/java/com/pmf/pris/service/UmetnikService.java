package com.pmf.pris.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pmf.pris.repository.UmetnikRepository;

import model.Epoha;
import model.Umetnik;

@Service
public class UmetnikService {
	
	private final UmetnikRepository ur;
	
	public UmetnikService(UmetnikRepository ur) {
		this.ur = ur;
	}
	
	public Umetnik kreirajUmetnika(String ime, Date godinaRodjenja, Date godinaSmrti, Epoha epoha) {
		Umetnik noviUmetnik = new Umetnik();
		noviUmetnik.setIme(ime);
		noviUmetnik.setGodinaRodjenja(godinaRodjenja);
		noviUmetnik.setGodinaSmrti(godinaSmrti);
		noviUmetnik.setEpoha(epoha);
		epoha.addUmetnik(noviUmetnik);
		
		try {
			ur.save(noviUmetnik);
			return noviUmetnik;
		} catch (Exception e) {
			System.out.println("Greska pri cuvanju umetnika");
			return null;
		}
	}
	
	public List<Umetnik> getAllUmetnici() {
		return ur.findAll();
	}

    public Umetnik findById(int id) {
        return ur.findById(id).orElse(null);
    }

}
