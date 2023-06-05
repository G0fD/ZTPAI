package com.company.shipify.controllers;

import com.company.shipify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        model.addAttribute("testUser", userService.getUserByEmail("other@email.com"));
        return "dev";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", title);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", title);
        return "register";
    }

    @Secured({"user", "admin"})
    @GetMapping("/auth/site")
    public String site(Model model, @RequestParam("token") String token) {
        String jwtToken = token.substring(7);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("title", title);
        return "site";
    }

    @Secured({"user", "admin"})
    @GetMapping("/auth/profile")
    public String profile(Model model) {
        model.addAttribute("title", title);
        return "profile";
    }

    @Secured({"admin"})
    @GetMapping("/auth/profile/add")
    public String addSong(Model model) {
        model.addAttribute("title", title);
        return "profile";
    }

}