package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.repositories.RiparazioneRepository;
import com.wsda.bikegarage.services.MeccanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meccanico")
public class MeccanicoController {

    @Autowired
    private MeccanicoService meccanicoService;


    @PostMapping("/aggiornastato")
    public Riparazione aggiornaStato(@RequestParam(name = "mecId")int mecId, @RequestParam(name = "stato")String stato,@RequestParam(name = "ripId")int ripId) {
        try {
            System.out.println(mecId);
            System.out.println(stato);
            System.out.println(ripId);
            return meccanicoService.setNewStatus(mecId, ripId, stato);
        }catch (Exception e) {
            Riparazione riparazione = new Riparazione();
            riparazione.setId(-1);
            return riparazione;
        }
    }
}
