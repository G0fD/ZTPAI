package com.company.shipify.controllers;


import com.company.shipify.dto.MatchesDTO;
import com.company.shipify.dto.ProfileDTO;
import com.company.shipify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ProfileContentController {
    private final UserService userService;

    @GetMapping("/myProfile")
    public ResponseEntity<ProfileDTO> myProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

    @GetMapping("/myProfile/matches/{id}")
    public ResponseEntity<List<MatchesDTO>> myMatches(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(userService.getMatches());
    }

}
