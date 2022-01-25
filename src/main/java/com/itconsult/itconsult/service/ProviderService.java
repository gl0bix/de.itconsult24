package com.itconsult.itconsult.service;


import com.itconsult.itconsult.controller.form.ProviderRegisterFormModel;
import com.itconsult.itconsult.entity.Provider;
import com.itconsult.itconsult.enums.OrderType;
import com.itconsult.itconsult.repository.ProviderRepository;
import com.itconsult.itconsult.service.Exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private PasswordEncoder passwordEncoder;

    public Optional<Provider> getProviderByEmail(String email) { return providerRepository.findByEmail(email); }

    public List<Provider> getAllProviders() {
        return (List<Provider>) providerRepository.findAll();
    }

    public Optional<Provider> getProvider(long id) {
        return providerRepository.findById(id);
    }

    public List<Provider> getProviderByOrderType() { return providerRepository.findAllByOrderType(); }

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

    public void registerNewProvider(ProviderRegisterFormModel form) throws UserAlreadyExistException {
        if (emailExists(form.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + form.getEmail());

        }
        providerRepository.save(Provider.builder()
                .name(form.getCompany())
                .orderType(form.getOrderType())
                .street(form.getStreet())
                .postalCode(form.getPostalCode())
                .city(form.getCity())
                .country(form.getCountry())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .enabled(true)
                .build());

    }

    private boolean emailExists(String email) {
        return providerRepository.findByEmail(email).isPresent();
    }
}
