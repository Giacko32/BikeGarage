package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.repositories.RiparazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CheckStatusService {

    @Autowired
    private RiparazioneRepository riparazioneRepository;

    public Riparazione getRiparazione(int code,String Targa){
        Moto moto = new Moto();
        moto.setTarga(Targa);
        Riparazione riparazione = riparazioneRepository.findRiparazioneByIdAndTarga(code,moto);
        if(riparazione == null){
            riparazione = new Riparazione();
            riparazione.setId(0);
        }
        return riparazione;
    }
}
