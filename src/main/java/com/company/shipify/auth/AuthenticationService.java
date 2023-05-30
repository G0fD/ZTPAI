package com.company.shipify.auth;

import com.company.shipify.dto.RoleDTO;
import com.company.shipify.model.Gender;
import com.company.shipify.model.MyUserDetails;
import com.company.shipify.model.Role;
import com.company.shipify.model.User;
import com.company.shipify.repositories.RoleRepository;
import com.company.shipify.repositories.UserDetailsRepository;
import com.company.shipify.repositories.UserRepository;
import com.company.shipify.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final UserDetailsRepository details;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var userDetail = MyUserDetails.builder()
                .email(request.getEmail())
                .name(request.getName())
                .surname(request.getSurname())
                .gender(Gender.builder().name("male").id(1).build())    //fixed values
                .preferences(
                        Set.of(Gender.builder().name("female").id(2).build(),
                                Gender.builder().name("other").id(3).build())
                )
                .build();
        details.save(userDetail);
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(Role.builder().name("user").id(2).build())    //fixed value
                .details(userDetail)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
