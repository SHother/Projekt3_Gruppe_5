package org.example.projekt3_gruppe_5.models;

public class Car {
    private int carId;
    private String brand;
    private String model;
    private String status; //ledig, skadet etc
    private int price; //pris om måneden
    private String fuelType;
    private float mileage; // l/km

    public Car(){}
    public Car(int carId, String brand, String model, String status, int price, String fuelType, float mileage){
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.price = price;
        this.fuelType = fuelType;
        this.mileage = mileage;
    }
    // Getters
    public int getCarId() {
        return carId;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public String getStatus() {
        return status;
    }
    public float getPrice() {
        return price;
    }
    public String getFuelType() {
        return fuelType;
    }
    public float getMileage() {
        return mileage;
    }

    // Setters
    public void setCarId(int carId) {
        this.carId = carId;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

}
