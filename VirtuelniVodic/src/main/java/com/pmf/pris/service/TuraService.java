package com.pmf.pris.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
		List<Tura> privatne = tr.findByTip("privanta");
		
		return privatne;
	}
	
	public List<Tura> getJavne() {
		List<Tura> javne = tr.findByTip("javna");
		
		return javne;
	}

	public Tura prikaziDetaljeTure(int idTure, int idKorisnika) {
		Tura tura = tr.findById(idTure).get();
		if(tura.getKorisnik().getIdKorisnik() == idKorisnika || tura.getTip().equals("javna")) {
			return tura;
		}
		return null;
	}
	
	
	public void napraviIzvestaj(int idTure) throws FileNotFoundException, DocumentException {
		Tura tura = tr.findById(idTure).get();
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Tura.pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK);
		Chunk id = new Chunk("id: " + tura.getIdTura(), font);
		Chunk opis = new Chunk("Opis: " + tura.getOpis(), font);
		Chunk naziv = new Chunk("Naziv: " + tura.getNaziv(), font);
		Chunk korisnik = new Chunk("Korisnik: " + tura.getKorisnik().getKorisnickoIme(), font);
		Chunk tip = new Chunk("Tip: " + tura.getTip(), font);
		Paragraph naslov = new Paragraph("Prikaz informacija za odabranu turu");
		naslov.setAlignment(Element.ALIGN_CENTER);
		


		document.add(naslov);
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(id);
		document.add(new Paragraph("\n"));
		document.add(opis);
		document.add(new Paragraph("\n"));
		document.add(korisnik);
		document.add(new Paragraph("\n"));
		document.add(naziv);
		document.add(new Paragraph("\n"));
		document.add(tip);
		document.close();
	}
}

