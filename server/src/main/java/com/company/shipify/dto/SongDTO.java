package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SongDTO {

    private Integer id;
    private String title;
    private String author;
    private String album;
    private String filename;
    private List<String> genres;
    private List<String> providers;
}