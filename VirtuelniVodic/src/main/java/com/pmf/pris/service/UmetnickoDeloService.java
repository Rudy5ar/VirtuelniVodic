package com.pmf.pris.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.EpohaRepository;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.UmetnickoDeloRepository;
import com.pmf.pris.repository.UmetnikRepository;

import model.Tura;
import model.Umetnickodelo;
import model.Umetnik;

@Service
public class UmetnickoDeloService {

    @Autowired
    UmetnickoDeloRepository udr;

    @Autowired
    TuraRepository tr;

    @Autowired
    UmetnikRepository ur;

    @Autowired
    EpohaRepository epohaRepository;

    public List<Umetnickodelo> getDelaUTuri(int idTure) {
        Tura t = tr.findById(idTure).get();
        return t.getUmetnickodelos();
    }

    public List<Umetnickodelo> getDela() {
        return udr.findAll();
    }

	public boolean kreirajUmetnickoDelo(String naziv, String opis, Date datum,
			double geografskaDuzina, double geografskaSirina, int umetnikId, String opstost1, String opstost2, String opstost3) {
		Umetnickodelo novaUmetnickoDelo = new Umetnickodelo();
		novaUmetnickoDelo.setNaziv(naziv);
		novaUmetnickoDelo.setOpis(opis);
		novaUmetnickoDelo.setDatum(datum);
		novaUmetnickoDelo.setGeografskaDuzina(geografskaDuzina);
		novaUmetnickoDelo.setGeografskaSirina(geografskaSirina);
		novaUmetnickoDelo.setUmetnik(ur.findById(umetnikId).get());
		novaUmetnickoDelo.setOpstost1(opstost1);
		novaUmetnickoDelo.setOpstost2(opstost2);
		novaUmetnickoDelo.setOpstost3(opstost3);

		try {
			udr.save(novaUmetnickoDelo);
		} catch (Exception e) {
			System.out.println("Nije dobro sacuvano");
		    return false;
		}

		return true;
	}
	
	public Umetnickodelo findById(int id) {
        return udr.findById(id).orElse(null);
    }
	
	public Umetnickodelo save(Umetnickodelo umetnickodelo) {
        return udr.save(umetnickodelo);
    }
	
	public boolean izmeniOpisUmetnickogDela(int idUmetnickoDelo, String noviOpis) {
        try {
            Umetnickodelo delo = udr.findById(idUmetnickoDelo).orElse(null);
            if (delo == null) {
                return false;
            }
            delo.setOpis(noviOpis);
            udr.save(delo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	public boolean izmeniAutoraUmetnickogDela(int idUmetnickoDelo, int noviUmetnikId) {
        Optional<Umetnickodelo> umetnickoDeloOpt = udr.findById(idUmetnickoDelo);
        Optional<Umetnik> noviUmetnikOpt = ur.findById(noviUmetnikId);

        if (umetnickoDeloOpt.isPresent() && noviUmetnikOpt.isPresent()) {
            Umetnickodelo umetnickoDelo = umetnickoDeloOpt.get();
            umetnickoDelo.setUmetnik(noviUmetnikOpt.get());
            try {
                udr.save(umetnickoDelo);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean izmeniGodinuNastankaUmetnickogDela(int idUmetnickoDelo, Date novaGodinaNastanka) {
        try {
            Umetnickodelo delo = udr.findById(idUmetnickoDelo).orElse(null);
            if (delo == null) {
                return false;
            }
            delo.setDatum(novaGodinaNastanka);
            udr.save(delo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Umetnickodelo getDetaljiUmetnickogDela(int idUmetnickoDelo) {
        return udr.findById(idUmetnickoDelo).orElse(null);
    }
    
    public List<Umetnickodelo> findAllById(List<Integer> ids) {
        return udr.findAllById(ids);
    }


}
