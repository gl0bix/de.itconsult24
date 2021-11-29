package com.itconsult.itconsult.security.authority;

import org.springframework.security.core.GrantedAuthority;

public class CustomerAuthority implements GrantedAuthority {

    public final static String ROLE_CUSTOMER = "ROLE_CUSTOMER";

    @Override
    public String getAuthority() {
        return ROLE_CUSTOMER;
    }

}