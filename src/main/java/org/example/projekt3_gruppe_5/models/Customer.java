package org.example.projekt3_gruppe_5.models;

public class Customer {
    private int customerId;
    private String customerName;

    public Customer(){}
    public Customer(int customerId, String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public int getCustomerId() {return customerId;}
    public String getCustomerName() {return customerName;}

    public void setCustomerName(String customerName) {this.customerName = customerName;}
    public void setCustomerId(int customerId) {this.customerId = customerId;}
}