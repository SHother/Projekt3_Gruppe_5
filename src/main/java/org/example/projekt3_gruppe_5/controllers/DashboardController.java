package org.example.projekt3_gruppe_5.controllers;


import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/carFilter")
    public String showCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String status,
            Model model) {

        List<Car> cars = carService.filterCars(brand, status);
        model.addAttribute("cars", cars);

        return "inventory";
    }

    //test
    //Header links
    @GetMapping("/register_car")
    public String registerCar(Model model) {
        return "register_car";
    }

    @GetMapping("/damage_report")
    public String damageReport(Model model) {
        return "damage_report";
    }

    @GetMapping("/register_lease")
    public String registerLease(Model model) {
        return "register_lease";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "login";
    }
}