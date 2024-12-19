package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Ricambi;
import org.springframework.data.repository.CrudRepository;

public interface RicambiRepository extends CrudRepository<Ricambi, Integer> {
    Ricambi findRicambiById(int id);
}
