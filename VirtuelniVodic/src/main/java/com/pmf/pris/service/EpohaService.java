package com.pmf.pris.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.pmf.pris.repository.EpohaRepository;

import model.Epoha;

@Service
public class EpohaService {
	
	@Autowired
    private EpohaRepository er;
	
	public Epoha kreirajEpohu(String naziv, String opis, Date vremenskiPeriodOd, Date vremenskiPeriodDo) {
		Epoha novaEpoha = new Epoha();
		novaEpoha.setNaziv(naziv);
		novaEpoha.setOpis(opis);
		novaEpoha.setVremenskiPeriodOd(vremenskiPeriodOd);
		novaEpoha.setVremenskiPeriodDo(vremenskiPeriodDo);
		try {
			er.save(novaEpoha);
			return novaEpoha;
		} catch(Exception e) {
			return null;
		}
	}

    public List<Epoha> getAllEpohe() {
        return er.findAll();
    }

    public Epoha findById(int id) {
        return er.findById(id).orElse(null);
    }

}
