package com.pmf.pris.security.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pmf.pris.repository.KorisnikRepository;
import com.pmf.pris.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Korisnik;

@Component
public class JwtTokenFilter extends OncePerRequestFilter { 
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getCookies() == null) {
			filterChain.doFilter(request, response);
			return;
		}

		Cookie cookie = null;
		String token = null;

		for (Cookie current : request.getCookies()) {
			if (current.getName().equals("_jwt")) {
				cookie = current;
				token = cookie.getValue();
				break;
			}
		}

		
		if(token == null) {
			filterChain.doFilter(request, response);
			return;
		}	
		
		if(tokenService.validateAccessToken(token)) {
			UserDetails user = tokenService.extractUserDetails(token);
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities()));
			String username = user.getUsername();
			Optional<Korisnik> korisnik =  korisnikRepository.findKorisnikByEmail(user.getUsername());
			
			request.getSession().setAttribute("username", korisnik.isPresent() ? korisnik.get().getKorisnickoIme() : username);
		}
		
		filterChain.doFilter(request, response);
	}

}
