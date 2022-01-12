package com.itconsult.itconsult.security;

import com.itconsult.itconsult.repository.ProviderRepository;
import com.itconsult.itconsult.security.authority.ProviderAuthority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@AllArgsConstructor
public class ProviderDetailsService implements UserDetailsService {
    private final ProviderRepository providerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return providerRepository.findByEmail(username)
                .map(provider -> ProviderDetails.builder()
                        .username(provider.getEmail())
                        .password(provider.getPassword())
                        .authorities(List.of(new ProviderAuthority()))
                        .enabled(provider.isEnabled())
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
