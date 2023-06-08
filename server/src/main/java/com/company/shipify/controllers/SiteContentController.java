package com.company.shipify.controllers;

import com.company.shipify.dto.*;
import com.company.shipify.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
@RequiredArgsConstructor
public class SiteContentController {

    private final SongService songService;

    @GetMapping("/first")
    public ResponseEntity<SongDTO> firstSong() {
        return ResponseEntity.ok(songService.getSongById(UniversalIntRequest.builder().integer(1).build()));
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchDTO>> findSong(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(songService.findSongs(request));
    }

    @GetMapping("/search/result")
    @RequestMapping(value = "/{id}")
    public ResponseEntity<SongDTO> anySong(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(songService.getSongById(UniversalIntRequest.builder().integer(id).build()));
    }

    @PostMapping("/rate")
    public ResponseEntity<String> rateSong(@RequestBody LikedByDTO request) {
        return ResponseEntity.ok(songService.rateSong(request));
    }
}
