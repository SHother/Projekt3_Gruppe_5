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
            Model model) {

        model.addAttribute("cars", carService.getAllCars());

        model.addAttribute("showPopup",
                showPopup != null && showPopup);

        model.addAttribute("showDamagePopup",
                showDamagePopup != null && showDamagePopup);

        model.addAttribute("showCustomerPopup",
                showCustomerPopup != null && showCustomerPopup);

        model.addAttribute("showLeasePopup",
                showLeasePopup != null && showLeasePopup);


        List<Customer> customers = leaseService.getAllCustomers();
        List<Car> availableCars = carService.filterCars(null, "Ledig");

        model.addAttribute("customers", customers);
        model.addAttribute("availableCars", availableCars);
        model.addAttribute("lease", new Lease());

    // NY cirkel diagram car status ****TESTING*****        

    Map<String, Integer> counts = carService.getStatusCounts();

    int ledig = counts.getOrDefault("ledig", 0);
    int udlejet = counts.getOrDefault("udlejet", 0);
    int skadet = counts.getOrDefault("skadet", 0);

    // jeg har brugt .getOrDefault for at "int" aldrig bliver retuneret som null fra databasen        

    int total = ledig + udlejet + skadet;
    if (total == 0) total = 1;

    //  bruger "total == 1" hvis "total == 0" så vi ikke dividere med 0 på (linje 64)

    model.addAttribute("ledig", ledig);
    model.addAttribute("udlejet", udlejet);
    model.addAttribute("skadet", skadet);
    model.addAttribute("total", total);

    double ledigPct = (ledig * 100.0) / total;
    double udlejetPct = ((ledig + udlejet) * 100.0) / total;

    model.addAttribute("ledigPct", ledigPct);
    model.addAttribute("udlejetPct", udlejetPct);




      //----------------------------------------------------------  cirkel diagram slut    


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
    public String createLeaseForm() {
        return "redirect:/?showLeasePopup=true";
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