package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.sheetreserve;
import com.example.demo.repository.SheetReserveRepository;

@Controller
public class SheetReserveController {

	@Autowired
	SheetReserveRepository sheetReserveRepository;

	@RequestMapping(path = "/sheetreserve", method = RequestMethod.GET)
	public String third(Model model) {

		List<sheetreserve> list = sheetReserveRepository.findAll();

		model.addAttribute("resultList", list);
		return "sheetreserve";
	}

	//条件付き検索
	@RequestMapping(path = "/sheetreservesearch", method = RequestMethod.POST)
	public String fourth(Model model, String yoyakubango, String yoyakubi) {

		yoyakubango = yoyakubango.replaceAll(" ", "");
		yoyakubango = yoyakubango.replaceAll("　", "");

		yoyakubi = yoyakubi.replaceAll(" ", "");
		yoyakubi = yoyakubi.replaceAll("　", "");

		yoyakubango = "%" + yoyakubango + "%";
		List<sheetreserve> list = sheetReserveRepository.findByYoyakubangoLikeAndYoyakubi(yoyakubango, yoyakubi);

		if (list.size() == 0) {
			return "redirect:/sheetreserve";
		}

		model.addAttribute("resultList", list);

		return "sheetreserve";

	}

	//登録・更新
	@RequestMapping(path = "/sheetreserveinsupd", method = RequestMethod.POST)
	public String fifth(Model model, String yoyakubango, String yoyakubi, String yoyakuname) {

		sheetreserve entity = new sheetreserve();

		yoyakubango = yoyakubango.replaceAll(" ", "");
		yoyakubango = yoyakubango.replaceAll("　", "");

		yoyakubi = yoyakubi.replaceAll(" ", "");
		yoyakubi = yoyakubi.replaceAll("　", "");

		yoyakuname = yoyakuname.replaceAll(" ", "");
		yoyakuname = yoyakuname.replaceAll("　", "");

		entity.setYoyakubango(yoyakubango);
		entity.setYoyakubi(yoyakubi);
		entity.setYoyakuname(yoyakuname);

		sheetReserveRepository.save(entity);

		return "redirect:/sheetreserve";
	}

	//削除
	@RequestMapping(path = "/sheetreservedel", method = RequestMethod.POST)
	public String sixth(Model model, String yoyakubango, String yoyakuname, String yoyakubi) {

		sheetreserve entity = new sheetreserve();

		entity.setYoyakubango(yoyakubango);
		entity.setYoyakubi(yoyakubi);
		entity.setYoyakuname(yoyakuname);

		sheetReserveRepository.delete(entity);

		return "redirect:/sheetreserve";
	}

}