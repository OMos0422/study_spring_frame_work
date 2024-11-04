package com.example.kouki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kouki.entity.sheetuser;
import com.example.kouki.repository.SheetUserRepository;

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
