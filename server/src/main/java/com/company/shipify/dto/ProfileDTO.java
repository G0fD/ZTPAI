package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProfileDTO {
    private Integer userId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String gender;
    private List<String> preferences;
}
