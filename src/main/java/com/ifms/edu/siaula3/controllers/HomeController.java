package com.ifms.edu.siaula3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model view){
        view.addAttribute("mensagem", "Olá mundo");
        return "index";
    }
}
