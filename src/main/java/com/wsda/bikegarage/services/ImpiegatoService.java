package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.repositories.ImpiegatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpiegatoService {

    @Autowired
    private ImpiegatoRepository impiegatoRepository;

    public Impiegato getImpiegato(String username, String password) {
        Impiegato impiegato = null;
        impiegato = impiegatoRepository.findImpiegatoByUsernameAndPassword(username, password);
        return impiegato;
    }
}
