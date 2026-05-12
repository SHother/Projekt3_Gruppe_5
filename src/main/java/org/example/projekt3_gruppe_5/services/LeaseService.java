package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.repositories.ICustomerRepository;
import org.example.projekt3_gruppe_5.repositories.ILeaseRepository;
import org.example.projekt3_gruppe_5.repositories.customerRepository;
import org.springframework.stereotype.Service;
import org.example.projekt3_gruppe_5.models.Lease;

import java.time.LocalDate;

@Service
public class LeaseService {


    private final ILeaseRepository leaseRepository;
    private final ICustomerRepository customerRepository;

    public LeaseService(ILeaseRepository leaseRepository, ICustomerRepository customerRepository) {
        this.leaseRepository = leaseRepository;
        this.customerRepository = customerRepository;

    }

    public void createLease(String customerName, int carId, LocalDate pickupDate, LocalDate turnInDate) {

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name is mandatory");
        }
        if (carId <= 0) {
            throw new IllegalArgumentException("Car number is mandatory");
        }
        if (pickupDate == null) {
            throw new IllegalArgumentException("Pickup-date is mandatory");
        }
        if (turnInDate == null) {
            throw new IllegalArgumentException("Turn in date is mandatory");

        }
      int customerId = customerRepository.findOrCreateByName(customerName);

        Lease lease = new Lease();
        lease.setCarId(carId);
        lease.setCustomerId(customerId);
        lease.setPickupDate(pickupDate);
        lease.setTurnInDate(turnInDate);

        leaseRepository.createLease(lease);


    }
}

