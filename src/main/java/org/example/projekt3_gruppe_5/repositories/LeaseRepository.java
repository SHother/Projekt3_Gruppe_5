package org.example.projekt3_gruppe_5.repositories;

import org.example.projekt3_gruppe_5.models.Lease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LeaseRepository{

    private final JdbcTemplate jdbcTemplate;

    public LeaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createLease(Lease lease) {
        String sql =    "INSERT INTO lease (car_id, customer_id, pickup_date, turn_in_date, pickup_location, turn_in_location) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                lease.getCarId(),
                lease.getCustomerId(),
                lease.getPickupDate(),
                lease.getTurnInDate(),
                "Bilabonnement HQ",
                "FDM Station Sjælland"
        );
    }
}

