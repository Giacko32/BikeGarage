package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.entities.Moto;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.services.ClienteService;
import com.wsda.bikegarage.services.MotoService;
import com.wsda.bikegarage.services.RiparazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accettazione")
public class AccettazioneController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private MotoService motoService;
    @Autowired
    private RiparazioneService riparazioneService;

    @PostMapping("/registra")
    public Cliente registraCliente(@RequestParam(name = "nome", required = true) String nome, @RequestParam(name = "cognome", required = true) String cognome, @RequestParam(name = "email", required = true) String email) {
        nome = nome.trim();
        cognome = cognome.trim();
        email = email.trim();
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCognome(cognome);
        cliente.setEmail(email);
        try {
            cliente = clienteService.registraCliente(cliente);
        } catch (Exception e) {
            return null;
        }
        return cliente;
    }

    @GetMapping("/motoById")
    public Collection<Moto> motoById(@RequestParam(name = "id", required = true) int id) {
        try {
            return motoService.getMotoById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/registraMoto")
    public Moto registraMoto(@RequestParam(name = "targa", required = true) String targa, @RequestParam(name = "marca", required = true) String marca, @RequestParam(name = "modello", required = true) String modello, @RequestParam(name = "id", required = true) int id) {
        Moto moto = new Moto();
        moto.setMarca(marca);
        moto.setModello(modello);
        moto.setTarga(targa);
        Cliente c = new Cliente();
        c.setId(id);
        moto.setIdCliente(c);
        try {
            return motoService.registraMoto(moto);
        }catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/creaRiparazione")
    public int creaRiparazione(@RequestParam(name = "targa", required = true) String targa, @RequestParam(name = "note", required = true) String note) {
        System.out.println(targa);
        Riparazione rip = new Riparazione();
        Moto moto = new Moto();
        moto.setTarga(targa);
        rip.setTarga(moto);
        rip.setOre(0);
        rip.setNote(note);
        rip.setLavorazioni("");
        rip.setStato("In attesa");
        try{
            return riparazioneService.registraRiparazione(rip).getId();
        } catch (Exception e){
            return -1;
        }
    }

}
