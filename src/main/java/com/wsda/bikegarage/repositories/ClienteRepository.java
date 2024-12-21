package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    Cliente findClienteById(int id);
    Collection<Cliente> findAll();
}
