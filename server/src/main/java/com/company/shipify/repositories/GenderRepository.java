package com.company.shipify.repositories;

import com.company.shipify.model.Gender;
import com.company.shipify.model.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
    List<Gender> findByUsersDetails(MyUserDetails userDetails);

    List<Gender> findByUsersPreference(MyUserDetails userDetails);
}