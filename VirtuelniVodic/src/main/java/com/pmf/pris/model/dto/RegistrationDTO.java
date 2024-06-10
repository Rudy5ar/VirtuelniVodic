package com.pmf.pris.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationDTO {
	@NotBlank
	private String email;
	@NotBlank
	private String korisnickoIme;
	@NotBlank
	private String sifra;
	@NotBlank
	private String sifraProvera;
}
