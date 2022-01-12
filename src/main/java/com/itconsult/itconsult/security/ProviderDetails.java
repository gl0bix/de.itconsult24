package com.itconsult.itconsult.security;

import com.itconsult.itconsult.security.authority.ProviderAuthority;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Value
@Builder(toBuilder = true)
@ToString
public class ProviderDetails implements UserDetails {
    String username;
    @ToString.Exclude
    String password;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;
    Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority(ProviderAuthority.PROVIDER));

        return list;
    }
}