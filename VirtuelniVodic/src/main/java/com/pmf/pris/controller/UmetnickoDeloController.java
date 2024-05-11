package com.pmf.pris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmf.pris.service.UmetnickoDeloService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("umetnickoDelo")
public class UmetnickoDeloController {
	
	@Autowired
	UmetnickoDeloService uds;
	
	@GetMapping("svaDela")
	public String svaDela(HttpServletRequest request) {
		request.setAttribute("svaDela", uds.getDela());
		return "home";
	}
	
}
