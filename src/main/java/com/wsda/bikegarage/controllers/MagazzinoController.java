package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.Ricambi;
import com.wsda.bikegarage.services.RicambiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/magazzino")
public class MagazzinoController {

    @Autowired
    private RicambiService ricambiService;


    @GetMapping("/aggiungi")
    public Ricambi aggiungi(@RequestParam(name = "id") int id, @RequestParam(name = "quantity") int quantita) {
        Ricambi ricambio;
        try {
            ricambio = ricambiService.orderRicambi(id, quantita);
        } catch (Exception e) {
            return null;
        }
        return ricambio;
    }

    @GetMapping("/aggiunginuovo")
    public Ricambi aggiunginuovo(@RequestParam(name = "id") String id, @RequestParam(name = "quantity") int quantita, @RequestParam(name = "name") String nome, @RequestParam(name = "price") Float price) {
        Ricambi ricambio = new Ricambi();
        try{
            int Id=Integer.parseInt(id);
            ricambio.setId(Id);
        } catch (NumberFormatException e) {
            ricambio.setId(-1);
            return ricambio;
        }
        ricambio.setQuantita(quantita);
        ricambio.setNome(nome);
        ricambio.setPrezzo(price);
        return ricambiService.addnewRicambio(ricambio);
    }
}
