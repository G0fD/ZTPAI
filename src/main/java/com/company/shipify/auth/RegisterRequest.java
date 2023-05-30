package com.company.shipify.auth;

import com.company.shipify.dto.GenderDTO;
import com.company.shipify.dto.UserDTO;
import com.company.shipify.dto.UserDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

//    private UserDTO userDTO;
//    private UserDetailsDTO detailsDTO;
//    private GenderDTO genderDTO;

    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
}