package com.company.shipify.controllers;


import com.company.shipify.dto.MatchesDTO;
import com.company.shipify.dto.ProfileDTO;
import com.company.shipify.dto.SongDTO;
import com.company.shipify.dto.UniversalIntRequest;
import com.company.shipify.services.SongService;
import com.company.shipify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ProfileContentController {
    private final UserService userService;
    private final SongService songService;

    @GetMapping("/myProfile")
    public ResponseEntity<ProfileDTO> myProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

    @GetMapping("/myProfile/matches")
    public ResponseEntity<List<MatchesDTO>> myMatches() {
        return ResponseEntity.ok(userService.getMatches());
    }

    @PostMapping("/addSong")
    public ResponseEntity<UniversalIntRequest> addNewSong(@RequestBody MultipartFile file, SongDTO request) throws IOException {
        return ResponseEntity.ok(songService.addSong(request, file));
    }

    @GetMapping("/getImage/{filename}")
    public ResponseEntity<byte[]> getSongImage(@PathVariable("filename") String filename) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(songService.getImage(filename));
    }

    @GetMapping("/getSongImage/{id}")
    public ResponseEntity<byte[]> getSongImageById(@PathVariable("id") Integer id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(songService.getSongImageById(id));
    }
}
