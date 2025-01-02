package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Impiegato;
import org.springframework.data.repository.CrudRepository;

public interface ImpiegatoRepository extends CrudRepository<Impiegato, Integer> {
    Impiegato findById(int id);
    Impiegato findImpiegatoByUsernameAndPassword(String username, String password);
    Impiegato findImpiegatoByUsername(String username);
    Impiegato findFirstImpiegatoByMail(String mail);
}
