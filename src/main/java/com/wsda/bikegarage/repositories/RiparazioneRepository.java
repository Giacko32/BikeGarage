package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.entities.Riparazione;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RiparazioneRepository extends CrudRepository<Riparazione, Integer> {
    Riparazione findById(int id);
    Riparazione findRiparazioneByIdAndStato(int id, String stato);
    Collection<Riparazione> findRiparazioneByStato(String stato);
    Collection<Riparazione> findRiparazioneByIdMeccanicoAndStato(Impiegato idMeccanico, String stato);
    Riparazione findRiparazioneByIdAndTarga(Integer id, Moto targa);

}
