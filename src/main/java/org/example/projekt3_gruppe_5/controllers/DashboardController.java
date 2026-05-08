package org.example.projekt3_gruppe_5.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.projekt3_gruppe_5.repositories.CarRepository;

@Controller
public class DashboardController {
    private final CarRepository carRepository;

    public DashboardController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cars", carRepository.allCars());
        return "inventory";
    }


    @GetMapping("/dataregistration")
    public String dataregistration(Model model) {
        return "dataregistration";
    }


}

//test
