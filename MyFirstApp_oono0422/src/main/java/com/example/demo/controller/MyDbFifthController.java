package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyDbFifthController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/yoyaku", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfifth";
	}

	@RequestMapping(path = "/yoyaku", method = RequestMethod.POST)
	public String dbfg0(HttpSession session, Model model, String yoyaku) {

		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku");

		session.setAttribute("yoyaku", yoyaku);
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfifth";
	}

	//練習問題②用
	@RequestMapping(path = "/yoyakuIns", method = RequestMethod.POST)
	public String dbfg2(Model model, String yoyakubi, String yoyakuclass, String yoyakuname, String yoyakucoat) {

		// データ更新

		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM domeyoyaku WHERE yoyakubi = ? AND yoyakuname = ?", yoyakubi, yoyakucoat);
		if (kensakukekka.size() == 0) {
			jdbcTemplate.update("INSERT INTO domeyoyaku (yoyakubi,yoyakuclass,yoyakuname,yoyakucoat) VALUES (?,?,?,?)",
					yoyakubi, yoyakuclass, yoyakuname, yoyakucoat);
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku");
			model.addAttribute("kensakupra", kensakukekka);
		} else {
			model.addAttribute("no", "no");
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku");
			model.addAttribute("kensakupra", kensakukekka);
			return "mydbfifth";
		}

		//検索結果のリストをmodelに格納

		return "mydbfifth";

	}

	//練習問題③用
	@RequestMapping(path = "/yoyakuUpd", method = RequestMethod.POST)
	public String dbfg3(Model model, String yoyakuname, String yoyakubi, String upd) {

		// データ更新
		jdbcTemplate.update("UPDATE domeyoyaku SET yoyakubi  = ? WHERE yoyakuname = ? AND yoyakubi = ?", upd,
				yoyakuname, yoyakubi);

		//検索結果のリストをmodelに格納
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku");
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfifth";
	}

	//練習問題④用
	@RequestMapping(path = "/yoyakuDel", method = RequestMethod.POST)
	public String dbfg4(Model model, String yoyakuname, String yoyakubi) {

		// データ更新
		//検索結果のリストをmodelに格納
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM domeyoyaku WHERE yoyakubi = ? AND yoyakuname = ?", yoyakubi, yoyakuname);
		if (kensakukekka.size() == 0) {
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku");
			model.addAttribute("kensakupra", kensakukekka);
			return "mydbfifth";
		} else {
			jdbcTemplate.update("DELETE FROM domeyoyaku WHERE yoyakuname = ? AND yoyakubi = ? ", yoyakuname, yoyakubi);
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku");
			model.addAttribute("kensakupra", kensakukekka);

		}

		return "mydbfifth";
	}

}
