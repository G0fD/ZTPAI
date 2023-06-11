package com.company.shipify.services;

import com.company.shipify.dto.*;
import com.company.shipify.model.LikedBy;
import com.company.shipify.model.Song;
import com.company.shipify.model.User;
import com.company.shipify.repositories.LikedByRepository;
import com.company.shipify.repositories.SongRepository;
import com.company.shipify.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final ProviderService providerService;
    private final GenreService genreService;
    private final LikedByRepository likedByRepository;
    private final UserRepository userRepository;

    public List<SongDTO> findSongs(SearchRequest request) {
        List<SongDTO> songs = new ArrayList<>();
        String searchString = request.getSearchString();

        for (Song s : songRepository.findSongsByAuthorIsContainingIgnoreCase(searchString)) {
            SongDTO songDTO = SongDTO.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .author(s.getAuthor())
                    .album(s.getAlbum())
                    .filename(s.getFilename())
                    .genres(genreService.getGenresBySong(s))
                    .providers(providerService.getProvidersBySong(s))
                    .build();
            songs.add(songDTO);
        }

        for (Song s : songRepository.findSongsByAlbumIsContainingIgnoreCase(searchString)) {
            SongDTO songDTO = SongDTO.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .author(s.getAuthor())
                    .album(s.getAlbum())
                    .filename(s.getFilename())
                    .genres(genreService.getGenresBySong(s))
                    .providers(providerService.getProvidersBySong(s))
                    .build();
            songs.add(songDTO);
        }

        for (Song s : songRepository.findSongsByTitleIsContainingIgnoreCase(searchString)) {
            SongDTO songDTO = SongDTO.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .author(s.getAuthor())
                    .album(s.getAlbum())
                    .filename(s.getFilename())
                    .genres(genreService.getGenresBySong(s))
                    .providers(providerService.getProvidersBySong(s))
                    .build();
            songs.add(songDTO);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
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

    public UniversalIntRequest getSongRating(LikedByDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        Song song = songRepository.getReferenceById(request.getSongId());

        if (likedByRepository.existsByLikerAndUserSong(user, song)) {
            return UniversalIntRequest.builder()
                    .integer(likedByRepository.findByLikerAndUserSong(user, song).getRating())
                    .build();
        }

        return UniversalIntRequest.builder()
                .integer(-1)
                .build();

    }

    public UniversalStringRequest addSong(MultipartFile file, SongDTO request) throws IOException {
        String filepath = "/server/src/main/resources/img" + file.getOriginalFilename();
        file.transferTo(new File(filepath));
        if (file.getOriginalFilename().isEmpty()) {
            return null;
        }
        return UniversalStringRequest.builder()
                .string("success")
                .build();
    }
}