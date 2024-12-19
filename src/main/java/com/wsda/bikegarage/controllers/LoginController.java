package com.wsda.bikegarage.controllers;
import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Utente;
import com.wsda.bikegarage.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
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
            return "meccanico";
        } else if (impiegato.getTipo().equals("ac")) {
            List<Utente> clienti = loginService.getAllUtenti().stream().toList();
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
