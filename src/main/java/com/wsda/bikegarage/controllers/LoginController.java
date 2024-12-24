package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.entities.Cliente;
import com.wsda.bikegarage.security.MyUserDetails;
import com.wsda.bikegarage.services.ClienteService;
import com.wsda.bikegarage.services.ImpiegatoService;
import com.wsda.bikegarage.services.RicambiService;
import com.wsda.bikegarage.services.RiparazioneService;
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
    private ImpiegatoService impiegatoService;
    @Autowired
    private RiparazioneService riparazioneService;
    @Autowired
    private RicambiService ricambiService;
    @Autowired
    private ClienteService clienteService;


    @GetMapping("/loginDone")
    public String loginDone(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = ((MyUserDetails) auth.getPrincipal());
        Impiegato impiegato = impiegatoService.getImpiegato(user.getUsername(), user.getPassword());
        model.addAttribute("impiegato", impiegato);
        switch (impiegato.getTipo()) {
            case "mc" -> {
                List<Riparazione> riparazioni = riparazioneService.getAllRiparazioneAttesa().stream().toList();
                List<Riparazione> riparazioniMie = riparazioneService.getAllRiparazioneMie(impiegato.getId()).stream().toList();
                model.addAttribute("riparazioni", riparazioni);
                model.addAttribute("riparazioniMie", riparazioniMie);
                model.addAttribute("ricambi",ricambiService.getAllRicambi().stream().toList());
                return "meccanico";
            }
            case "ac" -> {
                List<Cliente> clienti = clienteService.getAllUtenti().stream().toList();
                model.addAttribute("clienti", clienti);
                System.out.println("ciao");
                return "accettazione";
            }
            case "ca" -> {
                return "cassa";
            }
            case "mg" -> {
                model.addAttribute("impiegato", impiegato);
                model.addAttribute("ricambi", ricambiService.getAllRicambi().stream().toList());
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
