package com.pmf.pris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pmf.pris.exception.BadLoginException;
import com.pmf.pris.exception.BadRegistrationException;
import com.pmf.pris.exception.EmailAlreadyExistException;
import com.pmf.pris.model.dto.LoginDTO;
import com.pmf.pris.model.dto.RegistrationDTO;
import com.pmf.pris.model.dto.TokenDTO;
import com.pmf.pris.service.KorisnikService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@PostMapping("/register")
	public String register(@ModelAttribute @Valid RegistrationDTO registration,
			HttpServletResponse response,
			Model model) {
		log.info("Registracija: {}", registration.toString());
		TokenDTO token = korisnikService.register(registration);
		response.addHeader("Set-Cookie", "_jwt=" + token.getAccessToken() + "; HttpOnly; Secure; SameSite=Lax;");
		return "redirect:/security";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute @Valid LoginDTO login,
			HttpServletResponse response,
			Model model) {
		log.info("Prijava: {}", login.toString());
		TokenDTO token = korisnikService.login(login);
		response.addHeader("Set-Cookie", "_jwt=" + token.getAccessToken() + "; HttpOnly; Secure; SameSite=Lax;");
		return "redirect:/security";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new LoginDTO());
		return "index";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new RegistrationDTO());
		return "register";
	}
	
	@GetMapping("/security")
	public String security(Model model) {
		model.addAttribute("korisnickoIme", korisnikService.getCurrentUser().getKorisnickoIme());
		return "security";
	}
	
	@ExceptionHandler({UsernameNotFoundException.class, BadRegistrationException.class,
		EmailAlreadyExistException.class, BadLoginException.class})
	public String handleException(Exception ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		return "error";
	}
	
}
