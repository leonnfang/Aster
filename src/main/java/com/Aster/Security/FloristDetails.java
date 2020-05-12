package com.Aster.Security;

import com.Aster.Model.Florist;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class FloristDetails implements UserDetails {

    private Florist florist;

    public FloristDetails(Florist florist){
        super();
        this.florist = florist;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return florist.getPassword();
    }

    @Override
    public String getUsername() {
        return florist.getUsername();
    }

    public String getEmail() { return florist.getEmail(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
