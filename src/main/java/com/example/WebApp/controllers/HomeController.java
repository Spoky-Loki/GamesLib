package com.example.WebApp.controllers;

import com.example.WebApp.models.Game;
import com.example.WebApp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Console;

@Controller
public class HomeController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");

        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Страница о нас");

        return "about";
    }

    @GetMapping("/privacy")
    public String privacy(Model model) {
        model.addAttribute("title", "Страница Конфиденциальности");

        return "privacy";
    }
}
