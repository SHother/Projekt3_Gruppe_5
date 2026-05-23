package org.example.projekt3_gruppe_5.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.projekt3_gruppe_5.models.Car;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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


    // NY SAVE CAR *** TEST ****
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


    // NY DELETE CAR *** TEST ****
    public void deleteCar(int carId) {
    String sql = "DELETE FROM car WHERE car_id = ?";
    jdbcTemplate.update(sql, carId);
    }


    // NY cirkel diagram car status ****TESTING*****--------------------------------------------------------
    public Map<String, Integer> getStatusCounts() {
        String sql = """
            SELECT status, COUNT(*) as count
            FROM car
            GROUP BY status
        """;

        return jdbcTemplate.query(sql, rs -> {
            Map<String, Integer> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString("status").toLowerCase(), rs.getInt("count"));
            }
            return map;
        });
    }
    
}