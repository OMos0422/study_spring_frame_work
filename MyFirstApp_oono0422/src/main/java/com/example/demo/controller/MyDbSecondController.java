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
public class MyDbSecondController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbsecond", method = RequestMethod.GET)
	public String dbsg2(Model model) {

		//検索処理(secondtable用)
		List<Map<String, Object>> kensakukekkaSecond;
		kensakukekkaSecond = jdbcTemplate.queryForList("SELECT * FROM secondtable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupraSecond", kensakukekkaSecond);

		return "mydbsecond";
	}

	/**************************↓今日重要なのはここから↓******************************/
	@RequestMapping(path = "/mydbsecond", method = RequestMethod.POST)
	public String dbfgpra(Model model, String searchWordJp, String searchWordEn) {

		List<Map<String, Object>> kensakukekka;
		System.out.println(searchWordJp);

		if (searchWordJp != null) {
			//検索処理
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM secondtable WHERE nihongo = ?;", searchWordJp);

			//検索結果のリストをmodelに格納する。
			model.addAttribute("kensakupraSecond", kensakukekka);
		} else {
			//【慣れよう問題用】
			//検索処理
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM secondtable WHERE eiyaku = ?;", searchWordEn);
			//検索結果のリストをmodelに格納する。
			model.addAttribute("kensakupraSecond", kensakukekka);
		}

		//練習問題用
		if (searchWordJp != null || searchWordJp != null) {
			//検索処理
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM secondtable WHERE nihongo = ? OR eiyaku = ?;",
					searchWordJp, searchWordEn);
			//検索結果のリストをmodelに格納する。
			model.addAttribute("kensakupraSecond", kensakukekka);
		}

		return "mydbsecond";
	}

	@RequestMapping(path = "/mydbsecondIns", method = RequestMethod.POST)
	public String dbspins(Model model, String inputWordJp, String inputWordEn) {

		//データ登録
		jdbcTemplate.update("INSERT INTO secondtable (nihongo,eiyaku) VALUES (?,?);", inputWordJp, inputWordEn);

		//データ登録後、全件検索
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM secondtable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupraSecond", kensakukekka);

		return "mydbsecond";
	}
	/**************************************************************************/

}