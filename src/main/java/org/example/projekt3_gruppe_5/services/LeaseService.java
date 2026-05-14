package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.models.Customer;
import org.example.projekt3_gruppe_5.repositories.CustomerRepository;
import org.example.projekt3_gruppe_5.repositories.LeaseRepository;
import org.springframework.stereotype.Service;
import org.example.projekt3_gruppe_5.models.Lease;

import java.time.LocalDate;
import java.util.List;

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
            throw new IllegalArgumentException("Kundenavn er obligatorisk");
        }
        if (carId <= 0) {
            throw new IllegalArgumentException("Vognnummer er obligatorisk");
        }
        if (pickupDate == null) {
            throw new IllegalArgumentException("Afhentningsdato er obligatorisk");
        }
        if (turnInDate == null) {
            throw new IllegalArgumentException("Afleveringsdato er obligatorisk");
        }
        if (pickUpLocation == null) {
            throw new IllegalArgumentException("Afhentningslokation er obligatorisk");
        }
        if (turnInLocation == null) {
            throw new IllegalArgumentException("Afleveringslokation er obligatorisk");
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
}

