package com.pmf.pris.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmf.pris.service.UmetnickoDeloService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("umetnickoDelo")
public class UmetnickoDeloController {
	
	private final UmetnickoDeloService umetnickoDeloService;

    public UmetnickoDeloController(UmetnickoDeloService umetnickoDeloService) {
        this.umetnickoDeloService = umetnickoDeloService;
    }

    @GetMapping("svaDela")
	public String svaDela(HttpServletRequest request) {
		request.setAttribute("svaDela", umetnickoDeloService.getDela());
		return "home";
	}
	
}
