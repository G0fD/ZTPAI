package com.company.shipify.repositories;

import com.company.shipify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findSongsByAlbum(String albumName);

    List<Song> findSongsByAuthor(String authorName);

    List<Song> findSongsByAuthorIsContainingIgnoreCase(String searchString);

    List<Song> findSongsByAlbumIsContainingIgnoreCase(String searchString);

    List<Song> findSongsByTitleIsContainingIgnoreCase(String searchString);
}