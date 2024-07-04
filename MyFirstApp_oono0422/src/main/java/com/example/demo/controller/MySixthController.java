package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MySixthController {

	@RequestMapping(path = "/sixthpra", method = RequestMethod.GET)
	public String four(Model model) {

		model.addAttribute("ichimonme", "いちもんめ");

		return "MySixth";
	}

	@RequestMapping(path = "/sixthpra", method = RequestMethod.POST)
	public String fourpost(String mem1, String mem2) {

		System.out.println(mem1 + mem2);

		return "MySixth";
	}
}