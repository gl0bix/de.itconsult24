package com.itconsult.itconsult.service;

import com.itconsult.itconsult.entity.Provider;
import com.itconsult.itconsult.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public List<Provider> getallProviders(){
        return (List<Provider>) providerRepository.findAll();
    }

    public Optional<Provider> getProvider(long id){
        return providerRepository.findById(id);
    }

    public Provider addProvider(String name, String competence, String street, String postalCode, String city, String country){
        return providerRepository.save(Provider.builder()
                .name(name)
                .competence(competence)
                .street(street)
                .postalCode(postalCode)
                .city(city)
                .country(country)
                .build());
    }
}
