package com.pmf.pris.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pmf.pris.repository.UmetnikRepository;

import model.Umetnik;

@Service
public class UmetnikService {
	
	private final UmetnikRepository ur;
	
	public UmetnikService(UmetnikRepository ur) {
		this.ur = ur;
	}
	
	public List<Umetnik> getAllUmetnici() {
		return ur.findAll();
	}

    public Umetnik findById(int id) {
        return ur.findById(id).orElse(null);
    }

}
