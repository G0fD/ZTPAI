package com.company.shipify.repositories;

import com.company.shipify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {
    List<Song> findSongsByAlbum(String albumName);
    List<Song> findSongsByAuthor(String authorName);
}