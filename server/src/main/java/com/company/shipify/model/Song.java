package com.company.shipify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "songs")
public class Song{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String album;

    @Column
    private String filename;

    @ManyToMany
    @JoinTable(
            name = "song_providers",
            joinColumns = { @JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_provider")}
    )
    Set<Provider> providers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "song_genres",
            joinColumns = { @JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_genre")}
    )
    Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "userSong")
    private Set<LikedBy> userLiked;
}