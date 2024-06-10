package com.pmf.pris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pmf.pris.exception.BadLoginException;
import com.pmf.pris.exception.BadRegistrationException;
import com.pmf.pris.exception.EmailAlreadyExistException;
import com.pmf.pris.model.dto.LoginDTO;
import com.pmf.pris.model.dto.RegistrationDTO;
import com.pmf.pris.model.dto.TokenDTO;
import com.pmf.pris.repository.KorisnikRepository;

import jakarta.validation.Valid;
import model.Korisnik;

@Service
public class KorisnikService implements UserDetailsService{

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public TokenDTO register(RegistrationDTO registration) {
		if(korisnikRepository.findKorisnikByEmail(registration.getEmail()).isPresent()) {
			throw new EmailAlreadyExistException();
		}
		
		if(!registration.getSifra().equals(registration.getSifraProvera())) {
			throw new BadRegistrationException();
		}
		
		String encodedPassword = passwordEncoder.encode(registration.getSifra());
		
		Korisnik korisnik = new Korisnik();
		korisnik.setEmail(registration.getEmail());
		korisnik.setKorisnickoIme(registration.getKorisnickoIme());
		korisnik.setSifra(encodedPassword);
		korisnik.setUloga("KORISNIK");
		
		korisnikRepository.save(korisnik);
		
		return tokenService.generateToken(korisnik);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik korisnik = korisnikRepository.findKorisnikByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Nema korisnik sa imenom %s!", username)));
		
		return User.builder().username(korisnik.getEmail())
				.password(korisnik.getSifra())
				.authorities(korisnik.getUloga())
				.build();
	}

	public TokenDTO login(@Valid LoginDTO login) {
		Korisnik korisnik = korisnikRepository.findKorisnikByEmail(login.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("Nije ispravan email ili lozinka!"));
		
		if(!passwordEncoder.matches(login.getSifra(), korisnik.getSifra())) {
			throw new BadLoginException("Nije ispravan email ili lozinka!");
		}
		
		return tokenService.generateToken(korisnik);
	}

	public Korisnik getCurrentUser() {
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) 
				SecurityContextHolder.getContext().getAuthentication();
				
		
		return korisnikRepository.findKorisnikByEmail(authentication.getName()).get();
	}
}
