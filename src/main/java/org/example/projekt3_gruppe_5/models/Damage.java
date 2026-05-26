package org.example.projekt3_gruppe_5.models;
// Søren

// Modelklasse som repræsenterer en skade registreret på en leasingbil.
public class Damage {
    private int damageId;           // Primær nøgle for skaden i databasen.
    private int carId;              // carId refererer til bilen skaden tilhører.
    private int leaseId;            // leaseId refererer til den leasingaftale hvor skaden blev registreret.
    private String title;
    private String description;
    private int price;
    private String status;          // Status kan være "Udbedret", "Under udbedring" eller "Ikke indberettet".

    public Damage() {}
    public Damage(int damageId, int carId, int leaseId, String title, String description, int price, String status) {
        this.damageId = damageId;
        this.carId = carId;
        this.leaseId = leaseId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    // Getters
    public int getDamageId() {
        return damageId;
    }
    public int getCarId() {return carId;}
    public int getLeaseId() {
        return leaseId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getPrice() {
        return price;
    }
    public String getStatus() {
        return status;
    }

    // Setters
    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }
    public void setCarId(int carId) {this.carId = carId;}
    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
