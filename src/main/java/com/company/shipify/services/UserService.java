package com.company.shipify.services;

import com.company.shipify.model.User;
import com.company.shipify.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Collection<User> getAllUsers(){
        return userRepository.findAll();
    }
}