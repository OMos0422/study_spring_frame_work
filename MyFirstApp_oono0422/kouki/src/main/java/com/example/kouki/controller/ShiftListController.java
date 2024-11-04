package com.example.kouki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import com.example.kouki.entity.shiftlist;
import com.example.kouki.repository.ShiftListRepository;
import org.springframework.ui.Model;

@Controller
public class ShiftListController {
    @Autowired
    ShiftListRepository repos;

    @RequestMapping(path = "/koukihuku6", method = RequestMethod.GET)
    public String sheetget(Model model) {

        List<shiftlist> shift = repos.findAll();
        model.addAttribute("shiftlst", shift);
        return "shiftlist";
    }

}
