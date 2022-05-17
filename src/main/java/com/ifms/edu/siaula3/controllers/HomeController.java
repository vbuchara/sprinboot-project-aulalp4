package com.ifms.edu.siaula3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController{

    @RequestMapping("/")
    public String index(Model view){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String home(Model view){
        view.addAttribute("title", "Home");

        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model view){

        return "login";
    }
}
