package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikedByDTO {

    private Integer userId;
    private Integer songId;
    private Integer songRating;

}