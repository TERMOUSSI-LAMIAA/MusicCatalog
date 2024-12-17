package com.MusicCatalog.MusicCatalog.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
public class CustomUserDetails implements UserDetails{

    private String username;
    private String password;
    private Boolean active;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, Boolean active, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can implement account expiration logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return active != null && active;  // Assuming you want to lock the account if inactive
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can implement credentials expiration logic if needed
    }

    @Override
    public boolean isEnabled() {
        return active != null && active;
    }
}
