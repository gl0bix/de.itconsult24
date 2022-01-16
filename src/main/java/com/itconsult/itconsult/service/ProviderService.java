package com.itconsult.itconsult.service;

import com.itconsult.itconsult.entity.Order;
import com.itconsult.itconsult.entity.Provider;
import com.itconsult.itconsult.enums.OrderType;
import com.itconsult.itconsult.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private Order order;

    public Optional<Provider> getProviderByEmail(String email) {return providerRepository.findByEmail(email);}

    public List<Provider> getallProviders() {
        return (List<Provider>) providerRepository.findAll();
    }

    public Optional<Provider> getProvider(long id) {
        return providerRepository.findById(id);
    }

    public Provider addProvider(String name, OrderType orderType, String street, String postalCode, String city, String country) {
        return providerRepository.save(Provider.builder()
                .name(name)
                .orderType(orderType)
                .street(street)
                .postalCode(postalCode)
                .city(city)
                .country(country)
                .build());
    }
}
