package org.example.projekt3_gruppe_5.controllers;

import org.example.projekt3_gruppe_5.models.Damage;
import org.example.projekt3_gruppe_5.services.DamageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// Controller håndterer HTTP requests relateret til skader
// og videresender data til DamageService.

@Controller
public class DamageController {
    //Søren

    private final DamageService damageService;

    public DamageController(DamageService damageService) {
        this.damageService = damageService;
    }

    // Modtager data fra frontend og gemmer en ny skade i databasen.
    @PostMapping("/saveDamageReport")
    public String saveDamage(@ModelAttribute Damage damage){
        damageService.insert(damage);
        return "redirect:/";
    }

    // Sletter en skade baseret på damageId sendt fra frontend.
    @PostMapping("deleteDamage")
    public String deleteDamage(@ModelAttribute Damage damage){
        damageService.delete(damage);
        return "redirect:/";
    }
}