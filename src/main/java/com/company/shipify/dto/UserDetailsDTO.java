package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDTO {

    private Integer id;
    private String email;
    private String name;
    private String surname;
}