package com.company.shipify.repositories;

import com.company.shipify.model.LikedBy;
import com.company.shipify.model.Song;
import com.company.shipify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedByRepository extends JpaRepository<LikedBy, Integer> {
    boolean existsByLikerAndUserSong(User userLike, Song userSong);

    LikedBy findByLikerAndUserSong(User liker, Song userSong);
}
