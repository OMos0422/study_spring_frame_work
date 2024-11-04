package com.example.kouki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class Hukushu1Controller {
    // urlが/userLoginのときにuserLogin.htmlを表示する
    @RequestMapping(path = "/userLogin", method = RequestMethod.GET)
    public String userLogin() {
        return "userlogin";
    }

    // urlが/userLoginのときにuserLogin.htmlを表示する
    @RequestMapping(path = "/userLogin", method = RequestMethod.POST)
    public String userLogin2(Model model, RedirectAttributes redirectAttributes, HttpSession session, String id,
            String pass) {
        if ("userlogin".equals(id) && "userpass".equals(pass) && pass.length() >= 8) {
            session.setAttribute("user", id);
            redirectAttributes.addFlashAttribute("kakunin", "ログイン成功");
            return "redirect:/userHome";
        } else if (!("".equals(id)) && !("".equals(pass)) && pass.length() < 8) {
            model.addAttribute("miss", "パスワードが短い");
            return "userlogin";
        } else {
            model.addAttribute("miss", "IDまたはパスワードが間違っています");
            return "userlogin";
        }
    }

    // urlが/userHomeのときにuserHome.htmlを表示するgetメソッド
    @RequestMapping(path = "/userHome", method = RequestMethod.GET)
    public String userHome(HttpSession session, Model model) {
        String kakunin = (String) model.getAttribute("kakunin");
        model.addAttribute("kakunin", kakunin);
        return "userhome";
    }

    // urlが/userHomeのときにuserHome.htmlを表示するpostメソッド
    @RequestMapping(path = "/userHome", method = RequestMethod.POST)
    public String userHome2(HttpSession session, String message) {

        List<String> messageList;
        if (session.getAttribute("message") == null) {
            messageList = new ArrayList<>();
        } else {
            messageList = (List<String>) session.getAttribute("message");
        }
        if (!("".equals(message))) {
            messageList.add(message);
            session.setAttribute("message", messageList);
            return "userhome";
        } else {
            return "redirect:/userHome";
        }
    }

    // urlが/sakujoのときにsakujo.htmlを表示する
    @RequestMapping(path = "/sakujo", method = RequestMethod.GET)
    public String sakujo(HttpSession session) {
        session.removeAttribute("message");
        return "redirect:/userHome";
    }

    // urlがuserLogoutのpostメソッド
    @RequestMapping(path = "/userLogout", method = RequestMethod.GET)
    public String userLogout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("message");
        return "redirect:/userLogin";
    }

}
