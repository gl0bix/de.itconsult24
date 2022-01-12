package com.itconsult.itconsult.configuration;

import com.itconsult.itconsult.repository.CustomerRepository;

import com.itconsult.itconsult.repository.ProviderRepository;
import com.itconsult.itconsult.security.CustomerDetailsService;
import com.itconsult.itconsult.security.ProviderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private CustomerRepository customerRepository;
    private ProviderRepository providerRepository;

    @Bean
    public CustomerDetailsService customerDetailsService() {
        return new CustomerDetailsService(customerRepository);
    }

    @Bean
    public ProviderDetailsService providerDetailsService() {
        return new ProviderDetailsService(providerRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}