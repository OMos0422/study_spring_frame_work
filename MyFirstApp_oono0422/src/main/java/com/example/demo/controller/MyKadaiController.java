package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyKadaiController {

	@RequestMapping(path = "/userLogin", method = RequestMethod.GET)
	public String four(Model model) {

		return "mykadai";
	}

	@RequestMapping(path = "/userLogin", method = RequestMethod.POST)
	public String fourpost(Model model, String id, String pass, RedirectAttributes redirectAttributes) {

		if ("userlogin".equals(id) && "userpass".equals(pass)) {
			redirectAttributes.addFlashAttribute("id", id);
			return "redirect:/userHome";
		} else if (pass.length() <= 7 && id.length() >= 7 || pass.length() <= 7 && id.length() <= 7) {
			model.addAttribute("shortpass", "パスワードが短い");
			return "mykadai3";
		} else {
			model.addAttribute("miss", "失敗しました");
			return "mykadai";
		}

	}

	@RequestMapping(path = "/userHome", method = RequestMethod.GET)
	public String kadai(Model model) {

		String username = (String) model.getAttribute("id");
		model.addAttribute("username", username);
		return "mykadai2";
	}

	@RequestMapping(path = "/userHome", method = RequestMethod.POST)
	public String kadaipost(Model model, String tweet) {
		model.addAttribute("tweet", tweet);
		return "mykadai2";
	}
}