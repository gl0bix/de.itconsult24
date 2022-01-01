package com.itconsult.itconsult.service;

import com.itconsult.itconsult.controller.form.CustomerRegisterFormModel;
import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.repository.CustomerRepository;
import com.itconsult.itconsult.service.Exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
public class CustomerService{
    private final CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }
    public Optional<Customer> getCustomerByEmail(String email){ return customerRepository.findByEmail(email); }

    @Deprecated
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

    public Customer registerNewCustomer(CustomerRegisterFormModel form) throws UserAlreadyExistException {
        if (emailExists(form.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + form.getEmail());
        }
        return customerRepository.save(Customer.builder()
                .lastname(form.getLastname())
                .firstname(form.getFirstname())
                .phoneNumber(form.getPhoneNumber())
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
        return customerRepository.findByEmail(email).isPresent();
    }
}
