package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.PezziRichiesti;
import com.wsda.bikegarage.entities.Ricambi;
import com.wsda.bikegarage.entities.Riparazione;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PezziRichiestiRepository extends CrudRepository<PezziRichiesti, Integer> {
    PezziRichiesti findPezziRichiestiById(int id);
    Collection<PezziRichiesti> findPezzirichiestiByIdRiparazione(Riparazione idRiparazione);
    PezziRichiesti findPezziRichiestiByIdRicambioAndIdRiparazione(Ricambi idRicambio, Riparazione idRiparazione);
}
