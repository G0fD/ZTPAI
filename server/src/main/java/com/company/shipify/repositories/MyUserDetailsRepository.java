package com.company.shipify.repositories;

import com.company.shipify.model.MyUserDetails;
import com.company.shipify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserDetailsRepository extends JpaRepository<MyUserDetails, Integer> {
    MyUserDetails getMyUserDetailsByUser(User user);
}