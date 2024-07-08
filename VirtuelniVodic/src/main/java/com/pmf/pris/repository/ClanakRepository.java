package com.pmf.pris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Clanak;

import java.util.List;

public interface ClanakRepository extends JpaRepository<Clanak, Integer>{
    public List<Clanak> findAllByOrderByDatumKreiranjaDesc();
}
