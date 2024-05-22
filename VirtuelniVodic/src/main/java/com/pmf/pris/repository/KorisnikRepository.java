package com.pmf.pris.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{

	Optional<Korisnik> findKorisnikByEmail(String username);

}

