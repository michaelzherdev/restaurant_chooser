package com.mzherdev.restchooser.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mzherdev on 07.06.2016.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
