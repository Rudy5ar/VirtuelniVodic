package com.pmf.pris.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmf.pris.service.EpohaService;

import jakarta.servlet.http.HttpServletRequest;
import model.Epoha;

@Controller
@RequestMapping("epoha")
public class EpohaController {

	@Autowired
	private EpohaService es;
	
	@GetMapping("sveEpohe")
	public String sveEpohe(HttpServletRequest request) {
		request.getSession().setAttribute("sveEpohe", es.getAllEpohe());
		return "epoha";
	}
	
	@PostMapping("kreirajEpohu")
	public String kreirajEpohu(HttpServletRequest request, @RequestParam String opis, @RequestParam String naziv, @RequestParam Date vremenskiPeriodOd, @RequestParam Date vremenskiPeriodDo) {
		Epoha e = es.kreirajEpohu(opis, naziv, vremenskiPeriodOd, vremenskiPeriodDo);
		
		if (e == null) {
			System.out.println("greska");
		}
		request.setAttribute("epoha", e);
		return "epoha/kreiranaEpoha";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
}
