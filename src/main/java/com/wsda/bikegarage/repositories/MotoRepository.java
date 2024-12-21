package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.entities.Moto;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MotoRepository extends CrudRepository<Moto, String> {
    Moto findByTarga(String targa);
    Collection<Moto> findByIdCliente(Cliente cliente);
}
