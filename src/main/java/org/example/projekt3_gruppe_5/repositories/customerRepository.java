package org.example.projekt3_gruppe_5.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class customerRepository implements ICustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public customerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int findOrCreateByName(String customerName) {
        String findSql = "Select customer_id from customer where customer_name = ?";
    try {
        return jdbcTemplate.queryForObject(findSql, Integer.class, customerName);
    } catch (EmptyResultDataAccessException e) {
        jdbcTemplate.update("INSERT INTO customer (customer_name) VALUES (?)",customerName);
        return jdbcTemplate.queryForObject(findSql, Integer.class, customerName);
    }
    }
}
