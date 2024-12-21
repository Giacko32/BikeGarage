package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.services.AccettazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accettazione")
public class AccettazioneController {

    @Autowired
    private AccettazioneService accettazioneService;

    @PostMapping("/registra")
    public Cliente registraCliente(@RequestParam(name = "nome", required = true) String nome, @RequestParam(name = "cognome", required = true) String cognome, @RequestParam(name = "email", required = true) String email) {
        nome = nome.trim();
        cognome = cognome.trim();
        email = email.trim();
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCognome(cognome);
        cliente.setEmail(email);
        try{
            cliente =  accettazioneService.registraCliente(cliente);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return cliente;
    }

    @GetMapping("/motoById")
    public Collection<Moto> motoById(@RequestParam(name = "id", required = true) int id) {
        try {
            return accettazioneService.getMotoById(id);
        }catch (Exception e){
            return null;
        }
    }
}
