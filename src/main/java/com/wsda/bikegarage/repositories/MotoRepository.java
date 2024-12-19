package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Moto;
import org.springframework.data.repository.CrudRepository;

public interface MotoRepository extends CrudRepository<Moto, String> {
    Moto findByTarga(String targa);
}
