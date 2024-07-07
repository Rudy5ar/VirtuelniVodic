package com.pmf.pris.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.service.EpohaService;
import com.pmf.pris.service.UmetnikService;

import jakarta.servlet.http.HttpServletRequest;
import model.Epoha;
import model.Umetnik;

@Controller
@RequestMapping("umetnik")
public class UmetnikController {
	
	private final UmetnikService us;
	private final EpohaService es;
	
	public UmetnikController(UmetnikService us, EpohaService es) {
		this.us = us;
		this.es = es;
	}
	
	@GetMapping("kreirajUmetnika")
	public String getEpoheZaUmetnika(HttpServletRequest request) {
		List<Epoha> epohe = es.getAllEpohe();
		request.setAttribute("epohe", epohe);
		return "umetnik/kreiranjeUmetnika";
	}
	
	@PostMapping("kreirajUmetnika")
	public String kreirajUmetnika(HttpServletRequest request, @RequestParam String ime, @RequestParam Date godinaRodjenja, @RequestParam Date godinaSmrti, @RequestParam Epoha epoha) {
		Umetnik u = us.kreirajUmetnika(ime, godinaRodjenja, godinaSmrti, epoha);
		if (u == null) {
			return "error";
		}
		request.setAttribute("umetnik", u);
		return "umetnik/kreiranUmetnik";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
