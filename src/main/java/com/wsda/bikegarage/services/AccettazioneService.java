package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccettazioneService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente registraCliente(Cliente cliente) {
        if (clienteRepository.findClienteByEmail(cliente.getEmail()) == null) {
            Cliente cliente1 = clienteRepository.save(cliente);
            System.out.println("Cliente registrato: " + cliente);
            return cliente1;
        } else {
            return null;
        }
    }
}

