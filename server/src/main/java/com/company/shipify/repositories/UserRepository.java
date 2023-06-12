package com.company.shipify.repositories;

import com.company.shipify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByDetails_Email(String email);

    Optional<User> findByUsername(String username);

    List<User> findUsersByMatchedUser1(User user);

    List<User> findUsersByMatchedUser2(User user);
}