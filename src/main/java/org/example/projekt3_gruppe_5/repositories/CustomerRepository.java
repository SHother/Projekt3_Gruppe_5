package org.example.projekt3_gruppe_5.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
