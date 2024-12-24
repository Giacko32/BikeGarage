package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.repositories.ClienteRepository;
import com.wsda.bikegarage.repositories.MotoRepository;
import com.wsda.bikegarage.repositories.RiparazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class AccettazioneService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MotoRepository motoRepository;
    @Autowired
    private RiparazioneRepository riparazioneRepository;

    public Cliente registraCliente(Cliente cliente) {
        if (clienteRepository.findClienteByEmail(cliente.getEmail()) == null) {
            Cliente cliente1 = clienteRepository.save(cliente);
            return cliente1;
        } else {
            Cliente clienteError = new Cliente();
            clienteError.setId(0);
            return clienteError;
        }
    }

    public Collection<Moto> getMotoById(int id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return motoRepository.findByIdCliente(cliente);
    }

    public Moto registraMoto(Moto moto) {
        Moto found = motoRepository.findByTarga(moto.getTarga());
        if(found == null) {
            return motoRepository.save(moto);
        } else {
            Moto m = new Moto();
            m.setTarga("-1");
            return m;
        }

    }

    public Riparazione registraRiparazione(Riparazione r) {
        return riparazioneRepository.save(r);
    }
}

