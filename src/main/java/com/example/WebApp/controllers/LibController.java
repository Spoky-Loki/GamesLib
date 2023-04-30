package com.example.WebApp.controllers;

import com.example.WebApp.models.Game;
import com.example.WebApp.models.Lib;
import com.example.WebApp.models.User;
import com.example.WebApp.repository.GameRepository;
import com.example.WebApp.repository.LibRepository;
import com.example.WebApp.repository.UserRepository;
import com.example.WebApp.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class LibController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LibRepository libRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/myLib")
    public String about(Model model) {
        model.addAttribute("title", "Библиотека");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByEmail(username);
        long user_id = user.getId();

        ArrayList<Lib> lib = libRepository.findAllByUserId(user_id);

        ArrayList<Game> games = new ArrayList<Game>();
        for (var el : lib){
            Optional<Game> game = gameRepository.findAllById(el.getGame_id());
            game.ifPresent(games::add);
        }
        model.addAttribute("games", games);

        return "my_lib";
    }

    @PostMapping("/addToLib/{id}")
    public String addToLib(@PathVariable(value = "id") long id, Model model){
        if (!gameRepository.existsById(id)) {
            return "redirect:/games";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByEmail(username);

        ArrayList<Lib> lib = libRepository.findAllByUserId(user.getId());
        for (var el : lib){
            if (el.getGame_id() == id) {
                return "redirect:/games";
            }
        }
        Lib ex = new Lib(user.getId(), id);
        libRepository.save(ex);

        return "redirect:/myLib";
    }

    @PostMapping("/removeFromLib/{id}")
    public String removeFromLib(@PathVariable(value = "id") long id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByEmail(username);

        ArrayList<Lib> lib = libRepository.findAllByUserId(user.getId());
        for (var el : lib){
            if (el.getGame_id() == id) {
                libRepository.delete(el);
            }
        }

        return "redirect:/myLib";
    }
}
