package org.example.projekt3_gruppe_5.models;

import java.time.LocalDate;

// Modelklasse som repræsenterer en leasingaftale i systemet.
//Micki
public class Lease {
    private int leaseId;            // Primær nøgle for leasingaftalen i databasen.
    private int carId;              // Referencer til bil som er knyttet til leasingaftalen.
    private int customerId;         // Referencer til Kunde som er knyttet til leasingaftalen.
    private LocalDate pickupDate;   // Leasing periodens start dato
    private LocalDate turnInDate;   // Leasing periodens slut dato
    private String pickupLocation;
    private String turnInLocation;

    public Lease() {}

    public Lease(int leaseId, int carId, int customerId, LocalDate pickupDate, LocalDate turnInDate, String pickupLocation, String turnInLocation) {
        this.leaseId = leaseId;
        this.carId = carId;
        this.customerId = customerId;
        this.pickupDate = pickupDate;
        this.turnInDate = turnInDate;
        this.pickupLocation = pickupLocation;
        this.turnInLocation = turnInLocation;
    }
    // Getters
    public int getLeaseId() {
        return leaseId;
    }
    public int getCarId() {
        return carId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public LocalDate getPickupDate() {
        return pickupDate;
    }
    public LocalDate getTurnInDate() {
        return turnInDate;
    }
    public String getPickupLocation() {
        return pickupLocation;
    }
    public String getTurnInLocation() {
        return turnInLocation;
    }

    // Setters
    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }
    public void setTurnInDate(LocalDate turnInDate) {
        this.turnInDate = turnInDate;
    }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    public void setTurnInLocation(String turnInLocation) {
        this.turnInLocation = turnInLocation;
    }

}
