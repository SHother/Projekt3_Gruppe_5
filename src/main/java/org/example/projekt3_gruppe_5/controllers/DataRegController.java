package org.example.projekt3_gruppe_5.controllers;

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

    @PostMapping("/dataregistration")
    public String submit(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false, defaultValue = "0") int carId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pickupDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate turnInDate,
            Model model
    ) {
        try {
            leaseService.createLease(customerName, carId, pickupDate, turnInDate);
            return "/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "dataregistration";
        }
    }
}

