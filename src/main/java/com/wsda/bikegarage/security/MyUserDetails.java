package com.wsda.bikegarage.security;

import java.util.Arrays;
import java.util.Collection;
import com.wsda.bikegarage.entities.Impiegato;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private Impiegato impiegato;

    public MyUserDetails(Impiegato impiegato) {
        this.impiegato = impiegato;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(impiegato.getTipo());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return impiegato.getPassword();
    }

    @Override
    public String getUsername() {
        return impiegato.getUsername();
    }

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
