package com.company.shipify.services;

import com.company.shipify.dto.*;
import com.company.shipify.model.LikedBy;
import com.company.shipify.model.Song;
import com.company.shipify.model.User;
import com.company.shipify.repositories.LikedByRepository;
import com.company.shipify.repositories.SongRepository;
import com.company.shipify.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final ProviderService providerService;
    private final GenreService genreService;
    private final LikedByRepository likedByRepository;
    private final UserRepository userRepository;

    public List<SearchDTO> findSongs(SearchRequest request) {
        List<SearchDTO> songs = new ArrayList<>();
        String searchString = request.getSearchString();

        for (Song s : songRepository.findSongsByAuthorIsContainingIgnoreCase(searchString)) {
            SearchDTO searchDTO = SearchDTO.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .author(s.getAuthor())
                    .album(s.getAlbum())
                    .build();
            songs.add(searchDTO);
        }

        for (Song s : songRepository.findSongsByAlbumIsContainingIgnoreCase(searchString)) {
            SearchDTO searchDTO = SearchDTO.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .author(s.getAuthor())
                    .album(s.getAlbum())
                    .build();
            songs.add(searchDTO);
        }

        for (Song s : songRepository.findSongsByTitleIsContainingIgnoreCase(searchString)) {
            SearchDTO searchDTO = SearchDTO.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .author(s.getAuthor())
                    .album(s.getAlbum())
                    .build();
            songs.add(searchDTO);
        }

        songs = new ArrayList<>(new HashSet<>(songs));
        Collections.sort(songs);
        return songs;
    }

    public SongDTO getSongById(UniversalIntRequest request) {
        Optional<Song> song = songRepository.findById(request.getInteger());

        return SongDTO.builder()
                .id(song.get().getId())
                .title(song.get().getTitle())
                .author(song.get().getAuthor())
                .album(song.get().getAlbum())
                .filename(song.get().getFilename())
                .genres(genreService.getGenresBySong(song.get()))
                .providers(providerService.getProvidersBySong(song.get()))
                .build();
    }

    public String rateSong(LikedByDTO request) {
        User user = userRepository.getReferenceById(request.getUserId());
        Song song = songRepository.getReferenceById(request.getSongId());

        if (!likedByRepository.existsByLikerAndUserSong(user, song)) {
            LikedBy likedBy = LikedBy.builder()
                    .liker(user)
                    .userSong(song)
                    .rating(request.getSongRating())
                    .build();
            likedByRepository.save(likedBy);
        } else {
            LikedBy existingLikedBy = likedByRepository.findByLikerAndUserSong(user, song);
            existingLikedBy.setRating(request.getSongRating());
            likedByRepository.save(existingLikedBy);
        }

        return "success";
    }
}