package com.pmf.pris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmf.pris.service.EpohaService;

import jakarta.servlet.http.HttpServletRequest;

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
	
}
