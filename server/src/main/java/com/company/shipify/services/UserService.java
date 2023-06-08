package com.company.shipify.services;

import com.company.shipify.dto.MatchesDTO;
import com.company.shipify.dto.ProfileDTO;
import com.company.shipify.model.MyUserDetails;
import com.company.shipify.model.User;
import com.company.shipify.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Configuration
public class UserService {
    private final UserRepository userRepository;
    private final MyUserDetailsService udService;
    private final GenderService genderService;

    public ProfileDTO getUserProfile() {
        Integer id = 1;
        User user = userRepository.getReferenceById(1);
        MyUserDetails userDetails = udService.getUserDetailsByUser(user);

        return ProfileDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(userDetails.getEmail())
                .name(userDetails.getName())
                .surname(userDetails.getSurname())
                .gender(userDetails.getGender().getName())
                .preferences(genderService.getUserPreferences(userDetails))
                .build();
    }

    public List<MatchesDTO> getMatches() {
        Integer id = 1;
        User user1 = userRepository.getReferenceById(id);
        List<User> userList = userRepository.findUsersByMatchedUser1(user1);
        List<MatchesDTO> userMatches = new ArrayList<>();

        for (User u : userList) {
            MyUserDetails userDetails = udService.getUserDetailsByUser(u);
            userMatches.add(MatchesDTO.builder()
                    .name(userDetails.getName())
                    .surname(userDetails.getSurname())
                    .email(userDetails.getEmail())
                    .gender(userDetails.getGender().getName())
                    .build());
        }

        userList = userRepository.findUsersByMatchedUser2(user1);

        for (User u : userList) {
            MyUserDetails userDetails = udService.getUserDetailsByUser(u);
            userMatches.add(MatchesDTO.builder()
                    .name(userDetails.getName())
                    .surname(userDetails.getSurname())
                    .email(userDetails.getEmail())
                    .gender(userDetails.getGender().getName())
                    .build());
        }

        return new ArrayList<>(new HashSet<>(userMatches));
    }
}