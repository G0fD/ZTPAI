package com.company.shipify.services;

import com.company.shipify.model.MyUserDetails;
import com.company.shipify.model.User;
import com.company.shipify.repositories.MyUserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService {
    private final MyUserDetailsRepository userDetailsRepository;

    public MyUserDetails getUserDetailsByUser(User user) {
        return userDetailsRepository.getMyUserDetailsByUser(user);
    }

}
