package com.pmf.pris.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import model.Umetnickodelo;

@Data
public class TuraDTO {
	private String naziv;
	private String opis;
	private String tip;
	private List<Umetnickodelo> umetnickodelos = new ArrayList<>();
}
