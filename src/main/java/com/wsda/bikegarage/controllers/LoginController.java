package com.wsda.bikegarage.controllers;
import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public String login(@RequestParam(name="username", required = true) String username, @RequestParam(name = "password", required = true) String password, Model model) {
        Impiegato impiegato = loginService.getImpiegato(username, password);
        model.addAttribute("impiegato", impiegato);
        if (impiegato == null) {
            model.addAttribute("error", true);
            return "index";
        } else if (impiegato.getTipo().equals("mc")) {
            List<Riparazione> riparazioni = loginService.getAllRiparazioneAttesa().stream().toList();
            List<Riparazione> riparazioniMie = loginService.getAllRiparazioneMie(impiegato.getId()).stream().toList();
            model.addAttribute("riparazioni", riparazioni);
            model.addAttribute("riparazioniMie", riparazioniMie);
            return "meccanico";
        } else if (impiegato.getTipo().equals("ac")) {
            List<Cliente> clienti = loginService.getAllUtenti().stream().toList();
            model.addAttribute("clienti", clienti);
            return "accettazione";
        } else if (impiegato.getTipo().equals("ca")) {
            return "cassa";
        } else if (impiegato.getTipo().equals("mg")) {
            return "magazzino";
        }
        return "accettazione";
    }
}
