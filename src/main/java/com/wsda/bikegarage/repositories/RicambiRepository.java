package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Ricambi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RicambiRepository extends CrudRepository<Ricambi, Integer> {
    Ricambi findRicambiById(int id);
    Collection<Ricambi> findAll();

}
