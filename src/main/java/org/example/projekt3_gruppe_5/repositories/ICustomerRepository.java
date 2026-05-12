package org.example.projekt3_gruppe_5.repositories;

public interface ICustomerRepository {
    int findOrCreateByName(String customerName);
}
