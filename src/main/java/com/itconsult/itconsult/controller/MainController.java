package com.itconsult.itconsult.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showNotebooks(Model model) {
        return "home";
    }

    @GetMapping("secure")
    public String securedPage() {
        return "secure";
    }
}
