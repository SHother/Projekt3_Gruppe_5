package org.example.projekt3_gruppe_5.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.projekt3_gruppe_5.repositories.CarRepository;

import java.util.List;

@Controller
public class DashboardController {
    private final CarService carService;

    public DashboardController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "inventory";
    }

    @GetMapping("/dataregistration")
    public String dataregistration(Model model) {
        return "dataregistration";
    }


    @GetMapping("/cars")
    public String showCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String status,
            Model model) {

        List<Car> cars = carService.filterCars(brand, status);
        model.addAttribute("cars", cars);

        return "cars";
    }

}

//test
