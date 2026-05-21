package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.models.Customer;
import org.example.projekt3_gruppe_5.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
}

    public void createCustomer(String customerName, String address, String city, String zipCode, String email, String phone){
        //Gustav
        //TODO: input Validering
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setZipCode(zipCode);
        customer.setEmail(email);
        customer.setPhone(phone);

        //TODO Error handling
        customerRepository.insertCustomer(customer);
    }
}
