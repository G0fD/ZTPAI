package com.company.shipify.services;

import com.company.shipify.model.Gender;
import com.company.shipify.model.MyUserDetails;
import com.company.shipify.repositories.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenderService {
    private final GenderRepository genderRepository;

    public Set<Gender> getGenderSet(String interested) {
        String[] ids = interested.split(" ");
        Set<Gender> genderSet = new HashSet<>();
        for (String id : ids) {
            Integer genderId = Integer.parseInt(id);
            Gender gender = genderRepository.findById(genderId).orElseThrow();
            genderSet.add(gender);
        }
        return genderSet;
    }

    public List<String> getUserPreferences(MyUserDetails userDetails) {
        List<String> gendersName = new ArrayList<>();
        for (Gender g : genderRepository.findByUsersPreference(userDetails)) {
            gendersName.add(g.getName());
        }
        return gendersName;
    }
}