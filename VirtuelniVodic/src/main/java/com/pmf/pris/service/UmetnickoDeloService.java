package com.pmf.pris.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmf.pris.repository.TuraRepository;
import com.pmf.pris.repository.UmetnickoDeloRepository;
import model.Tura;
import model.Umetnickodelo;

@Service
public class UmetnickoDeloService {
    
    @Autowired
    UmetnickoDeloRepository udr;
    
    @Autowired
    TuraRepository tr;
    
    public List<Umetnickodelo> getDelaUTuri(int idTure) {
        Tura t = tr.findById(idTure).get();
        List<Umetnickodelo> delaUTuri = t.getUmetnickodelos();
        return delaUTuri;
    }
    
    public List<Umetnickodelo> getDela(){
        return udr.findAll();
    }
    
    public boolean dodajUmetnickoDelo(String naziv, String opis) {
        try {
            Umetnickodelo novoDelo = new Umetnickodelo();
            novoDelo.setNaziv(naziv);
            novoDelo.setOpis(opis);
            udr.save(novoDelo);
            return true;
        } catch (Exception e) {
            System.out.println("Nije dodato umetničko delo: " + e.getMessage());
            return false;
        }
    }
    
    public boolean promeniOpisUmetnickogDela(int idDela, String noviOpis) {
        Optional<Umetnickodelo> optionalDelo = udr.findById(idDela);
        if (optionalDelo.isPresent()) {
            Umetnickodelo umetnickoDelo = optionalDelo.get();
            umetnickoDelo.setOpis(noviOpis);
            udr.save(umetnickoDelo);
            return true;
        } else {
            return false;
        }
    }
}
