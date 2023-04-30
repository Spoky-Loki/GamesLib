package com.example.WebApp.controllers;

import com.example.WebApp.models.Game;
import com.example.WebApp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public String gameMain(Model model){
        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);

        return "gameMain";
    }

}
