package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.PezziRichiesti;
import com.wsda.bikegarage.entities.Ricambi;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.repositories.PezziRichiestiRepository;
import com.wsda.bikegarage.repositories.RicambiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@Transactional
public class PezziRichiestiService {
    @Autowired
    private PezziRichiestiRepository pezziRichiestiRepository;
    @Autowired
    private RicambiRepository ricambiRepository;

    public Collection<PezziRichiesti> getPezziRiparazione(int id) {
        Riparazione riparazione = new Riparazione();
        riparazione.setId(id);
        return pezziRichiestiRepository.findPezzirichiestiByIdRiparazione(riparazione);
    }

    public Collection<PezziRichiesti> getPezziRichiestiByriparazione(int riparazioneId){
        Riparazione riparazione =new Riparazione();
        riparazione.setId(riparazioneId);
        return pezziRichiestiRepository.findPezzirichiestiByIdRiparazione(riparazione);
    }

    public void aggiungipezzo(PezziRichiesti pezzoRichiesti){
        PezziRichiesti temp=pezziRichiestiRepository.findPezziRichiestiByIdRicambioAndIdRiparazione(pezzoRichiesti.getIdRicambio(),pezzoRichiesti.getIdRiparazione());
        if(temp==null){
            pezziRichiestiRepository.save(pezzoRichiesti);
        }else{
            temp.setQuantita(temp.getQuantita()+pezzoRichiesti.getQuantita());
            pezziRichiestiRepository.save(temp);
        }
    }

    public void aggiornaMagazzino(PezziRichiesti pezziRichiesti){
        Ricambi ricambio = ricambiRepository.findRicambiById(pezziRichiesti.getIdRicambio().getId());
        ricambio.setQuantita(ricambio.getQuantita() - pezziRichiesti.getQuantita());
        ricambiRepository.save(ricambio);
    }
}
