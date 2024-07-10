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
public class MyDbSeventh2Controller {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//ログイン画面表示
	@RequestMapping(path = "/sheetlogin", method = RequestMethod.GET)
	public String sheet1(Model model) {

		//検索処理

		return "mydbseventh";
	}

	//ログイン
	@RequestMapping(path = "/sheetlogin", method = RequestMethod.POST)
	public String sheet11(HttpSession session, String id, String pass) {
		//IDとパスワードを受け取り、ログイン画面へ移行
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM sheetuser WHERE userid = ? AND userpass = ?", id, pass);
		if (kensakukekka.size() == 0) {
			return "mydbseventh";
		} else if ("admin".equals(id) && "adminpass".equals(pass)) {
			return "redirect:/sheetadmin";
		} else {
			session.setAttribute("loginid", id);
			return "redirect:/sheetreserv";
		}

		/**returnの後ろのhtml名以外は変更の必要無し**/

	}

	@RequestMapping(path = "/sheetadmin", method = RequestMethod.GET)
	public String sheet15(Model model, HttpSession session, String id, String pass) {
		//IDとパスワードを受け取り、ログイン画面へ移行
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList(
						"SELECT sheetreserve.yoyakuname,sheetreserve.yoyakubango,sheetreserve.yoyakubi, sheetuser.userpass\n"
								+ "FROM sheetreserve\n"
								+ "LEFT JOIN sheetuser\n"
								+ "ON sheetreserve.yoyakuname = sheetuser.userid;");
		model.addAttribute("kensakupra", kensakukekka);
		return "mydbnineth";
		/**returnの後ろのhtml名以外は変更の必要無し**/

	}

	@RequestMapping(path = "/sheetadminUpd", method = RequestMethod.POST)
	public String sheet16(Model model, HttpSession session,  String id2, String pass2) {
		//IDとパスワードを受け取り、ログイン画面へ移行
		jdbcTemplate.update("UPDATE sheetuser SET userpass = ? WHERE userid = ?", pass2, id2);
		return "redirect:/sheetadmin";
		/**returnの後ろのhtml名以外は変更の必要無し**/

	}

	@RequestMapping(path = "/sheetadminDel", method = RequestMethod.POST)
	public String sheet17(Model model, HttpSession session, String id) {
		//IDとパスワードを受け取り、ログイン画面へ移行
		jdbcTemplate.update("DELETE FROM sheetuser WHERE userid = ?", id);
		return "redirect:/sheetadmin";
		/**returnの後ろのhtml名以外は変更の必要無し**/

	}

	@RequestMapping(path = "/loginIns", method = RequestMethod.POST)
	public String sheet12(HttpSession session, Model model, String id, String pass) {
		//IDとパスワードが等しい時にアラートを発生させる↓
		if (id.equals(pass)) {
			//空白の場合
			if ("".equals(id) && "".equals(pass)) {
				model.addAttribute("id", "空白");
				return "mydbseventh";
			} else {
				model.addAttribute("id", "id");
				return "mydbseventh";
			}
		} else if ("".equals(id) || "".equals(pass)) {
			//どちらか一方が空白の場合
			model.addAttribute("id", "空白");
			return "mydbseventh";
		} else {
			//IDとパスワードが等しくなく、空白でもない場合↓
			List<Map<String, Object>> kensakukekka = jdbcTemplate
					.queryForList("SELECT * FROM sheetuser WHERE userid = ? AND userpass = ?", id, pass);
			if (kensakukekka.size() == 0 && id != null && pass != null) {
				jdbcTemplate.update("INSERT INTO sheetuser VALUES(?,?)", id, pass);
				return "redirect:/sheetlogin";
			} else {
				return "redirect:/sheetlogin";
			}
		}
	}

	@RequestMapping(path = "/loginDel", method = RequestMethod.POST)
	public String sheet13(HttpSession session, String id, String pass) {

		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM sheetuser WHERE userid = ? AND userpass = ?", id, pass);
		if (kensakukekka.size() == 0) {
			return "redirect:/sheetlogin";
		} else if (kensakukekka.size() != 0 && id != null && pass != null) {
			jdbcTemplate.update("DELETE FROM sheetuser WHERE userid = ? AND userpass = ?", id, pass);
			return "redirect:/sheetlogin";
		}
		return "redirect:/sheetlogin";
	}

	@RequestMapping(path = "/passUpd", method = RequestMethod.POST)
	public String sheet5(Model model, String pass2, String id, String pass) {
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM sheetuser WHERE userid = ? AND userpass = ?", id, pass);
		//パスワードを変更するときの処理↓
		if (kensakukekka.size() != 0) {
			jdbcTemplate.update("UPDATE sheetuser SET userpass = ? WHERE userid = ? AND userpass = ?", pass2, id, pass);
		} else {
			return "redirect:/sheetlogin";
		}

		//パスワード変更処理

		return "mydbseventh";
	}

}