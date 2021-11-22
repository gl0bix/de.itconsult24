package com.itconsult.itconsult.configuration;

import com.itconsult.itconsult.repository.CustomerRepository;
import com.itconsult.itconsult.security.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerDetailsService(customerRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}