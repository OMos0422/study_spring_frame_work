package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyDbSecondHukuController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*****************復習問題用********************/
	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbsecondhuku", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//(以下はサンプルです。復習問題用に必要な所を変えましょう）
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM secondhukutable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbsecondhuku";
	}
}