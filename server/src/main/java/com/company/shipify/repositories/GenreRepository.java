package com.company.shipify.repositories;

import com.company.shipify.model.Genre;
import com.company.shipify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findBySongsGenre(Song song);
}
