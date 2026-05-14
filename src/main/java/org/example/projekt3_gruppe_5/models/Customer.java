package org.example.projekt3_gruppe_5.models;

public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String city;
    private String zipCode;
    private String email;
    private String phone;

    public Customer() {}

    public Customer(int customerId, String customerName, String address, String city, String zipCode, String email, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.email = email;
        this.phone = phone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}