package com.example.kouki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpSession;

@Controller
public class SampleController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/sample", method = RequestMethod.GET)
    public String sample(Model model, HttpSession session) {
        model.addAttribute("message", "こんにちは！");
        session.setAttribute("messages", "こんにちわんこ");
        return "sample";
    }

    @RequestMapping(path = "/sample2", method = RequestMethod.GET)
    public String sample2() {
        return "sample2";
    }

    // ログイン画面
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    // ログイン画面のPOSTメソッド
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, Model model, String name) {
        if ("A01".equals(name)) {
            session.setAttribute("name", name);
            return "redirect:/sample2";
        } else {
            model.addAttribute("miss", "ログインに失敗しました");
            return "login";
        }
    }

    // 一覧画面
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list(HttpSession session, Model model) {
        // jdbcテンプレートを使用してitemテーブルから全件取得
        List<Map<String, Object>> items = jdbcTemplate.queryForList("SELECT * FROM item");
        // 検索結果をモデルに追加
        model.addAttribute("items", items);
        return "list";
    }

}
