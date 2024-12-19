package com.wsda.bikegarage.repositories;

import com.wsda.bikegarage.entities.PezziRichiesti;
import com.wsda.bikegarage.entities.PezziRichiestiId;
import org.springframework.data.repository.CrudRepository;

public interface PezziRichiestiRepository extends CrudRepository<PezziRichiesti, PezziRichiestiId> {
    PezziRichiesti findPezziRichiestiById(PezziRichiestiId id);
}
