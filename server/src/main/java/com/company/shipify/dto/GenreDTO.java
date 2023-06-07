package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreDTO {

    private Integer id;
    private String genreName;
}