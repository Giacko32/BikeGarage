package com.wsda.bikegarage.security;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.repositories.ImpiegatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ImpiegatoRepository impiegatoRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Impiegato impiegato= impiegatoRepository.findImpiegatoByUsername(username);

        if (impiegato == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(impiegato);
    }

}
