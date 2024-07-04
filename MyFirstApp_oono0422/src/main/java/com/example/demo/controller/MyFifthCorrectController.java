package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFifthCorrectController {

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fifthcor", method = RequestMethod.GET)
	public String fourthget() {
		return "myfifthcorrect";
	}

	//画面からの値を処理するPOSTメソッド
	@RequestMapping(path = "/fifthcor", method = RequestMethod.POST)
	public String fourthpost(String debuggerName, Model model) {
		if("つよエンジニア".equals(debuggerName)) {
			debuggerName = "問題無し";
			System.out.println("問題なし");
			model.addAttribute("uhouho", debuggerName);
		} else if("ひよこエンジニア".equals(debuggerName)) {
			debuggerName = "もう一度確認";
			System.out.println("もう一度確認");
			model.addAttribute("uhouho", debuggerName);
		} else {
			System.out.println(debuggerName);
			model.addAttribute("uhouho", debuggerName);
		}
		
		return "myfifthcorrect";
	}
}