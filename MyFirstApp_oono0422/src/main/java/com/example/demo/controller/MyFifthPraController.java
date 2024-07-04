package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFifthPraController {
	@RequestMapping(path = "/fifthpra", method = RequestMethod.GET)
	public String fifth(Model model) {
		model.addAttribute("modelpra", "さんもんめ");
		return "myfifthpra";
	}

	@RequestMapping(path = "/fifthpra", method = RequestMethod.POST)
	public String fifthpost(Model model, String like) {
		System.out.println(like + "が好きなようです！");
		if ("お寿司".equals(like)) {
			like = "私もお寿司が好きです";
			System.out.println("私もお寿司が好きです");
			model.addAttribute("likefood", like);
		} else {
			model.addAttribute("likefood", like);
		}

		return "myfifthpra";
	}

}
