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
public class SummaryDbController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/summarydb", method = RequestMethod.GET)
	public String summarydisp(Model model, HttpSession session) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM summarytb");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakukekka", kensakukekka);

		return "summarydb";
	}

	@RequestMapping(path = "/summarydb", method = RequestMethod.POST)
	public String summarypost(String inputData1, String inputData2) {

		//summarytbテーブルにデータを登録.column1, column2にinputData1, inputData2を登録
		jdbcTemplate.update("INSERT INTO summarytb VALUES (?,?)", inputData1, inputData2);

		return "redirect:/summarydb";
	}
}