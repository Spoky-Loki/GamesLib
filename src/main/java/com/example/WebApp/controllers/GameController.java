package com.example.WebApp.controllers;

import com.example.WebApp.models.Game;
import com.example.WebApp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/games/{id}")
    public String gameDetail(@PathVariable(value = "id") long id, Model model){
        if (!gameRepository.existsById(id)) {
            return "redirect:/games";
        }

        Optional<Game> game = gameRepository.findAllById(id);
        ArrayList<Game> res = new ArrayList<>();
        game.ifPresent(res::add);
        model.addAttribute("game", res);

        return "game_detail";
    }
}
