package org.example.projekt3_gruppe_5.repositories;

import org.example.projekt3_gruppe_5.models.Car;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public  List<Car> allCars() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class));
    }

    public void updateCarStatus(int carId, String status) {
        String sql = "UPDATE car SET status = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, status, carId);
    }
}
