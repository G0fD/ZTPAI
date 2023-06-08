package com.company.shipify.repositories;

import com.company.shipify.model.Provider;
import com.company.shipify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    List<Provider> findBySongsProvider(Song song);
}