package org.example.projekt3_gruppe_5.controllers;


import java.util.List;

import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DashboardController {
    private final CarService carService;

    public DashboardController(CarService carService) {
        this.carService = carService;
    }

// POPUP VINDUET*** TEST TEST ***

// REGISTER CAR og OPRET SKADERAPPORT

@GetMapping("/")
public String dashboard(
        @RequestParam(required = false) Boolean showPopup,
        @RequestParam(required = false) Boolean showDamagePopup,
        Model model) {

    model.addAttribute("cars", carService.getAllCars());

    model.addAttribute("showPopup",
            showPopup != null && showPopup);

    model.addAttribute("showDamagePopup",
            showDamagePopup != null && showDamagePopup);

    return "inventory";
}


    //--------------------------------------------------------

    @GetMapping("/carFilter")
    public String showCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String status,
            Model model) {

        List<Car> cars = carService.filterCars(brand, status);
        model.addAttribute("cars", cars);

        return "inventory";
    }

    //test 2
    //Header links

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



    // NY SAVE CAR *** TEST ****


    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute Car car) {

    carService.saveCar(car);

    return "redirect:/";
    }
    //--------------------------------------------------------

    // NY DELETE CAR *** TEST ****
    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam int carId) {

    carService.deleteCar(carId);

    return "redirect:/";
    }
    //--------------------------------------------------------




    
}