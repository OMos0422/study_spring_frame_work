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
public class MyDbThirdPraController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbthirdpra", method = RequestMethod.GET)
	public String dbsg2(Model model) {


		
		//検索処理(secondtable用)
		List<Map<String,Object>> kensakukekkaSecond;
		kensakukekkaSecond = jdbcTemplate.queryForList("SELECT * FROM thirdtable");

		
		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra",kensakukekkaSecond);

		return "mydbthirdpra";
	}
	
	@RequestMapping(path = "mydbthirdSer", method = RequestMethod.POST)
	public String dbsg3(Model model,String itemname) {

		//検索処理(secondtable用)
		List<Map<String,Object>> kensakukekkaSecond;
		kensakukekkaSecond = jdbcTemplate.queryForList("SELECT * FROM thirdtable WHERE itemname = ?",itemname);

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra",kensakukekkaSecond);

		return "mydbthirdpra";
	}
	
	@RequestMapping(path = "/mydbthirdIns", method = RequestMethod.POST)
	public String dbsg4(Model model,String inname,String inprice) {
		jdbcTemplate.update("INSERT INTO thirdtable (itemname,itemprice) VALUES(?,?)",inname,inprice);
		//検索処理(secondtable用)
		List<Map<String,Object>> kensakukekkaSecond;
		kensakukekkaSecond = jdbcTemplate.queryForList("SELECT * FROM thirdtable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra",kensakukekkaSecond);

		return "mydbthirdpra";
	}

	
	/*****データ更新用******/
	@RequestMapping(path = "/mydbthirdpraUpd", method = RequestMethod.POST)
	public String dbspupd(Model model,String beforeprice,String afterprice) {

		
		// データ更新
		jdbcTemplate.update("UPDATE thirdtable SET itemprice = ? WHERE itemprice = ?;",afterprice,beforeprice);
		
		
		//データ登録後、全件検索
		List<Map<String,Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM thirdtable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra",kensakukekka);		

		return "mydbthirdpra";
	}
	/**************************************************************************/
	
	/*****データ削除用******/
	@RequestMapping(path = "/mydbthirdpraDel", method = RequestMethod.POST)
	public String dbspdel(Model model,String itemname) {

		
		// データ削除
		jdbcTemplate.update("DELETE FROM thirdtable WHERE itemname = ?",itemname);
		
		//データ登録後、全件検索
		List<Map<String,Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM thirdtable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra",kensakukekka);		

		return "mydbthirdpra";
	}
	/**************************************************************************/
	
	
}