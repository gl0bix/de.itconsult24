package com.itconsult.itconsult.security;

import com.itconsult.itconsult.repository.CustomerRepository;
import com.itconsult.itconsult.security.authority.CustomerAuthority;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;


@AllArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return customerRepository.findByEmail(username)
                .map(customer -> CustomerDetails.builder()
                        .username(customer.getEmail())
                        .password(customer.getPassword())
                        .authorities(List.of(new CustomerAuthority()))
                        .enabled(customer.isEnabled())
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}