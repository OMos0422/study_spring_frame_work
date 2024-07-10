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
public class MyDbSeventhController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//一覧、登録画面表示用
	@RequestMapping(path = "/sheetreserv", method = RequestMethod.GET)
	public String sheet1(Model model) {
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM sheetreserve");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbseventh";
	}

	//編集、削除画面表示用
	@RequestMapping(path = "/reservmodify", method = RequestMethod.GET)
	public String sheet11(Model model, String change) {

		/**returnの後ろのhtml名以外は変更の必要無し**/
		return "mydbseventh2";
	}

	@RequestMapping(path = "/sheetlogout", method = RequestMethod.POST)
	public String sheet2(HttpSession session, Model model) {
		//検索処理
		session.removeAttribute("loginid");

		return "redirect:/sheetlogin";
	}

	//登録用
	@RequestMapping(path = "/sheetIns", method = RequestMethod.POST)
	public String sheet2(HttpSession session, Model model, String num, String day) {
		String name = (String) session.getAttribute("loginid");
		//登録処理

		if (num.length() == 1) {
			num += "番";
		} else if (num.length() == 2 && !("番".equals(num.substring(1, 2)))) {
			num += "番";
		} else if (num.length() == 3 && !("番".equals(num.substring(2, 3)))) {
			num += "番";
		} else if (num.length() == 4 && !("番".equals(num.substring(3, 4)))) {
			num += "番";
		}

		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM sheetreserve WHERE yoyakubango = ?", num);
		if (kensakukekka.size() == 0 && !("".equals(num)) && !("".equals(day))) {
			String day1 = day.substring(5, 6);
			int d1 = Integer.parseInt(day.substring(8, 9));
			if ("1".equals(day1) && 1 <= d1 && 4 > d1) {
				String day2 = day.substring(5, 7);
				String day3 = day.substring(8, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO sheetreserve VALUES (?,?,?)", num, day4, name);
				kensakukekka = jdbcTemplate.queryForList("SELECT * FROM sheetreserve");
			} else if ("1".equals(day1) && d1 == 0) {
				String day2 = day.substring(5, 7);
				String day3 = day.substring(9, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO sheetreserve VALUES (?,?,?)", num, day4, name);
				kensakukekka = jdbcTemplate.queryForList("SELECT * FROM sheetreserve");
			} else if ("0".equals(day1) && d1 == 0) {
				String day2 = day.substring(6, 7);
				String day3 = day.substring(9, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO sheetreserve VALUES (?,?,?)", num, day4, name);
				kensakukekka = jdbcTemplate.queryForList("SELECT * FROM sheetreserve");
			} else if ("0".equals(day1) && 1 <= d1 && 4 > d1) {
				String day2 = day.substring(6, 7);
				String day3 = day.substring(8, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO sheetreserve VALUES (?,?,?)", num, day4, name);
				kensakukekka = jdbcTemplate.queryForList("SELECT * FROM sheetreserve");
			}
		} else {
			return "redirect:/sheetreserv";
		}

		return "redirect:/sheetreserv";
	}

	//更新用(ヒント無し)
	@RequestMapping(path = "/sheetUpd", method = RequestMethod.POST)
	public String sheet3(HttpSession session, Model model, String num) {
		String name = (String) session.getAttribute("loginid");
		if (num.length() == 1) {
			num += "番";
		} else if (num.length() == 2 && !("番".equals(num.substring(1, 2)))) {
			num += "番";
		} else if (num.length() == 3 && !("番".equals(num.substring(2, 3)))) {
			num += "番";
		} else if (num.length() == 4 && !("番".equals(num.substring(3, 4)))) {
			num += "番";
		}
		jdbcTemplate.update("UPDATE sheetreserve SET yoyakuname = ? WHERE yoyakubango = ?", name, num);
		// 更新処理
		return "redirect:/sheetreserv";
	}

	//削除用(ヒント無し)
	@RequestMapping(path = "/sheetDel", method = RequestMethod.POST)
	public String sheet4(Model model, String num) {
		//番号単体で入力されたときの処理 例)１↓
		if (num.length() == 1) {
			num += "番";
		} else if (num.length() == 2 && !("番".equals(num.substring(1, 2)))) {
			num += "番";
		} else if (num.length() == 3 && !("番".equals(num.substring(2, 3)))) {
			num += "番";
		} else if (num.length() == 4 && !("番".equals(num.substring(3, 4)))) {
			num += "番";
		}
		jdbcTemplate.update("DELETE FROM sheetreserve WHERE yoyakubango = ?", num);

		//削除処理

		return "redirect:/sheetreserv";
	}

	@RequestMapping(path = "/sheetSer", method = RequestMethod.POST)
	public String sheet5(Model model, String name) {
		name = "%" + name + "%";
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM sheetreserve"
				+ " WHERE yoyakuname LIKE ?", name);
		model.addAttribute("kensakupra", kensakukekka);

		//削除処理

		return "mydbseventh";
	}

	@RequestMapping(path = "/sheetmypage", method = RequestMethod.GET)
	public String sheet6(HttpSession session, Model model) {
		String name = (String) session.getAttribute("loginid");
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM sheetreserve WHERE yoyakuname = ?", name);
		List<Map<String, Object>> kensakukekka2 = jdbcTemplate
				.queryForList("SELECT count(yoyakubango)AS count , yoyakuname FROM sheetreserve group by yoyakuname\n"
						+ "order by count desc limit 3");

		//検索結果のリストをmodelに格納

		model.addAttribute("kensakupra", kensakukekka);
		model.addAttribute("count", kensakukekka.size());
		model.addAttribute("max", kensakukekka2);

		//マイページ遷移

		return "mydbeigth";
	}

}
