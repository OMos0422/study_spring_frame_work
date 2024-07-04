package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyTwelvethController {

	@RequestMapping(path = "/twelvethpage", method = RequestMethod.GET)
	public String twelvethget(Model model, HttpSession session) {

		model.addAttribute("modelData", "モデルだよ～");
		List<String> list = new ArrayList<String>();
		list.add("1件目：田中 太郎");
		list.add("2件目：山本");
		list.add("3件目：たかはし");

		model.addAttribute("resultList", list);
		session.setAttribute("sessionData", "セッションにしまったよ");

		return "mytwelveth";
	}

	@RequestMapping(path = "/twelvethpage", method = RequestMethod.POST)
	public String twelvethpost(Model model, String text, HttpSession session) {
		model.addAttribute("inputData", text);
		session.setAttribute("sessionData2", text);

		return "mytwelveth";

	}

	@RequestMapping(path = "/tweone", method = RequestMethod.GET)
	public String twelvethget2(Model model, HttpSession session) {

		model.addAttribute("modelPra1", "練習問題①");
		List<String> praList = new ArrayList<String>();
		praList.add("1件目だよ");
		praList.add("2件目だよ");
		praList.add("3件目だよ");
		praList.add("4件目だよ");

		model.addAttribute("praList", praList);
		session.setAttribute("sessionData", "セッションにしまったよ");

		return "mytwelveth";
	}

	@RequestMapping(path = "/twefour", method = RequestMethod.GET)
	public String twelvethget3(Model model, HttpSession session) {

		model.addAttribute("modelPra1", "練習問題①");
		List<String> praList = new ArrayList<String>();
		praList.add("1件目だよ");
		praList.add("2件目だよ");
		praList.add("3件目だよ");
		praList.add("4件目だよ");

		model.addAttribute("praList", praList);
		session.setAttribute("sessionData", "セッションにしまったよ");

		return "mytwelveth";
	}

	@RequestMapping(path = "/twefour", method = RequestMethod.POST)
	public String twelvethpost3(Model model, HttpSession session,String text2) {

		model.addAttribute("modelPra1", "練習問題①");
		List<String> praList = new ArrayList<String>();
		praList.add("1件目だよ");
		praList.add("2件目だよ");
		praList.add("3件目だよ");
		praList.add("4件目だよ");

		model.addAttribute("praList", praList);
		session.setAttribute("sessionData", "セッションにしまったよ");
		session.setAttribute("sessionInput", text2);

		return "mytwelveth";
	}
}