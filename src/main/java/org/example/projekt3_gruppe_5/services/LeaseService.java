package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.models.Customer;
import org.example.projekt3_gruppe_5.repositories.CustomerRepository;
import org.example.projekt3_gruppe_5.repositories.LeaseRepository;
import org.springframework.stereotype.Service;
import org.example.projekt3_gruppe_5.models.Lease;
import org.example.projekt3_gruppe_5.exceptions.BadRequestException;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final CustomerRepository customerRepository;

    public LeaseService(LeaseRepository leaseRepository, CustomerRepository customerRepository) {
        this.leaseRepository = leaseRepository;
        this.customerRepository = customerRepository;

    }

    public void createLease(int customerId, int carId, LocalDate pickupDate, LocalDate turnInDate, String pickUpLocation, String turnInLocation) {
        //Søren

        if (customerId <= 0) {
            throw new BadRequestException("Kundenavn er obligatorisk");
        }
        if (carId <= 0) {
            throw new BadRequestException("Vognnummer er obligatorisk");
        }
        if (pickupDate == null) {
            throw new BadRequestException("Afhentningsdato er obligatorisk");
        }
        if (turnInDate == null) {
            throw new BadRequestException("Afleveringsdato er obligatorisk");
        }
        if (pickupDate.isAfter(turnInDate)) {
            throw new BadRequestException("Afhentningsdato kan ikke være efter Afleveringsdato");
        }
        if (pickUpLocation == null) {
            throw new BadRequestException("Afhentningslokation er obligatorisk");
        }
        if (turnInLocation == null) {
            throw new BadRequestException("Afleveringslokation er obligatorisk");
        }

        //TODO: Check om bilen allerede er udlejet i den valgte periode

        Lease lease = new Lease();
        lease.setCarId(carId);
        lease.setCustomerId(customerId);
        lease.setPickupDate(pickupDate);
        lease.setTurnInDate(turnInDate);
        lease.setPickupLocation(pickUpLocation);
        lease.setTurnInLocation(turnInLocation);

        leaseRepository.createLease(lease);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.allCustomer();
    }

    public List<Lease> filterLeases(String carId) {
        List<Lease> leases = getAllLeases();

        if (carId != null && !carId.isEmpty()) {
            int carIdint = parseInt(carId);
            leases = leases.stream()
            .filter(c -> c.getCarId() == carIdint)
            .toList();
        }

        return leases;
    }

    private List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }
}

