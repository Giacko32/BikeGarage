package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Ricambi;
import com.wsda.bikegarage.repositories.RicambiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MagazzinoService {

    @Autowired
    private RicambiRepository ricambiRepository;

    public Ricambi orderRicambi(int id,int quantity) {
        Ricambi ricambio = ricambiRepository.findRicambiById(id);
        ricambio.setQuantita(ricambio.getQuantita() + quantity);
        ricambio=ricambiRepository.save(ricambio);
        return ricambio;
    }

    public Ricambi addnewRicambio(Ricambi ricambio) {
        Ricambi temp=ricambiRepository.findRicambiById(ricambio.getId());
        if (temp==null) {
            ricambiRepository.save(ricambio);
            return ricambio;
        }
        else{
            temp=new Ricambi();
            temp.setId(0);
            return temp;
        }
    }
}
