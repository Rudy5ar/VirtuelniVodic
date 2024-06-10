package com.pmf.pris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmf.pris.repository.UmetnickoDeloRepository;

import model.Umetnickodelo;

@Service
public class UmetnickoDeloService {
	
	@Autowired
	UmetnickoDeloRepository udr;
	
	public List<Umetnickodelo> getDela(){
		return udr.findAll();
	}
	
}
