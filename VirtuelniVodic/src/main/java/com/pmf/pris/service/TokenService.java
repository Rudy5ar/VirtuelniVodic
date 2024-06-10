package com.pmf.pris.service;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.pmf.pris.model.dto.TokenDTO;

import model.Korisnik;

@Service
public class TokenService {
	
	private static final String ISSUER = "pris";

	@Autowired
	private JwtDecoder decoder;
	
	@Autowired
	private JwtEncoder encoder;
	
	public TokenDTO generateToken(Korisnik korisnik) {
		return TokenDTO.builder()
				.accessToken(generateAccesToken(korisnik).getTokenValue())
				.refreshToken(generateRefreshToken(korisnik).getTokenValue())
				.build();
	}
	
	public TokenDTO refreshAccessToken(TokenDTO tokenDTO, Korisnik korisnik) {
		Jwt refreshToken = decoder.decode(tokenDTO.getRefreshToken());
		
		if(isTokenExpired(refreshToken)) {
			throw new IllegalArgumentException();
		}
		
		tokenDTO.setAccessToken(generateAccesToken(korisnik).getTokenValue());
		
		return tokenDTO;
	}
	
	public boolean validateAccessToken(String token) {
		try {
			Jwt accessToken = decoder.decode(token);
			return !isTokenExpired(accessToken) && accessToken.getClaim("iss").toString().equals(ISSUER);			
		} catch (BadJwtException e) {
			return false;
		}
	}
	
	public UserDetails extractUserDetails(String token) {
		Jwt jwt = decoder.decode(token);
		String subject = jwt.getSubject();
		return User.builder()
				.username(subject)
				.password("notimportant")
				.authorities((String)jwt.getClaim("scope"))
				.build();
	}
	
	private Jwt generateAccesToken(Korisnik korisnik) {
		Instant now = Instant.now();
		Instant expiresAt = now.plus(10, MINUTES);
		String scope = korisnik.getUloga();
		
		JwtClaimsSet claims = JwtClaimsSet.builder().issuedAt(now)
				.expiresAt(expiresAt)
				.issuer(ISSUER)
				.subject(korisnik.getEmail())
				.claim("scope", scope)
				.build();

		return encoder.encode(JwtEncoderParameters.from(claims));
	}
	
	private Jwt generateRefreshToken(Korisnik korisnik) {
		Instant now = Instant.now();
		Instant expiresAt = now.plus(1, DAYS);
		String scope = korisnik.getUloga();
		
		JwtClaimsSet claims = JwtClaimsSet.builder().issuedAt(now)
				.expiresAt(expiresAt)
				.issuer(ISSUER)
				.subject(korisnik.getEmail())
				.claim("scope", scope)
				.build();

		return encoder.encode(JwtEncoderParameters.from(claims));
	}
	
	private boolean isTokenExpired(Jwt token) {
		return token.getExpiresAt().isBefore(Instant.now());
	}
}
