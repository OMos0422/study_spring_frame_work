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
public class MyDbThirdHukuController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbthirdhuku", method = RequestMethod.GET)
	public String dbsg2(Model model) {

		//検索処理(secondtable用)
		List<Map<String, Object>> kensakukekkaSecond;
		kensakukekkaSecond = jdbcTemplate.queryForList("SELECT * FROM thirdhukutable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekkaSecond);

		return "mydbthirdhuku";
	}

	/**************************↓ここから上は完成済みだよ。↑******************************/

	/**************************↓ここから考えてみてね。↓******************************/
	@RequestMapping(path = "/mydbthirdhukusearch", method = RequestMethod.POST)
	public String dbfgpra(Model model, String animalcategory) {

		List<Map<String, Object>> kensakukekka;

		//検索処理
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM thirdhukutable WHERE animalcategory = ?",
				animalcategory);

		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra", kensakukekka);
		return "mydbthirdhuku";
	}

	/*****データ登録用******/
	@RequestMapping(path = "/mydbthirdhukuIns", method = RequestMethod.POST)
	public String dbspins(Model model, String inanimal, String inkind) {

		//データ登録
		jdbcTemplate.update("INSERT INTO thirdhukutable (animalname , animalcategory) VALUES (?,?);", inanimal, inkind);

		//データ登録後、全件検索
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM thirdhukutable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbthirdhuku";
	}
	/**************************************************************************/

}