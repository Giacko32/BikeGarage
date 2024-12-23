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
public class MeccanicoService {

    @Autowired
    private RiparazioneRepository riparazioneRepository;

    @Autowired
    private PezziRichiestiRepository pezziRichiestiRepository;

    public Riparazione setNewStatus(int mec_Id,int rip_Id,String status){
        Riparazione riparazione = riparazioneRepository.findById(rip_Id);
        Impiegato impiegato = new Impiegato();
        impiegato.setId(mec_Id);
        riparazione.setIdMeccanico(impiegato);
        riparazione.setStato(status);
        return riparazioneRepository.save(riparazione);
    }
    public Collection<PezziRichiesti> getPezziRichiestiByriparazione(int riparazioneId){
        Riparazione riparazione =new Riparazione();
        riparazione.setId(riparazioneId);
        return pezziRichiestiRepository.findPezzi_richiestiByIdRiparazione(riparazione);
    }

    public Riparazione updateRiparazione(int idrip,int hours,String notes,String status,String targa,int idmec){
        Riparazione riparazione = new Riparazione();
        Moto moto=new Moto();
        moto.setTarga(targa);
        Impiegato impiegato = new Impiegato();
        impiegato.setId(idmec);
        riparazione.setId(idrip);
        riparazione.setStato(status);
        riparazione.setLavorazioni(notes);
        riparazione.setOre(hours);
        riparazione.setIdMeccanico(impiegato);
        riparazione.setTarga(moto);
        return riparazioneRepository.save(riparazione);
    }


}
