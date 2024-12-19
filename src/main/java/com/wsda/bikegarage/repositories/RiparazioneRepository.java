package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Riparazione;
import org.springframework.data.repository.CrudRepository;

public interface RiparazioneRepository extends CrudRepository<Riparazione, Integer> {
    Riparazione findById(int id);
}
