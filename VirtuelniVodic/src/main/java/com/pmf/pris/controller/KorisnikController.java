package com.pmf.pris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmf.pris.exception.BadLoginException;
import com.pmf.pris.exception.BadRegistrationException;
import com.pmf.pris.exception.EmailAlreadyExistException;
import com.pmf.pris.model.dto.ExceptionDTO;
import com.pmf.pris.model.dto.LoginDTO;
import com.pmf.pris.model.dto.RegistrationDTO;
import com.pmf.pris.model.dto.TokenDTO;
import com.pmf.pris.service.KorisnikService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("korisnik")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@ResponseBody
	@PostMapping("/register")
	public TokenDTO register(@RequestBody @Valid RegistrationDTO registration) {
		log.info("Registracija: {}", registration.toString());
		return korisnikService.register(registration);
	}
	
	@ResponseBody
	@PostMapping("/login")
	public TokenDTO login(@RequestBody @Valid LoginDTO login) {
		log.info("Prijava: {}", login.toString());
		return korisnikService.login(login);
	}
	
	@ResponseBody
	@GetMapping("/test")
	public String test() {
		return "Working!";
	}
	
	@ResponseBody
	@ExceptionHandler({UsernameNotFoundException.class, BadRegistrationException.class,
		EmailAlreadyExistException.class, BadLoginException.class})
	public ExceptionDTO handleException(Exception ex) {
		return ExceptionDTO.builder()
				.message(ex.getMessage())
				.build();
	}
	
}
