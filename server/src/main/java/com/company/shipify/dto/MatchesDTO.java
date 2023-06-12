package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchesDTO {
    private String name;
    private String surname;
    private String email;
    private String gender;
}
