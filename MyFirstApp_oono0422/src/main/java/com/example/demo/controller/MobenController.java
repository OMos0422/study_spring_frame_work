package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.moben;
import com.example.demo.repository.MobenRepository;

@Controller
public class MobenController {
	@Autowired
	MobenRepository mob;

	@RequestMapping(path = "/mobkai2", method = RequestMethod.GET)
	public String mobget(Model model) {
		List<moben> moben = mob.findAll();
		model.addAttribute("mob", moben);
		return "moben";
	}

}
