package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.praent;
import com.example.demo.repository.PraentRepository;

@Controller
public class PraentContrller {

	@Autowired
	PraentRepository prarepo;

	@RequestMapping(path = "/praent", method = RequestMethod.GET)
	public String third(Model model) {

		List<praent> list = prarepo.findAll();
		model.addAttribute("resultList", list);
		return "praent";
	}

	//条件付き検索
	@RequestMapping(path = "/praentsearch", method = RequestMethod.POST)
	public String fourth(Model model, String keycol, String optcol1) {

		keycol = keycol.replaceAll("　", "");
		keycol = keycol.replaceAll(" ", "");

		optcol1 = optcol1.replaceAll("　", "");
		optcol1 = optcol1.replaceAll("", "");

		keycol = "%" + keycol + "%";
		List<praent> list = prarepo.findByKeycolLikeOrOptcol1(keycol, optcol1);
		if ("".equals(keycol) || "".equals(optcol1) || list.size() == 0) {
			return "redirect:/praent";
		}
		model.addAttribute("resultList", list);
		return "praent";
	}

	//登録・更新
	@RequestMapping(path = "/praentinsupd", method = RequestMethod.POST)
	public String fifth(Model model, String keycol, String optcol1, String optcol2) {

		praent praent = new praent();

		praent.setKeycol(keycol);
		praent.setOptcol1(optcol1);
		praent.setOptcol2(optcol2);

		prarepo.save(praent);

		return "redirect:/praent";
	}

	//削除
	@RequestMapping(path = "/praentdel", method = RequestMethod.POST)
	public String sixth(Model model, String keycol) {

		praent praent = new praent();

		praent.setKeycol(keycol);

		prarepo.delete(praent);

		return "redirect:/praent";
	}
}