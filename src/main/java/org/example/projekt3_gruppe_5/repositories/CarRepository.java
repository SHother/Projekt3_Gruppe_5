package org.example.projekt3_gruppe_5.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.projekt3_gruppe_5.models.Car;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


// Repository håndterer databaseoperationer relateret til biler.
@Repository
public class CarRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public  List<Car> allCars() {       // Henter alle biler fra databasen.
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class));
    }

    public void updateCarStatus(int carId, String status) {      // Opdaterer bilens status i databasen.
        String sql = "UPDATE car SET status = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, status, carId);
    }

    public void insertCar(Car car) {
    String sql = """
        INSERT INTO car (brand, model, status, price, fuel_type, mileage)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

        jdbcTemplate.update(sql,
                car.getBrand(),
                car.getModel(),
                car.getStatus(),
                car.getPrice(),
                car.getFuelType(),
                car.getMileage()
        );
    }

    // Sletter en bil fra databasen baseret på carId.
    // Checker ikke om bilen har en foreign key et andet sted, så programmet crasher hvis bil er i en lease i databasen
    public void deleteCar(int carId) {
    String sql = "DELETE FROM car WHERE car_id = ?";
    jdbcTemplate.update(sql, carId);
    }

    // cirkel diagram car status - returnerer antal biler grupperet efter status til dashboard-cirkeldiagram 1 (lageroverblik).
    public Map<String, Integer> getStatusCounts() {
        String sql = """
            SELECT status, COUNT(*) as count
            FROM car
            GROUP BY status
        """;
        // "SQL GROUP BY" bruges til at tælle biler inden for hver statuskategori.
        
        return jdbcTemplate.query(sql, rs -> {
            Map<String, Integer> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString("status").toLowerCase(), rs.getInt("count"));
            }
            return map;
        });
    }
    
}