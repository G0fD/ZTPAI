package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchDTO implements Comparable<SearchDTO> {
    private Integer id;
    private String title;
    private String author;
    private String album;

    @Override
    public int compareTo(SearchDTO otherSearchDTO) {
        return Integer.compare(this.id, otherSearchDTO.getId());
    }
}
