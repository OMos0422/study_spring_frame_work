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
public class MyDbThirdController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbthird", method = RequestMethod.GET)
	public String dbsg2(Model model) {


		
		//検索処理(secondtable用)
		List<Map<String,Object>> kensakukekkaSecond;
		kensakukekkaSecond = jdbcTemplate.queryForList("SELECT * FROM thirdhukutable");

		
		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra",kensakukekkaSecond);

		return "mydbthird";
	}

	
	/*****データ更新用******/
	@RequestMapping(path = "/mydbthirdhukuUpd", method = RequestMethod.POST)
	public String dbspupd(Model model,String animalnamebefore,String animalnameafter) {

		
		// データ更新
		jdbcTemplate.update("UPDATE thirdhukutable SET animalname = ? WHERE animalname = ?;", animalnameafter, animalnamebefore);
		
		//データ登録後、全件検索
		List<Map<String,Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM thirdhukutable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra",kensakukekka);		

		return "mydbthird";
	}
	/**************************************************************************/
	
	/*****データ削除用******/
	@RequestMapping(path = "/mydbthirdhukuDel", method = RequestMethod.POST)
	public String dbspdel(Model model,String animalname,String animalcategory) {

		
		// データ削除
		jdbcTemplate.update("DELETE FROM thirdhukutable WHERE animalname = ?;", animalname);

		//データ登録後、全件検索
		List<Map<String,Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM thirdhukutable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra",kensakukekka);		

		return "mydbthird";
	}
	/**************************************************************************/
	
	
}