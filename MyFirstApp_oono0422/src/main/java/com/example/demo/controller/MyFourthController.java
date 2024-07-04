package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFourthController {

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fourthpage", method = RequestMethod.GET)
	public String fourthget() {
		return "myfourth";
	}
	
	//画面からの値を処理するPOSTメソッド
	@RequestMapping(path = "/fourthpage", method = RequestMethod.POST)
	public String fourthpost(String param1, String hobby,String bplace, Model model) {
		
		System.out.println("画面から"+ param1 +"と入力されました");
		System.out.println("趣味は"+hobby+"のようです。");
		System.out.println("出身地は"+bplace+"のようです。");
		
		model.addAttribute("userName", param1);
		model.addAttribute("userHobby", hobby);
		model.addAttribute("bp", bplace);
		
		return "myfourth";
	}
}