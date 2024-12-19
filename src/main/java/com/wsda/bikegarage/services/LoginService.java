package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Utente;
import com.wsda.bikegarage.repositories.ImpiegatoRepository;
import com.wsda.bikegarage.repositories.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class LoginService {

    @Autowired
    private ImpiegatoRepository impiegatoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Impiegato getImpiegato(String username, String password) {
        Impiegato impiegato = null;
        impiegato = impiegatoRepository.findImpiegatoByUsernameAndPassword(username, password);
        return impiegato;
    }

    public Collection<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

}
