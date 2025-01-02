package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente registraCliente(Cliente cliente) {
        if (clienteRepository.findClienteByEmail(cliente.getEmail()) == null) {
            return clienteRepository.save(cliente);
        } else {
            Cliente clienteError = new Cliente();
            clienteError.setId(0);
            return clienteError;
        }
    }

    public Collection<Cliente> getAllUtenti() {
        return clienteRepository.findAll();
    }
}
