package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.shiftlist;
import com.example.demo.entity.shiftlistcustom;
import com.example.demo.repository.ShiftListCustomRepository;
import com.example.demo.repository.ShiftListRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyShiftController2 {

	@Autowired
	ShiftListRepository shiftrepo;
	@Autowired
	ShiftListCustomRepository shiftlistcustom;

	//画面表示用
	@RequestMapping(path = "/renshiftlist", method = RequestMethod.GET)
	public String shiftlistget(Model model, HttpSession session) {

		List<shiftlist> list = shiftrepo.findAll();
		model.addAttribute("shiftlist", list);
		return "myshift3";
	}

	@RequestMapping(path = "/renshiftsearch", method = RequestMethod.POST)
	public String shiftlistcustompost2(Model model, HttpSession session, String j) {

		List<shiftlistcustom> list = shiftlistcustom.findAll();
		List<shiftlist> list2 = null;

		String jouken = "";
		if (list.size() == 0) {
			session.removeAttribute("li");
			return "redirect:/renshiftlist";
		} else if (list.size() >= 2) {
			jouken = (String) list.get(list.size() - 1).getCustomjouken();
		} else {
			jouken = (String) list.get(0).getCustomjouken();
		}

		if ("empname".equals(jouken)) {
			list2 = shiftrepo.findByEmpname(j);
		} else if ("workdate".equals(jouken)) {
			list2 = shiftrepo.findByWorkdate(j);
		} else if ("starttime".equals(jouken)) {
			list2 = shiftrepo.findByStarttime(j);
		} else if ("endtime".equals(jouken)) {
			list2 = shiftrepo.findByEndtime(j);
		} else if ("jikyu".equals(jouken)) {
			list2 = shiftrepo.findByJikyu(j);
		} else {
			return "redirect:/renshiftlist";
		}

		if (list2.size() == 0) {
			return "redirect:/renshiftlist";
		}

		model.addAttribute("shiftlist", list2);

		return "myshift3";
	}

	//画面表示用
	@RequestMapping(path = "/renshiftlistcustom", method = RequestMethod.GET)
	public String shiftliststcustom(HttpSession session) {
		List<shiftlistcustom> list = shiftlistcustom.findAll();
		if (list.size() != 0) {
			shiftlistcustom.deleteAll();
			session.removeAttribute("column");
			session.removeAttribute("li");
		}
		return "myshift4";
	}

	@RequestMapping(path = "/renshiftlistcustom", method = RequestMethod.POST)
	public String shiftlistcustompost(HttpSession session, String custom) {
		List<shiftlistcustom> list = null;

		if ("empname".equals(custom) || "workdate".equals(custom) || "starttime".equals(custom)
				|| "endtime".equals(custom) || "jikyu".equals(custom)) {
			shiftlistcustom shift = new shiftlistcustom();
			shift.setCustomjouken(custom);
			shiftlistcustom.save(shift);
			list = shiftlistcustom.findAll();
			session.setAttribute("li", list.size());
			session.setAttribute("column", custom);
		}
		return "myshift4";
	}

	//シフト更新用
	@RequestMapping(path = "/renshiftaddupd", method = RequestMethod.POST)
	public String shiftpostupd(String code, String date, String start, String end, String money) {

		shiftlist shift = new shiftlist();

		shift.setEmpname(code);
		shift.setWorkdate(date);
		shift.setStarttime(start);
		shift.setEndtime(end);
		shift.setJikyu(money);

		shiftrepo.save(shift);

		return "redirect:/renshiftlist";
	}

	//シフト削除用
	@RequestMapping(path = "/renshiftdel", method = RequestMethod.POST)
	public String shiftpostdel(String code3, String date3) {

		shiftlist shift = new shiftlist();

		shift.setEmpname(code3);
		shift.setWorkdate(date3);

		shiftrepo.delete(shift);

		return "redirect:/renshiftlist";
	}

}