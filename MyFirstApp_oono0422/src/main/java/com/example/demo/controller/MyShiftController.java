package com.example.demo.controller;

import java.util.Calendar;
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
public class MyShiftController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*********************↓↓ログイン画面用↓↓**********************/
	@RequestMapping(path = "/shoplogin", method = RequestMethod.GET)
	public String loginget() {

		//画面表示用。処理無し。

		return "myshift1";
	}

	@RequestMapping(path = "/shoplogin", method = RequestMethod.POST)
	public String loginpost(String formdata1, Model model, HttpSession session) {

		//入力された従業員コードが「A01」もしくは「B01」の場合、ログイン成功とする。
		if ("A01".equals(formdata1) || "B01".equals(formdata1)) {

			//ログイン成功の場合、sessionに入力値を格納して、シフトリスト画面に遷移する。
			//			session.setAttribute("自分で決める", ??????);
			session.setAttribute("num", formdata1);
			return "redirect:/shiftlist";
		} else {
			//ログイン失敗の場合、「間違えています」という値をmodelに格納し、ログイン画面に戻る。
			model.addAttribute("miss", "間違えています");
			return "myshift1";
		}
	}

	/*********************↑↑ログイン画面用↑↑**********************/

	/*********************↓↓シフト一覧用↓↓**********************/

	//画面表示用
	@RequestMapping(path = "/shiftlist", method = RequestMethod.GET)
	public String shiftlistget( Model model, HttpSession session) {

		Calendar cal = Calendar.getInstance();
		String week = "";

		switch (cal.get(Calendar.DAY_OF_WEEK)) { 
	    case Calendar.SUNDAY:     // Calendar.SUNDAY:1 
	    	//日曜日
		    week = "日曜日";
		    break;
		case Calendar.MONDAY:     // Calendar.MONDAY:2
		    //月曜日
			week = "月曜日";
		    break;
		case Calendar.TUESDAY:    // Calendar.TUESDAY:3
		    //火曜日
			week = "火曜日";
		    break;
		case Calendar.WEDNESDAY:  // Calendar.WEDNESDAY:4
		    //水曜日
			week =  "水曜日";
		    break;
		case Calendar.THURSDAY:   // Calendar.THURSDAY:5
		    //木曜日
			week =  "木曜日";
		    break;
		case Calendar.FRIDAY:     // Calendar.FRIDAY:6
		    //金曜日 
			week = "金曜日";
		    break;
		case Calendar.SATURDAY:   // Calendar.SATURDAY:7
		    //土曜日
			week =  "土曜日";
		    break;
		default :
			week = "";
	  }
		session.setAttribute("week", week);
		
		String s = (String) session.getAttribute("num");
		//shiftlistテーブルにselectし、結果をmodelに格納
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM shiftlist");
		session.setAttribute("name", s);
		model.addAttribute("shiftlist", kensakukekka);
		return "myshift2";
	}

	//シフト登録用
	@RequestMapping(path = "/shiftlistadd", method = RequestMethod.POST)
	public String shiftpostadd(String code, String day, String start, String end, String money) {
		
		//INSERT文を発行してデータベースに登録をしよう。それだけでOK！
		if (!("".equals(code)) && !("".equals(day)) && !("".equals(start)) && !("".equals(end))
				&& !("".equals(money))) {
			String day1 = day.substring(5, 6);
			int d1 = Integer.parseInt(day.substring(8, 9));
			if ("1".equals(day1) && 1 <= d1 && 4 > d1) {
				String day2 = day.substring(5, 7);
				String day3 = day.substring(8, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO shiftlist VALUES (?,?,?,?,?)", code, day4, start, end, money);
			} else if ("1".equals(day1) && d1 == 0) {
				String day2 = day.substring(5, 7);
				String day3 = day.substring(9, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO shiftlist VALUES (?,?,?,?,?)", code, day4, start, end, money);
			} else if ("0".equals(day1) && d1 == 0) {
				String day2 = day.substring(6, 7);
				String day3 = day.substring(9, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO shiftlist VALUES (?,?,?,?,?)", code, day4, start, end, money);
			} else if ("0".equals(day1) && 1 <= d1 && 4 > d1) {
				String day2 = day.substring(6, 7);
				String day3 = day.substring(8, 10);
				String day4 = "";
				day4 = day2 + "月" + day3 + "日";
				jdbcTemplate.update("INSERT INTO shiftlist VALUES (?,?,?,?,?)", code, day4, start, end, money);
			}
		} else {
			return "redirect:/shiftlist";
		}

		return "redirect:/shiftlist";
	}

	//シフト更新用
	@RequestMapping(path = "/shiftlistupd", method = RequestMethod.POST)
	public String shiftpostupd(String code2, String day2, String money2) {

		//UPDATE文を発行してデータを変更しよう。それだけでOK！
		if (!("".equals(code2)) && !("".equals(day2)) && !("".equals(money2))) {
			String day1 = day2.substring(5, 6);
			int d1 = Integer.parseInt(day2.substring(8, 9));
			if ("1".equals(day1) && 1 <= d1 && 4 > d1) {
				String day3 = day2.substring(5, 7);
				String day4 = day2.substring(8, 10);
				String day5 = "";
				day5 = day3 + "月" + day4 + "日";
				jdbcTemplate.update("UPDATE shiftlist SET jikyu = ? WHERE empname = ? AND workdate = ?", money2, code2,
						day5);
			} else if ("1".equals(day1) && d1 == 0) {
				String day3 = day2.substring(5, 7);
				String day4 = day2.substring(9, 10);
				String day5 = "";
				day5 = day3 + "月" + day4 + "日";
				jdbcTemplate.update("UPDATE shiftlist SET jikyu = ? WHERE empname = ? AND workdate = ?", money2, code2,
						day5);
			} else if ("0".equals(day1) && d1 == 0) {
				String day3 = day2.substring(6, 7);
				String day4 = day2.substring(9, 10);
				String day5 = "";
				day5 = day3 + "月" + day4 + "日";
				jdbcTemplate.update("UPDATE shiftlist SET jikyu = ? WHERE empname = ? AND workdate = ?", money2, code2,
						day5);
			} else if ("0".equals(day1) && 1 <= d1 && 4 > d1) {
				String day3 = day2.substring(6, 7);
				String day4 = day2.substring(8, 10);
				String day5 = "";
				day5 = day3 + "月" + day4 + "日";
				jdbcTemplate.update("UPDATE shiftlist SET jikyu = ? WHERE empname = ? AND workdate = ?", money2, code2,
						day5);
			}
		} else {
			return "redirect:/shiftlist";
		}

		return "redirect:/shiftlist";
	}

	//シフト削除用
	@RequestMapping(path = "/shiftlistdel", method = RequestMethod.POST)
	public String shiftpostdel(String code3, String day3) {

		//DELETE文を発行してデータの削除を行おう。それだけでOK！
		if (!("".equals(code3)) && !("".equals(day3))) {
			String day1 = day3.substring(5, 6);
			int d1 = Integer.parseInt(day3.substring(8, 9));
			if ("1".equals(day1) && 1 <= d1 && 4 > d1) {
				String day4 = day3.substring(5, 7);
				String day5 = day3.substring(8, 10);
				String day6 = "";
				day6 = day4 + "月" + day5 + "日";
				jdbcTemplate.update("DELETE FROM shiftlist WHERE empname = ? AND workdate = ?", code3, day6);
			} else if ("1".equals(day1) && d1 == 0) {
				String day4 = day3.substring(5, 7);
				String day5 = day3.substring(9, 10);
				String day6 = "";
				day6 = day4 + "月" + day5 + "日";
				jdbcTemplate.update("DELETE FROM shiftlist WHERE empname = ? AND workdate = ?", code3, day6);
			} else if ("0".equals(day1) && d1 == 0) {
				String day4 = day3.substring(6, 7);
				String day5 = day3.substring(9, 10);
				String day6 = "";
				day6 = day4 + "月" + day5 + "日";
				jdbcTemplate.update("DELETE FROM shiftlist WHERE empname = ? AND workdate = ?", code3, day6);
			} else if ("0".equals(day1) && 1 <= d1 && 4 > d1) {
				String day4 = day3.substring(6, 7);
				String day5 = day3.substring(8, 10);
				String day6 = "";
				day6 = day4 + "月" + day5 + "日";
				jdbcTemplate.update("DELETE FROM shiftlist WHERE empname = ? AND workdate = ?", code3, day6);
			}
		} else {
			return "redirect:/shiftlist";
		}

		return "redirect:/shiftlist";
	}

	/*********************↑↑シフト一覧用↑↑**********************/
	//給料確認画面表示用↓
	@RequestMapping(path = "/mymoney", method = RequestMethod.GET)
	public String money(Model model, HttpSession session) {
		String s = (String) session.getAttribute("num");
		//shiftlistテーブルにselectし、結果をmodelに格納
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList(
				"SELECT empname,workdate,CAST(endtime - starttime AS char) AS totaltime,CAST((endtime - starttime) * jikyu as char) AS money FROM shiftlist WHERE empname = ?",
				s);
		session.setAttribute("name", s);
		model.addAttribute("shiftlist", kensakukekka);

		return "mymoney";
	}

	//給料トータル画面表示用↓
	@RequestMapping(path = "/mytotal", method = RequestMethod.GET)
	public String total(Model model, HttpSession session) {
		String s = (String) session.getAttribute("num");
		//shiftlistテーブルにselectし、結果をmodelに格納
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList(
				"SELECT empname,CAST((endtime - starttime) * jikyu as char) AS money,\n"
						+ " CASE  WHEN (endtime - starttime) * jikyu > 2500 AND (endtime - starttime) * jikyu <= 50000 THEN \n"
						+ " CAST(FLOOR((endtime - starttime) * jikyu * 0.9) as char) WHEN (endtime - starttime) * jikyu > 50000 AND (endtime - starttime) * jikyu <= 100000 THEN CAST(FLOOR((endtime - starttime) * jikyu * 0.8) AS char) \n"
						+ " WHEN (endtime - starttime) * jikyu > 100000 THEN CAST(FLOOR((endtime - starttime) * jikyu * 0.5) AS char) ELSE CAST((FLOOR(endtime - starttime) * jikyu) AS char) END AS tedori FROM shiftlist WHERE empname = ?;",
				s);
		session.setAttribute("name", s);
		model.addAttribute("shiftlist", kensakukekka);

		return "mymoneytotal";
	}

	//ログアウト用↓
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String moneypost(HttpSession session) {
		session.removeAttribute("name");
		return "redirect:/shoplogin";
	}

}
