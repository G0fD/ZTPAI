package com.company.shipify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaulController {
    private final String title = "Test page title";

    @GetMapping("/welcome")
    public String welcome(Model model,
                          @RequestParam(value = "username",
                          required = false,
                          defaultValue = "Guest") String username){
        model.addAttribute("username", username);
        model.addAttribute("title", title);
        return "welcome";
    }

}
