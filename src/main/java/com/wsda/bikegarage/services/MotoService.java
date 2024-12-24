package com.wsda.bikegarage.services;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.repositories.MotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional

public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public Collection<Moto> getMotoById(int id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return motoRepository.findByIdCliente(cliente);
    }

    public Moto registraMoto(Moto moto) {
        Moto found = motoRepository.findByTarga(moto.getTarga());
        if(found == null) {
            return motoRepository.save(moto);
        } else {
            Moto m = new Moto();
            m.setTarga("-1");
            return m;
        }

    }
}
