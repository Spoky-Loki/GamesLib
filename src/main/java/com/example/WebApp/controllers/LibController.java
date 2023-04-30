package com.example.WebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibController {

    @GetMapping("/myLib")
    public String about(Model model) {
        model.addAttribute("title", "Библиотека");

        return "my_lib";
    }
}
