package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class SummaryController {

	@RequestMapping(path = "/summary", method = RequestMethod.GET)
	public String summarydisp(Model model, HttpSession session) {

		//覚えてる④modelの利用
		model.addAttribute("modeldata", "あいうえお");

		//覚えてる⑤sessionの利用
		session.setAttribute("sessiondata", "かきくけこ");

		return "summary";
	}

	@RequestMapping(path = "/summary", method = RequestMethod.POST)
	public String summarypost(Model model, String formdata1, String formdata2) {

		//覚えてる⑦ Sysoutで表示しよう。
		System.out.println("formタグで受け取ったデータのひとつ目は" + formdata1);
		System.out.println("formタグで受け取ったデータのふたつ目は" + formdata2);

		//覚えてる⑧ 受け取った2つの変数を結合しよう。
		String x = formdata1 + formdata2;

		//覚えてる⑧ modelに結合結果(変数x)を格納しよう。
		model.addAttribute("oboe7", x);

		return "summary";
	}
}