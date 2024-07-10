package com.pmf.pris.repository;

import model.Epoha;
import model.Umetnik;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Umetnickodelo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UmetnickoDeloRepository extends JpaRepository<Umetnickodelo, Integer>{

    @Query("SELECT DISTINCT u FROM Umetnickodelo u " +
            "LEFT JOIN u.epohas e " +
            "WHERE (:epohas IS NULL OR e.idEpoha IN :epohas) " +
            "AND (:umetnik IS NULL OR u.umetnik = :umetnik) " +
            "AND (:godinaNastanka IS NULL OR u.datum = :godinaNastanka)")
    List<Umetnickodelo> searchUmetnickaDela(@Param("epohas") List<Integer> epohas,
                                            @Param("umetnik") Umetnik umetnik,
                                            @Param("godinaNastanka") Date godinaNastanka);
}
