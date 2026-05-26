package org.example.projekt3_gruppe_5.controllers;

import java.time.LocalDate;

import org.example.projekt3_gruppe_5.services.CarService;
import org.example.projekt3_gruppe_5.services.CustomerService;
import org.example.projekt3_gruppe_5.services.LeaseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


// Controller håndterer registrering af leases og kunder via popup vinduerne fra frontend.
//Skrevet af Gustav
@Controller
public class DataRegController {

    private final LeaseService leaseService;
    private final CustomerService customerService;
    private final CarService carService;

    public DataRegController(LeaseService leaseService, CustomerService customerService, CarService carService ) {
        this.leaseService = leaseService;
        this.customerService = customerService;
        this.carService = carService;
    }
    // Modtager data fra lease popup vinduet fra frontend og opretter en ny leasingaftale.
    @PostMapping("/register_lease")
    public String submit(
            @RequestParam(required = false, defaultValue = "0") String customerIdAsString,
            @RequestParam(required = false, defaultValue = "0") String carIdAsString,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pickUpDate,   // Konverterer automatisk dato fra HTML-format til LocalDate objekt.
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate turnInDate,   // Konverterer automatisk dato fra HTML-format til LocalDate objekt.
            @RequestParam(required = false) String pickUpLocation,
            @RequestParam(required = false) String turnInLocation,
            Model model
    ) {

        //TODO: find en anden løsning end casting?
        int customerId = Integer.parseInt(customerIdAsString);  // Konverterer String-værdier fra formularen til integers.
        int carId = Integer.parseInt(carIdAsString);

        try {   // Sender validerede leasingdata videre til Service-laget.
            leaseService.createLease(customerId, carId, pickUpDate, turnInDate, pickUpLocation, turnInLocation);
            carService.updateStatus(carId, "Udlejet");  // Opdaterer bilens status når leasingaftalen oprettes.
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register_lease";
        }
    }
    // Opretter en ny kunde ud fra data sendt fra frontend.
    @PostMapping("/register_customer")
    public String registerCustomer(
            @RequestParam(required = false) String customerName,    // RequestParam bruges til at hente inputværdier fra registerCustomer popup vinduet fra frontend.
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String zipCode,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
        Model model
    ) {
        try {
            customerService.createCustomer(customerName, address, city, zipCode, email, phone);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register_customer";
        }
    }
}

