package org.example.projekt3_gruppe_5.controllers;

import org.example.projekt3_gruppe_5.services.CustomerService;
import org.example.projekt3_gruppe_5.services.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@Controller
public class DataRegController {

    @Autowired
    private LeaseService leaseService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register_lease")
    public String submit(
            @RequestParam(required = false, defaultValue = "0") String customerId,
            @RequestParam(required = false, defaultValue = "0") String carId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pickUpDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate turnInDate,
            @RequestParam(required = false) String pickUpLocation,
            @RequestParam(required = false) String turnInLocation,
            Model model
    ) {
        try {
            leaseService.createLease(customerId, carId, pickUpDate, turnInDate, pickUpLocation, turnInLocation);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register_lease";
        }
    }

    @PostMapping("/register_customer")
    public String registerCustomer(
            @RequestParam(required = false) String customerName,
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

