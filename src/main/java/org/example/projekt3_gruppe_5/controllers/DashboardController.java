package org.example.projekt3_gruppe_5.controllers;


import java.util.List;
import java.util.Map;

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

// REGISTER CAR og OPRET SKADERAPPORT

    @GetMapping("/")
    public String dashboard(
            @RequestParam(required = false) Boolean showPopup,
            @RequestParam(required = false) Boolean showDamagePopup,
            @RequestParam(required = false) Boolean showCustomerPopup,
            @RequestParam(required = false) Boolean showLeasePopup,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String carId,
            Model model) {

        //alle biler til lageroversigt grafer
        model.addAttribute("cars", carService.getAllCars());

        //Filtrerede biler til listen af biler (kan også være alle biler)
        model.addAttribute("carsFiltered", carService.filterCars(brand, status));

        model.addAttribute("leasesFiltered", leaseService.filterLeases(carId));

        //pop-ups
        model.addAttribute("showPopup", showPopup != null && showPopup);
        model.addAttribute("showDamagePopup", showDamagePopup != null && showDamagePopup);
        model.addAttribute("showCustomerPopup", showCustomerPopup != null && showCustomerPopup);
        model.addAttribute("showLeasePopup", showLeasePopup != null && showLeasePopup);

        //Data der skal bruges i pop-ups
        //Burde der var nogen if-statements tilknyttet, så vi ikke skal gøre det hver gang vi trykker på noget?
        model.addAttribute("customers", leaseService.getAllCustomers());
        model.addAttribute("availableCars", carService.filterCars(null, "Ledig"));
        model.addAttribute("lease", new Lease());


        // NY cirkel diagram car status ****TESTING*****
        // TODO: skal tildels rykkes til en Service klasse.. måske
        Map<String, Integer> counts = carService.getStatusCounts();

        int ledigCount = counts.getOrDefault("ledig", 0);
        int udlejetCount = counts.getOrDefault("udlejet", 0);
        int skadetCount = counts.getOrDefault("skadet", 0);

        // Bruger .getOrDefault for at "int" aldrig bliver retuneret som null fra databasen

        int total = ledigCount + udlejetCount + skadetCount;
        if (total == 0) total = 1;

        //  bruger "total == 1" hvis "total == 0" så vi ikke dividere med 0 på (linje 83)

        model.addAttribute("ledigCount", ledigCount);
        model.addAttribute("udlejetCount", udlejetCount);
        model.addAttribute("skadetCount", skadetCount);
        model.addAttribute("total", total);

        double ledigPct = (ledigCount * 100.0) / total;
        double udlejetPct = ((ledigCount + udlejetCount) * 100.0) / total;

        model.addAttribute("ledigPctCount", ledigPct);
        model.addAttribute("udlejetPctCount", udlejetPct);

        // Graf 2
        Map<String, Double> prices = carService.getStatusPrices();

        model.addAttribute("ledigPrice", prices.get("Ledig"));
        model.addAttribute("udlejetPrice", prices.get("Udlejet"));
        model.addAttribute("skadetPrice", prices.get("Skadet"));
        model.addAttribute("totalPrice", prices.get("Total"));
        model.addAttribute("ledigPctPrice", prices.get("ledigPct"));
        model.addAttribute("udlejetPctPrice", prices.get("udlejetPct"));

        return "inventory";
}

    // Tager mærke og status fra filter formen og videregiver til Service klassen
    // Får
    @GetMapping("/carFilter")
    public String showCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String status,
            Model model) {

        List<Car> carsFiltered = carService.filterCars(brand, status);
        model.addAttribute("carsFiltered", carsFiltered);

        return "inventory";
    }


    // @ModelAttribute mapper automatisk form-input fra HTML felter til et Car objekt
    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute Car car) {
        //TODO: check input
        carService.saveCar(car);
        return "redirect:/";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam int carId) {
        carService.deleteCar(carId);
        return "redirect:/";
    }


}