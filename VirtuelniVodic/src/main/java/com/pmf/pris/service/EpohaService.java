package com.pmf.pris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.EpohaRepository;

import model.Epoha;

@Service
public class EpohaService {
	
	@Autowired
    private EpohaRepository epohaRepository;

    public List<Epoha> getAllEpohe() {
        return epohaRepository.findAll();
    }

    public Epoha findById(int id) {
        return epohaRepository.findById(id).orElse(null);
    }

}
