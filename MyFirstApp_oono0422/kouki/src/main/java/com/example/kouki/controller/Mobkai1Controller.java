package com.example.kouki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

@Controller
public class Mobkai1Controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 画面A表示用
    @RequestMapping(path = "/mobkai1", method = RequestMethod.GET)
    public String mob() {
        return "mobkai1";
    }

    // 画面B表示用(検索処理)
    @RequestMapping(path = "/mobkai1", method = RequestMethod.POST)
    public String mobpost(Model model, HttpSession session, String mob) {
        String name = "%" + mob + "%";
        List<Map<String, Object>> mobkai = jdbcTemplate.queryForList("SELECT * FROM mobkai WHERE member like ?",
                name);
        if ("".equals(mob) || mobkai.size() == 0) {
            return "redirect:/mobkai1";
        } else {
            if (mobkai.size() < 2) {
                model.addAttribute("litle", "めっちゃ少ない");
            }
            model.addAttribute("mobkai", mobkai);
            return "mobkai1";
        }
    }

}
