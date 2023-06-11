package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SongDTO implements Comparable<SongDTO> {

    private Integer id;
    private String title;
    private String author;
    private String album;
    private String filename;
    private List<String> genres;
    private List<String> providers;

    @Override
    public int compareTo(SongDTO otherSongDTO) {
        return Integer.compare(this.id, otherSongDTO.getId());
    }

}