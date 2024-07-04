package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyEleventhController {

	@RequestMapping(path = "/elevenpageone", method = RequestMethod.GET)
	public String elevenget(Model model, HttpSession session) {
		//モデルにデータを格納
		model.addAttribute("modelData", "→モデルにしまったデータです←");

		//sessionにデータを格納
		session.setAttribute("sessionData", "→sessionにしまったデータです←");
		session.setAttribute("sessionData2", "練習①クリア");

		return "myeleventhone";
	}

	@RequestMapping(path = "/elevenpageone", method = RequestMethod.POST)
	public String elevenpost(HttpSession session, String id, String pass) {

		int x = 0;
		String[] array = new String[4];

		if (x == 0 && "elevenpass".equals(pass) && array[0] == null) {
			array[0] = id;
			array[3] = Integer.toString(++x);
			session.setAttribute("count", array[3]);
			session.setAttribute("userDataId", array[0]);
			return "myeleventhtone";
		} else if (x == 1 && "elevenpass".equals(pass) && array[0] != null && array[1] == null) {
			array[1] = id;
			array[3] = Integer.toString(++x);
			session.setAttribute("count", array[3]);
			session.setAttribute("userDataId", array[0]);
			session.setAttribute("userDataId2", array[1]);
			return "myeleventhone";
		} else if (x == 2 && "elevenpass".equals(pass) && array[0] != null && array[1] != null
				&& array[2] != null) {
			array[2] = id;
			array[3] = Integer.toString(++x);
			session.setAttribute("count", array[3]);
			session.setAttribute("userDataId", array[0]);
			session.setAttribute("userDataId2", array[1]);
			session.setAttribute("userDataId3", array[2]);
			return "myeleventhone";
		} else if (x >= 3 && "elevenpass".equals(pass) && array[0] != null && array[1] != null
				&& array[2] != null) {
			array[0] = array[1];
			array[1] = array[2];
			array[2] = id;
			array[3] = Integer.toString(++x);
			session.setAttribute("count", array[3]);
			session.setAttribute("userDataId", array[0]);
			session.setAttribute("userDataId2", array[1]);
			session.setAttribute("userDataId3", array[2]);
			return "myeleventhtwo";
		} else {
			return "myeleventhone";
		}
	}

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/elevenpagetwo", method = RequestMethod.GET)
	public String elevenget2(HttpSession session) {

		//セッションからデータを取り出して適当な文字列をくっつける。
		String cnt = (String) session.getAttribute("count");
		int count = Integer.parseInt(cnt);
		String user = (String) session.getAttribute("userDataId");
		String user1 = (String) session.getAttribute("userDataId2");
		String user2 = (String) session.getAttribute("userDataId3");
		String x1 = (String) session.getAttribute("sessionData");
		String x2 = (String) session.getAttribute("sessionData2");
		x1 = x1 + "だよだよ";
		x2 = x2 + "練習②もクリア";

		//sessionにデータを格納
		session.setAttribute("user", user);
		session.setAttribute("user1", user1);
		session.setAttribute("user2", user2);
		session.setAttribute("count", count);
		session.setAttribute("sessionData", x1);
		session.setAttribute("sessionData2", x2);
		return "myeleventhtwo";
	}

	@RequestMapping(path = "/elevenpagethree", method = RequestMethod.GET)
	public String elevenget3(HttpSession session) {

		session.setAttribute("sessionData3", "練習③もクリアだよおお");

		return "myelevenththree";
	}

}