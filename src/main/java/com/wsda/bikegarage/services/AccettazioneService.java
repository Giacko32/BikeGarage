package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.repositories.ClienteRepository;
import com.wsda.bikegarage.repositories.MotoRepository;
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

    public Cliente registraCliente(Cliente cliente) {
        if (clienteRepository.findClienteByEmail(cliente.getEmail()) == null) {
            Cliente cliente1 = clienteRepository.save(cliente);
            System.out.println("Cliente registrato: " + cliente);
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
}

