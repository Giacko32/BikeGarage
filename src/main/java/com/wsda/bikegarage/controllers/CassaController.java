package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.PezziRichiesti;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.services.CheckStatusService;
import com.wsda.bikegarage.services.RiparazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cassa")
public class CassaController {

    @Autowired
    private RiparazioneService riparazioneService;

    @GetMapping("/getRiparazione")
    public Riparazione getRiparazione(@RequestParam(name = "id", required = true) int id) {
        try {
            return riparazioneService.getRiparazioneById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getDatiPagamento")
    public Collection<PezziRichiesti> getDatiPagamento(@RequestParam(name = "id", required = true) int id) {
        try {
            return riparazioneService.getPezziRiparazione(id);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/pagamentoCompletato")
    public void pagamentoCompletato(@RequestParam(name = "id", required = true) int id, @RequestParam(name = "ore", required = true) int ore, @RequestParam(name = "id_m", required = true) int id_m, @RequestParam(name = "targa", required = true) String targa) {
        try {
            riparazioneService.pagamentoRiparazione(id, ore, id_m, targa);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
