package com.itconsult.itconsult.security.authority;

import org.springframework.security.core.GrantedAuthority;

public class ProviderAuthority implements GrantedAuthority {

    public final static String PROVIDER = "PROVIDER";

    @Override
    public String getAuthority() {
        return PROVIDER;
    }
}
