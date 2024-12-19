package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UtenteRepository extends CrudRepository<Utente, Integer> {
    Utente findUtenteById(int id);

    Collection<Utente> findAll();
}
