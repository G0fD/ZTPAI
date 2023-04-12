package com.company.shipify.services;

import com.company.shipify.model.Song;
import com.company.shipify.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public Collection<Song> getAllSongs(){
        return songRepository.findAll();
    }
}