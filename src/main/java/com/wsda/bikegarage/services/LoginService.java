package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.repositories.ImpiegatoRepository;
import com.wsda.bikegarage.repositories.RiparazioneRepository;
import com.wsda.bikegarage.repositories.ClienteRepository;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private RiparazioneRepository riparazioneRepository;

    public Impiegato getImpiegato(String username, String password) {
        Impiegato impiegato = null;
        impiegato = impiegatoRepository.findImpiegatoByUsernameAndPassword(username, password);
        return impiegato;
    }

    public Collection<Cliente> getAllUtenti() {
        return clienteRepository.findAll();
    }


    public Collection<Riparazione> getAllRiparazioneAttesa() {return riparazioneRepository.findRiparazioneByStato("Attesa");}

    public Collection<Riparazione> getAllRiparazioneMie(int id) {
        Impiegato impiegato=new Impiegato();
        impiegato.setId(id);
        return riparazioneRepository.findRiparazioneByIdMeccanicoAndStato(impiegato,"In lavorazione");
    }
}
