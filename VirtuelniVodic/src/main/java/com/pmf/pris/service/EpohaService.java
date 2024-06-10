package com.pmf.pris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.EpohaRepository;

import model.Epoha;

@Service
public class EpohaService {
	
	@Autowired
	private EpohaRepository er;
	
	public List<Epoha> sveEpohe() {
		return er.findAll();
	}

}
