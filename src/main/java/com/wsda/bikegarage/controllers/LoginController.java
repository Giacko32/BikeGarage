package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.security.MyUserDetails;
import com.wsda.bikegarage.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/loginDone")
    public String loginDone(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = ((MyUserDetails) auth.getPrincipal());
        Impiegato impiegato = loginService.getImpiegato(user.getUsername(), user.getPassword());
        model.addAttribute("impiegato", impiegato);
        switch (impiegato.getTipo()) {
            case "mc" -> {
                List<Riparazione> riparazioni = loginService.getAllRiparazioneAttesa().stream().toList();
                List<Riparazione> riparazioniMie = loginService.getAllRiparazioneMie(impiegato.getId()).stream().toList();
                model.addAttribute("riparazioni", riparazioni);
                model.addAttribute("riparazioniMie", riparazioniMie);
                return "meccanico";
            }
            case "ac" -> {
                List<Cliente> clienti = loginService.getAllUtenti().stream().toList();
                model.addAttribute("clienti", clienti);
                System.out.println("ciao");
                return "accettazione";
            }
            case "ca" -> {
                return "cassa";
            }
            case "mg" -> {
                model.addAttribute("impiegato", impiegato);
                model.addAttribute("ricambi", loginService.getAllRicambi().stream().toList());
                return "magazzino";
            }
        }
        return "index";
    }

    @GetMapping("/loginFailed")
    public String loginFailed(Model model) {
        model.addAttribute("error", true);
        return "index";
    }

}
