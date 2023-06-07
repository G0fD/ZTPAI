package com.company.shipify.services;

import com.company.shipify.model.Song;
import com.company.shipify.repositories.GenreRepository;
import com.company.shipify.repositories.ProviderRepository;
import com.company.shipify.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final ProviderRepository providerRepository;
    private final GenreRepository genreRepository;

    public List<Song> getAll(){
        return songRepository.findAll();
    }
}