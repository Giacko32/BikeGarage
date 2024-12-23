package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.repositories.RiparazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MeccanicoService {

    @Autowired
    private RiparazioneRepository riparazioneRepository;

    public Riparazione setNewStatus(int mec_Id,int rip_Id,String status){
        Riparazione riparazione = riparazioneRepository.findById(rip_Id);
        Impiegato impiegato = new Impiegato();
        impiegato.setId(mec_Id);
        riparazione.setIdMeccanico(impiegato);
        riparazione.setStato(status);
        return riparazioneRepository.save(riparazione);
    }
}
