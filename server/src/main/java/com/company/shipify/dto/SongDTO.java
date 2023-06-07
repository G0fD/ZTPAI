package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongDTO {

    private Integer id;
    private String title;
    private String author;
    private String album;
    private String filename;
}