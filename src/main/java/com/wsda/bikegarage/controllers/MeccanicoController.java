package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.PezziRichiesti;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.repositories.RiparazioneRepository;
import com.wsda.bikegarage.services.MeccanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/meccanico")
public class MeccanicoController {

    @Autowired
    private MeccanicoService meccanicoService;



    @PostMapping("/aggiornastato")
    public Riparazione aggiornaStato(@RequestParam(name = "mecId")int mecId, @RequestParam(name = "stato")String stato,@RequestParam(name = "ripId")int ripId) {
        try {
            return meccanicoService.setNewStatus(mecId, ripId, stato);
        }catch (Exception e) {
            Riparazione riparazione = new Riparazione();
            riparazione.setId(-1);
            return riparazione;
        }
    }

    @GetMapping("/getPezzi")
    public Collection<PezziRichiesti> getPezzi(@RequestParam(name = "idRip")int idRip) {
        try{
            return meccanicoService.getPezziRichiestiByriparazione(idRip);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/updateriparazione")
    public Riparazione updateriparazione(@RequestParam(name = "idRip")int idRip, @RequestParam(name = "stato")String stato,@RequestParam(name = "notes")String notes,@RequestParam(name = "hours")int hours) {
        try{
            return meccanicoService.updateRiparazione(idRip,hours,notes,stato);
        }catch (Exception e) {
            return null;
        }
    }
}
