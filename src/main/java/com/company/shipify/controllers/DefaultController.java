package com.company.shipify.controllers;

import com.company.shipify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DefaultController {
    private final String title = "SHIPify";
    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome(Model model,
                          @RequestParam(value = "username",
                                  required = false,
                                  defaultValue = "Guest") String username){
        model.addAttribute("username", username);
        model.addAttribute("title", title);
        return "welcome";
    }

    @GetMapping("/dev")
    public String dev(Model model){
        model.addAttribute("title", title);
        model.addAttribute("users", userService.getAllUsers());
        return "dev";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", title);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title", title);
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("title", title);
        return "profile";
    }

    @GetMapping("/site")
    public String site(Model model){
        model.addAttribute("title", title);
        return "site";
    }

}