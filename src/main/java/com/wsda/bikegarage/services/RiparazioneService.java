package com.wsda.bikegarage.services;

import com.wsda.bikegarage.Utils.EmailSender;
import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.entities.Riparazione;
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


    public Riparazione getRiparazioneById(int id) {
        return riparazioneRepository.findById(id);
    }

    public void pagamentoRiparazione(int id) {
        Riparazione riparazione = riparazioneRepository.findById(id);
        riparazione.setStato("Pagata");
        riparazioneRepository.save(riparazione);
    }

    public Riparazione registraRiparazione(Riparazione r) {
        return riparazioneRepository.save(r);
    }

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

    public Collection<Riparazione> getAllRiparazioneAttesa() {return riparazioneRepository.findRiparazioneByStato("In attesa");}

    public Collection<Riparazione> getAllRiparazioneMie(int id) {
        Impiegato impiegato=new Impiegato();
        impiegato.setId(id);
        return riparazioneRepository.findRiparazioneByIdMeccanicoAndStato(impiegato,"In lavorazione");
    }

    public Riparazione setNewStatus(int mec_Id,int rip_Id,String status){
        Riparazione riparazione = riparazioneRepository.findById(rip_Id);
        Impiegato impiegato = new Impiegato();
        impiegato.setId(mec_Id);
        riparazione.setIdMeccanico(impiegato);
        riparazione.setStato(status);
        EmailSender.sendEmailStatus(riparazione.getTarga().getIdCliente().getEmail(),status,riparazione.getTarga().getTarga());
        return riparazioneRepository.save(riparazione);
    }

    public Riparazione updateRiparazione(int idrip,int hours,String notes,String status){
        Riparazione riparazione = riparazioneRepository.findById(idrip);
        riparazione.setStato(status);
        riparazione.setLavorazioni(notes);
        riparazione.setOre(hours);
        if (status.equals("Completata")) {
            EmailSender.sendEmailStatus(riparazione.getTarga().getIdCliente().getEmail(),status,riparazione.getTarga().getTarga());
        }
        return riparazioneRepository.save(riparazione);
    }
}
