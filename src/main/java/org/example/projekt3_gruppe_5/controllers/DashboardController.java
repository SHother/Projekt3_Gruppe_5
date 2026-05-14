package org.example.projekt3_gruppe_5.controllers;


import java.util.List;

import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.models.Customer;
import org.example.projekt3_gruppe_5.models.Lease;
import org.example.projekt3_gruppe_5.services.CarService;
import org.example.projekt3_gruppe_5.services.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DashboardController {
    private final CarService carService;
    private final LeaseService leaseService;

    public DashboardController(CarService carService, LeaseService leaseService) {
        this.carService = carService;
        this.leaseService = leaseService;
    }

// POPUP VINDUET*** TEST TEST ***

@GetMapping("/")
public String index(
        @RequestParam(required = false, defaultValue = "false")
        
        boolean showPopup,
        
        Model model) {

    model.addAttribute("cars", carService.getAllCars());
    model.addAttribute("showPopup", showPopup);

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
    public String createLeaseForm(Model model) {
        List<Customer> customers = leaseService.getAllCustomers();
        List<Car> cars = carService.filterCars(null, "Ledig");

        model.addAttribute("customers", customers);
        model.addAttribute("cars", cars);
        model.addAttribute("lease", new Lease());
        return "register_lease";
    }

    @GetMapping("/register_customer")
    public String registerCustomer(Model model) {
        return "register_customer";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "login";
    }



    // NY SAVE CAR *** TEST ****


    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute Car car) {
        //TODO: check input
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