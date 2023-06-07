package com.company.shipify.repositories;

import com.company.shipify.model.LikedBy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedByRepository extends JpaRepository<LikedBy, Integer> {
}
