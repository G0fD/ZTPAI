package com.company.shipify.repositories;

import com.company.shipify.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider,Integer> {
}