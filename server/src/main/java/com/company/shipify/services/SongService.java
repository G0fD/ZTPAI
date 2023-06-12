package com.company.shipify.services;

import com.company.shipify.dto.LikedByDTO;
import com.company.shipify.dto.SearchRequest;
import com.company.shipify.dto.SongDTO;
import com.company.shipify.dto.UniversalIntRequest;
import com.company.shipify.model.*;
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
import java.nio.file.Files;
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

    public UniversalIntRequest addSong(SongDTO request, MultipartFile file) throws IOException {
        String filepath = "C:\\ZTPAI\\shipify\\server\\src\\main\\resources\\img\\" + file.getOriginalFilename();
        file.transferTo(new File(filepath));


        if (file.getOriginalFilename().isEmpty()) {
            return UniversalIntRequest.builder()
                    .integer(0)
                    .build();
        }

        Set<Provider> providers = new HashSet<>();
        Set<Genre> genres = new HashSet<>();

        for (String s : request.getProviders()) {
            Provider provider = providerService.getProviderById(Integer.parseInt(s));
            providers.add(provider);
        }
        for (String s : request.getGenres()) {
            Genre genre = genreService.getGenreById(Integer.parseInt(s));
            genres.add(genre);
        }

        Song song = Song.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .album(request.getAlbum())
                .filename(file.getOriginalFilename())
                .providers(providers)
                .genres(genres)
                .build();

        songRepository.save(song);

        return UniversalIntRequest.builder()
                .integer(1)
                .build();
    }

    public byte[] getImage(String filename) throws IOException {
        String filePath = "C:\\ZTPAI\\shipify\\server\\src\\main\\resources\\img\\";
        return Files.readAllBytes(new File(filePath + filename).toPath());
    }

    public byte[] getSongImageById(Integer id) throws IOException {
        String filePath = "C:\\ZTPAI\\shipify\\server\\src\\main\\resources\\img\\";
        Song song = songRepository.getReferenceById(id);
        return Files.readAllBytes(new File(filePath + song.getFilename()).toPath());
    }
}