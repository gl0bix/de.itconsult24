package com.itconsult.itconsult.service;

import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(long id) {
        return customerRepository.findById(id);
    }

    public Customer addCustomer(String lastname, String firstname, String phoneNumber, String street,
                                String postalCode, String city, String country, String email, String password, Boolean enabled) {
        return customerRepository.save(Customer.builder()
                .lastname(lastname)
                .firstname(firstname)
                .phoneNumber(phoneNumber)
                .street(street)
                .postalCode(postalCode)
                .city(city)
                .country(country)
                .email(email)
                .password(password)
                .enabled(enabled)
                .build());
    }
}
