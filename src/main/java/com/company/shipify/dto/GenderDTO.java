package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenderDTO {

    private Integer id;
    private String genderName;
}