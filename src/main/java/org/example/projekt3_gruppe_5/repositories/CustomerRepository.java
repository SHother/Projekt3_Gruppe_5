package org.example.projekt3_gruppe_5.repositories;

import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.models.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository{

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int findOrCreateByName(String customerName) {
        String findSql = "Select customer_id from customer where customer_name = ?";

    try {
        return jdbcTemplate.queryForObject(findSql, Integer.class, customerName);

    } catch (EmptyResultDataAccessException e) {
        jdbcTemplate.update("INSERT INTO customer (customer_name) VALUES (?)",customerName);
        return jdbcTemplate.queryForObject(findSql, Integer.class, customerName);
        }
    }

    public List<Customer> allCustomer() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    public Customer findCustomerById(int customerId) {
        String sql = "SELECT * from customer WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class),customerId);
    }

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
