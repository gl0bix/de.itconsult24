package com.itconsult.itconsult.security;

import java.util.List;

import com.itconsult.itconsult.repository.CustomerRepository;
import com.itconsult.itconsult.security.authority.CustomerAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomerDetails customerDetails = customerRepository.findByEmail(username)
                .map(customer -> {
                    return CustomerDetails.builder()
                            .username(customer.getEmail())
                            .password(customer.getPassword())
                            .authorities(List.of(new CustomerAuthority()))
                            .enabled(customer.isEnabled())
                            .accountNonExpired(true)
                            .accountNonLocked(true)
                            .credentialsNonExpired(true)
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return customerDetails;
    }
}