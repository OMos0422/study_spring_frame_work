package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyTenthController {
	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/tenthpage", method = RequestMethod.GET)
	public String tenthget() {

		return "mytenth";
	}

	@RequestMapping(path = "/tenthpage", method = RequestMethod.POST)
	public String tenthppost(String password, Model model) {

		model.addAttribute("inputPass", password);

		return "mytenth";
	}
}