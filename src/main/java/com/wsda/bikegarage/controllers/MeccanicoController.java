package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.PezziRichiesti;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.services.PezziRichiestiService;
import com.wsda.bikegarage.services.RiparazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/meccanico")
public class MeccanicoController {

    @Autowired
    private RiparazioneService riparazioneService;
    @Autowired
    private PezziRichiestiService pezziRichiestiService;

    @PostMapping("/aggiornastato")
    public Riparazione aggiornaStato(@RequestParam(name = "mecId")int mecId, @RequestParam(name = "stato")String stato,@RequestParam(name = "ripId")int ripId) {
        try {
            return riparazioneService.setNewStatus(mecId, ripId, stato);
        }catch (Exception e) {
            Riparazione riparazione = new Riparazione();
            riparazione.setId(-1);
            return riparazione;
        }
    }

    @GetMapping("/getPezzi")
    public Collection<PezziRichiesti> getPezzi(@RequestParam(name = "idRip")int idRip) {
        try{
            return pezziRichiestiService.getPezziRichiestiByriparazione(idRip);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/updateriparazione")
    public Riparazione updateriparazione(@RequestParam(name = "idRip")int idRip, @RequestParam(name = "stato")String stato,@RequestParam(name = "notes")String notes,@RequestParam(name = "hours")int hours) {
        try{
            return riparazioneService.updateRiparazione(idRip,hours,notes,stato);
        }catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/aggiungipezzi")
    public ResponseEntity<String> aggiungipezzi(@RequestBody Collection<PezziRichiesti> lista){
        try {
            for (PezziRichiesti p : lista) {
                pezziRichiestiService.aggiungipezzo(p);
                pezziRichiestiService.aggiornaMagazzino(p);
            }
            return ResponseEntity.ok("Tutti i pezzi sono stati aggiunti con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante l'aggiunta dei pezzi");
        }

    }
}
