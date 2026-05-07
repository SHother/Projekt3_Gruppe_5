package org.example.projekt3_gruppe_5.models;

import java.util.Date;

public class Lease {
    private int leaseId;
    private int carId;
    private int customerId;
    private Date pickupDate;
    private Date turnInDate;
    private String pickupLocation;
    private String turnInLocation;

    public Lease() {
    }

    public Lease(int leaseId, int carId, int customerId, Date pickupDate, Date turnInDate, String pickupLocation, String turnInLocation) {
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
    public Date getPickupDate() {
        return pickupDate;
    }
    public Date getTurnInDate() {
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
    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }
    public void setTurnInDate(Date turnInDate) {
        this.turnInDate = turnInDate;
    }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    public void setTurnInLocation(String turnInLocation) {
        this.turnInLocation = turnInLocation;
    }

}
