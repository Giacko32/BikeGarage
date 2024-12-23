package com.wsda.bikegarage.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.wsda.bikegarage.entities.Impiegato;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails {

    private Impiegato impiegato;

    public MyUserDetails(Impiegato impiegato) {
        if(impiegato != null) {
            this.impiegato = impiegato;
        } else {
            this.impiegato = new Impiegato();
            this.impiegato.setUsername("");
            this.impiegato.setPassword("");
            this.impiegato.setId(-1);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(impiegato.getTipo());
        return List.of(authority);
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
