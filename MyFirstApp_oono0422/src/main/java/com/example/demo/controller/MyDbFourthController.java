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
public class MyDbFourthController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fourthpra", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthtable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfourth";
	}

	@RequestMapping(path = "/fourthpra", method = RequestMethod.POST)
	public String dbfg2(Model model, String sikaku) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM fourthtable WHERE sikakuname = ?", sikaku);

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfourth";
	}

	@RequestMapping(path = "/fourthpraIns", method = RequestMethod.POST)
	public String dbfg3(Model model, String cername, String cerrank) {
		jdbcTemplate.update("INSERT INTO fourthtable (sikakuname,sikakurank) VALUES (?,?)", cername, cerrank);
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthtable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "redirect:/fourthpra";
	}

	@RequestMapping(path = "/fourthpraUpd", method = RequestMethod.POST)
	public String dbfg4(Model model, String cerrank, String cername) {
		jdbcTemplate.update("UPDATE fourthtable SET sikakurank = ? WHERE sikakuname = ? ", cerrank, cername);
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthtable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "redirect:/fourthpra";
	}

	@RequestMapping(path = "/fourthpraDel", method = RequestMethod.POST)
	public String dbfg5(Model model, String cername) {
		jdbcTemplate.update("DELETE FROM fourthtable WHERE sikakuname = ? ", cername);
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthtable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "redirect:/fourthpra";
	}

}