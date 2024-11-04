package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.sheetuser;
import com.example.demo.repository.SheetUserRepository;

@Controller
public class SheetUserController {
	@Autowired
	SheetUserRepository sheet;

	@RequestMapping(path = "/sheetuserkouki", method = RequestMethod.GET)
	public String sheetget(Model model) {

		List<sheetuser> sheetlist = sheet.findAll();
		model.addAttribute("sheetlist", sheetlist);
		return "sheetuser";
	}
}
