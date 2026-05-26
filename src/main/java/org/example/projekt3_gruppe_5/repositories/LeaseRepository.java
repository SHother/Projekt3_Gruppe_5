package org.example.projekt3_gruppe_5.repositories;

import java.util.List;

import org.example.projekt3_gruppe_5.models.Lease;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Repository håndterer databaseoperationer relateret til leasingaftaler.
@Repository
public class LeaseRepository{

    private final JdbcTemplate jdbcTemplate;

    public LeaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // Gemmer en ny leasingaftale i databasen.
    public void createLease(Lease lease) {
        String sql =    "INSERT INTO lease (car_id, customer_id, pickup_date, turn_in_date, pickup_location, turn_in_location) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                lease.getCarId(),
                lease.getCustomerId(),
                lease.getPickupDate(),
                lease.getTurnInDate(),
                lease.getPickupLocation(),
                lease.getTurnInLocation()
        );
    }
    // Henter alle leasingaftaler fra databasen.
    public List<Lease> findAll() {
        String sql = "SELECT * FROM lease";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Lease.class));
    }
}

