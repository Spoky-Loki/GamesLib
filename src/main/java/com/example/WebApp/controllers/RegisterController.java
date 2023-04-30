package com.example.WebApp.controllers;

import com.example.WebApp.models.User;
import com.example.WebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/sign_up")
    public String showRegistrationForm(Model model) {
        return "signup_form";
    }

    @GetMapping("/register_success")
    public String register_success(Model model) {
        return "register_success";
    }

    @PostMapping("/sign_up_process")
    public String processRegister(@RequestParam String email, @RequestParam String password,
                                  @RequestParam String username, Model model) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(email, encodedPassword, username);

        userRepo.save(user);

        return "redirect:/register_success";
    }
}
