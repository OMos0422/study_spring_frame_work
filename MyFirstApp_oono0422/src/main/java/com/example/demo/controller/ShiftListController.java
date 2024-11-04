package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.shiftlist;
import com.example.demo.repository.ShiftListRepository;

@Controller
public class ShiftListController {
	@Autowired
	ShiftListRepository list;

	@RequestMapping(path = "/koukihuku6", method = RequestMethod.GET)
	public String sheetget(Model model) {

		List<shiftlist> shift = list.findAll();
		model.addAttribute("shiftlist", shift);
		return "shiftlist";
	}
}
