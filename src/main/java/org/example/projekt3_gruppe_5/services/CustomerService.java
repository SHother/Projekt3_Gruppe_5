package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.models.Customer;
import org.example.projekt3_gruppe_5.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.example.projekt3_gruppe_5.exceptions.BadRequestException;
//Skrevet af Gustav
@Service
public class CustomerService {
//Gustav

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
}

    public void createCustomer(String customerName, String address, String city, String zipCode, String email, String phone){
        if (customerName == null || customerName.isEmpty()) {
            throw new BadRequestException("Kundenavn er obligatorisk");
        }

        if (address == null || address.isEmpty()) {
            throw new BadRequestException("Adresse er obligatorisk");
        }

        if (city == null || city.isEmpty()) {
            throw new BadRequestException("By er obligatorisk");
        }

        if (zipCode == null || zipCode.isEmpty()) {
            throw new BadRequestException("Postnummer er obligatorisk");
        }

        if (email == null || email.isEmpty()) {
            throw new BadRequestException("Email er obligatorisk");
        }

        if (phone == null || phone.isEmpty()) {
            throw new BadRequestException("Telefon Nummer er obligatorisk");
        }
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setZipCode(zipCode);
        customer.setEmail(email);
        customer.setPhone(phone);

        customerRepository.insertCustomer(customer);
    }
}
