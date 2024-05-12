package com.pmf.pris.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Tura;

@Repository
public interface TuraRepository extends JpaRepository<Tura, Integer>{

	public List<Tura> findByTip(String string);

}

