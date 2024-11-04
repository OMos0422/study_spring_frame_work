package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class MySampleController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/kanri", method = RequestMethod.GET)
	public String dbfg(Model model) {

		return "mysample";
	}

	@RequestMapping(path = "/kanri", method = RequestMethod.POST)
	public String dbfg0(HttpSession session, Model model, String id, String pass) {

		//ログインIDがuser1かつパスワードがpass1の場合↓
		if ("user1".equals(id) && "pass1".equals(pass)) {
			session.setAttribute("id", id);
			return "redirect:/kanri2";
		} else {
			model.addAttribute("miss", "ログインに失敗しました！");
		}

		return "mysample";
	}

	@RequestMapping(path = "/kanri2", method = RequestMethod.GET)
	public String dbfg2(HttpSession session, Model model) {
		String check = (String) model.getAttribute("ok");

		Calendar cal = Calendar.getInstance();

		String week = "";

		//2年後期用↓
		List<String> list = new ArrayList<>();

		List<String> list2 = new ArrayList<>();
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY: // Calendar.SUNDAY:1 
			//日曜日
			week = "日曜日";
			break;
		case Calendar.MONDAY: // Calendar.MONDAY:2
			//月曜日
			week = "月曜日";
			list.add("就職講座Ⅱ");
			list.add("JavaScript応用演習Ⅰ");
			list.add("データベース応用");

			list2.add("シラバス");
			list2.add("スッキリわかるSQL入門");
			list2.add("CAB/GABのテキスト");
			break;
		case Calendar.TUESDAY: // Calendar.TUESDAY:3
			//火曜日
			week = "火曜日";
			list.add("Javaフレームワーク");
			list.add("スポーツ実習Ⅱ");
			list.add("Javaフレームワーク");

			list2.add("シラバス");
			list2.add("ゼッケン");
			break;
		case Calendar.WEDNESDAY: // Calendar.WEDNESDAY:4
			//水曜日
			week = "水曜日";
			list.add("進級制作");
			list.add("クラウド演習");

			list2.add("シラバスのみ");
			model.addAttribute("list2", list2);
			break;
		case Calendar.THURSDAY: // Calendar.THURSDAY:5
			//木曜日
			week = "木曜日";
			list.add("システム設計Ⅱ");
			list.add("進級制作");

			list2.add("シラバスのみ");
			break;
		case Calendar.FRIDAY: // Calendar.FRIDAY:6
			//金曜日 
			week = "金曜日";
			list.add("就職講座Ⅰ");
			list.add("進級制作");
			list.add("C言語演習");

			list2.add("シラバス");
			list2.add("スーツ、ネクタイ、革靴");
			list2.add("就職テキスト");
			list2.add("入門ANSI-C");
			break;
		case Calendar.SATURDAY: // Calendar.SATURDAY:7
			//土曜日
			week = "土曜日";
			break;
		default:
			week = "";
		}
		session.setAttribute("week", week);
		if (check != null) {
			model.addAttribute("ok", check);
		}
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);

		//検索処理
		return "mysample2";
	}

	@RequestMapping(path = "/check", method = RequestMethod.POST)
	public String dbfg4(HttpSession session, RedirectAttributes redirectAttributes, String need) {
		String week = (String) session.getAttribute("week");
		if ("on,on,on,on".equals(need) && "金曜日".equals(week)) {
			redirectAttributes.addFlashAttribute("ok", "チェックが完了しました");
		} else if ("on,on,on".equals(need) && "月曜日".equals(week)) {
			redirectAttributes.addFlashAttribute("ok", "チェックが完了しました");
		} else if ("on,on".equals(need) && "火曜日".equals(week)) {
			redirectAttributes.addFlashAttribute("ok", "チェックが完了しました");
		} else if ("on".equals(need) && "水曜日".equals(week) || "on".equals(need) && "木曜日".equals(week)) {
			redirectAttributes.addFlashAttribute("ok", "チェックが完了しました");
		} else {
			redirectAttributes.addFlashAttribute("no", "チェックのし忘れがあります");
		}
		return "redirect:/kanri2";
	}

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/logouts", method = RequestMethod.GET)
	public String dbfg3(HttpSession session, Model model) {
		session.removeAttribute("id");
		return "redirect:/kanri";
	}

}