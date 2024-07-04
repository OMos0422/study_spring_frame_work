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
public class MyDbSecondSouController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbsecondsou", method = RequestMethod.GET)
	public String dbsg2(Model model) {

		//検索処理(secondsoutable用)
		List<Map<String, Object>> kensaku;
		kensaku = jdbcTemplate.queryForList("SELECT * FROM secondsoutable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensaku", kensaku);

		//検索結果のリストをmodelに格納

		return "mydbsecondsou";
	}

	@RequestMapping(path = "/mydbsecondsou", method = RequestMethod.POST)
	public String dbfgpra(Model model, String gaiyo, String setumei) {

		List<Map<String, Object>> kensaku;
		//検索処理(secondsoutable)
		if (gaiyo != null) {
			kensaku = jdbcTemplate.queryForList("SELECT * FROM secondsoutable WHERE gaiyo = ?;", gaiyo);
			model.addAttribute("kensaku", kensaku);
			//検索結果のリストをmodelに格納
		} else {
			kensaku = jdbcTemplate.queryForList("SELECT * FROM secondsoutable WHERE setumei = ?", setumei);
			model.addAttribute("kensaku", kensaku);
		}

		if (gaiyo != null || setumei != null) {
			//検索処理
			kensaku = jdbcTemplate.queryForList("SELECT * FROM secondsoutable WHERE gaiyo = ? OR setumei = ?;", gaiyo,
					setumei);
			//検索結果のリストをmodelに格納する。
			model.addAttribute("kensaku", kensaku);
		}

		return "mydbsecondsou";
	}

	@RequestMapping(path = "/mydbsecondsouIns", method = RequestMethod.POST)
	public String dbspins(Model model, String inputgaiyo, String inputsetumei) {

		List<Map<String, Object>> kensaku;
		//データ登録
		jdbcTemplate.update("INSERT INTO secondsoutable (gaiyo,setumei) VALUES (?,?);", inputgaiyo, inputsetumei);
		//データ登録後、全件検索
		kensaku = jdbcTemplate.queryForList("SELECT * FROM secondsoutable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensaku", kensaku);
		return "mydbsecondsou";
	}

}