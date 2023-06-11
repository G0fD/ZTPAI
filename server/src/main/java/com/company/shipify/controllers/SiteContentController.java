package com.company.shipify.controllers;

import com.company.shipify.dto.LikedByDTO;
import com.company.shipify.dto.SearchRequest;
import com.company.shipify.dto.SongDTO;
import com.company.shipify.dto.UniversalIntRequest;
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
    public ResponseEntity<List<SongDTO>> findSong(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(songService.findSongs(request));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<SongDTO> anySong(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(songService.getSongById(UniversalIntRequest.builder().integer(id).build()));
    }

    @PutMapping("/rate")
    public ResponseEntity<String> rateSong(@RequestBody LikedByDTO request) {
        return ResponseEntity.ok(songService.rateSong(request));
    }

    @PostMapping("/rate/get")
    public ResponseEntity<UniversalIntRequest> getSongRate(@RequestBody LikedByDTO request) {
        return ResponseEntity.ok(songService.getSongRating(request));
    }
}
