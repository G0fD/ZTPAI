package com.company.shipify.services;

import com.company.shipify.model.Genre;
import com.company.shipify.model.Song;
import com.company.shipify.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<String> getGenresBySong(Song song) {
        List<String> genreNames = new ArrayList<>();
        for (Genre g : genreRepository.findBySongsGenre(song)) {
            genreNames.add(g.getName());
        }
        return genreNames;
    }
}
