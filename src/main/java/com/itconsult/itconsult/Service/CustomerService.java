package com.itconsult.itconsult.Service;

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

    public Customer addCustomer(long id, String lastname, String firstname, String phonenumber, String street, String postalcode, String city, String country) {
        return customerRepository.save(Customer.builder()
                .id(id)
                .lastname(lastname)
                .firstname(firstname)
                .phoneNumber(phonenumber)
                .street(street)
                .postalCode(postalcode)
                .city(city)
                .country(country)
                .build());
    }

}
