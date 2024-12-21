package com.wsda.bikegarage.security;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.repositories.ImpiegatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ImpiegatoRepository impiegatoRepository;

    public UserDetailsServiceImpl(ImpiegatoRepository impiegatoRepository) {
        this.impiegatoRepository = impiegatoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Impiegato impiegato = impiegatoRepository.findImpiegatoByUsername(username);
        return new MyUserDetails(impiegato);
    }
}