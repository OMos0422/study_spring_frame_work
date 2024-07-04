package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MyTenthHukuController {
	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/tenhuku", method = RequestMethod.GET)
	public String ninthget(Model model) {

		/***サンプル集用***/
		model.addAttribute("sample1","サンプルだよ。モデル使ってるよ");
		
		model.addAttribute("sample2","2個目");
		
		List<String> samplelist = new ArrayList<>();
		
		samplelist.add("リストの中身1つ目");
		samplelist.add("リストの中身2つ目");
		samplelist.add("リストの中身3つ目");
		
		model.addAttribute("samplelist",samplelist);
		
		/***復習問題用(ここは触る必要無し)***/
		model.addAttribute("mondaione","1問目だよ～。これが画面に出ればクリアだよ～～");
		
		model.addAttribute("mondaitwo","2問目");
		
		List<String> list = new ArrayList<>();
		
		list.add("データ1");
		list.add("データ2");
		list.add("データ3");
		
		model.addAttribute("resultList",list);
		
		return "mytenhuku";
	}
}