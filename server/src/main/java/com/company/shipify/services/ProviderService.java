package com.company.shipify.services;

import com.company.shipify.model.Provider;
import com.company.shipify.model.Song;
import com.company.shipify.repositories.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public List<String> getProvidersBySong(Song song) {
        List<String> providersName = new ArrayList<>();
        for (Provider p : providerRepository.findBySongsProvider(song)) {
            providersName.add(p.getName());
        }
        return providersName;
    }

    public Provider getProviderById(Integer id) {
        return providerRepository.getReferenceById(id);
    }
}
