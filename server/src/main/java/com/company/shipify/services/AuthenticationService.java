package com.company.shipify.services;

import com.company.shipify.dto.AuthenticationRequest;
import com.company.shipify.dto.AuthenticationResponse;
import com.company.shipify.dto.RegisterRequest;
import com.company.shipify.model.Genders;
import com.company.shipify.model.MyUserDetails;
import com.company.shipify.model.Roles;
import com.company.shipify.model.User;
import com.company.shipify.repositories.MyUserDetailsRepository;
import com.company.shipify.repositories.UserRepository;
import com.company.shipify.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final GenderService genderService;
    private final UserRepository repository;
    private final MyUserDetailsRepository details;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SecurityService securityService;

    public AuthenticationResponse register(RegisterRequest request) {
        MyUserDetails userDetail = MyUserDetails.builder()
                .email(request.getEmail())
                .name(request.getName())
                .surname(request.getSurname())
                .gender(Genders.mapToObject(request.getGender()))
                .preferences(genderService.getGenderSet(request.getInterested()))
                .build();
        details.save(userDetail);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(Roles.mapToObject(request.getRole()))
                .details(userDetail)
                .build();
        repository.save(user);

        String jwtToken = jwtService.generateToken(user);
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

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
