package com.wsda.bikegarage.controllers;

import com.wsda.bikegarage.entities.Riparazione;
import com.wsda.bikegarage.services.CheckStatusService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckStatusController {

    @Autowired
    private CheckStatusService checkStatusService;

    @GetMapping("/checkStatus")
    public Riparazione checkStatus(@RequestParam(name="targa",required=true) String targa,@RequestParam(name = "code",required = true)String code) {
        int idriparazione;
        try{
            idriparazione = Integer.parseInt(code);
            return checkStatusService.getRiparazione(idriparazione,targa);
        }catch (NumberFormatException e){
            System.out.println(e);
            return new Riparazione();
        }
    }
}