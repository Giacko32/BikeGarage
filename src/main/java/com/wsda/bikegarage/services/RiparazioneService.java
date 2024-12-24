package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.entities.PezziRichiesti;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.repositories.PezziRichiestiRepository;
import com.wsda.bikegarage.repositories.RiparazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class RiparazioneService {

    @Autowired
    private RiparazioneRepository riparazioneRepository;
    @Autowired
    private PezziRichiestiRepository pezziRichiestiRepository;

    public Riparazione getRiparazioneById(int id) {
        return riparazioneRepository.findRiparazioneByIdAndStato(id, "Completata");
    }

    public Collection<PezziRichiesti> getPezziRiparazione(int id) {
        Riparazione riparazione = new Riparazione();
        riparazione.setId(id);
        return pezziRichiestiRepository.findPezzirichiestiByIdRiparazione(riparazione);
    }

    public void pagamentoRiparazione(int id, int ore, int id_m, String targa) {
        Riparazione riparazione = new Riparazione();
        riparazione.setId(id);
        riparazione.setOre(ore);
        Impiegato meccanico = new Impiegato();
        meccanico.setId(id_m);
        riparazione.setIdMeccanico(meccanico);
        Moto moto = new Moto();
        moto.setTarga(targa);
        riparazione.setTarga(moto);
        riparazione.setStato("Pagata");
        riparazioneRepository.save(riparazione);
    }
}
