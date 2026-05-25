package org.example.projekt3_gruppe_5.controllers;

import java.util.List;

import org.example.projekt3_gruppe_5.models.Damage;
import org.example.projekt3_gruppe_5.services.DamageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DamageController {
    //Søren

    private final DamageService damageService;

    public DamageController(DamageService damageService) {
        this.damageService = damageService;
    }

    @PostMapping("/saveDamageReport")
    public String saveDamage(@ModelAttribute Damage damage){
        damageService.insert(damage);
        return "redirect:/";
    }

    @PostMapping("deleteDamage")
    public String deleteDamage(@ModelAttribute Damage damage){
        damageService.delete(damage);
        return "redirect:/";
    }
}