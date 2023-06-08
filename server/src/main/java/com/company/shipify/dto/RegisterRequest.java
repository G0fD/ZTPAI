package com.company.shipify.dto;

import com.company.shipify.model.Genders;
import com.company.shipify.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String name;
    private String interested;
    private String surname;
    private Genders gender;
    private Roles role;
}