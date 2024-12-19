package com.wsda.bikegarage.controllers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam(name="username", required = true) String username, @RequestParam(name = "password", required = true) String password) {
        System.out.println(username + " " + password);
        return "ciao";
    }
}
