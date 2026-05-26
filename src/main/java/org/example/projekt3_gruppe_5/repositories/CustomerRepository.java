package org.example.projekt3_gruppe_5.repositories;

import java.util.List;

import org.example.projekt3_gruppe_5.models.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Repository håndterer databaseoperationer relateret til kunder.
//Skrevet af Gustav
@Repository
public class CustomerRepository{

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // Finder eksisterende kunde baseret på navn ellers oprettes de hvis de ikke findes

    public int findOrCreateByName(String customerName) {
        String findSql = "Select customer_id from customer where customer_name = ?";

    try {   // Forsøger først at finde kunden i databasen.
        return jdbcTemplate.queryForObject(findSql, Integer.class, customerName);

    } catch (EmptyResultDataAccessException e) {    // Hvis kunden ikke findes, oprettes en ny kunde automatisk.
        jdbcTemplate.update("INSERT INTO customer (customer_name) VALUES (?)",customerName);    // Opretter ny kunde i databasen med det angivne navn.
        return jdbcTemplate.queryForObject(findSql, Integer.class, customerName);
        }
    }
    // Henter alle kunder fra databasen.
    public List<Customer> allCustomer() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }
    // Finder en kunde ud fra customerId.
    public Customer findCustomerById(int customerId) {
        String sql = "SELECT * from customer WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class),customerId);
    }
    // Gemmer en ny kunde i databasen.
    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_name, address, city, zip_code, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                customer.getCustomerName(),
                customer.getAddress(),
                customer.getCity(),
                customer.getZipCode(),
                customer.getEmail(),
                customer.getPhone());
    }
}
