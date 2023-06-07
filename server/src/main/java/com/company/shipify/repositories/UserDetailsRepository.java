package com.company.shipify.repositories;

import com.company.shipify.model.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<MyUserDetails, Integer> {
}