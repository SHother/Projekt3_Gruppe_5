package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //test
    public List<Car> filterCars(String brand, String status) {

        List<Car> cars = getAllCars();

        if (brand != null && !brand.isEmpty()) {
            cars = cars.stream()
                    .filter(c -> c.getBrand().toLowerCase()
                            .contains(brand.toLowerCase()))
                    .toList();
        }

        if (status != null && !status.isEmpty()) {
            cars = cars.stream()
                    .filter(c -> c.getStatus().equalsIgnoreCase(status))
                    .toList();
        }

        return cars;
    }

    public List<Car> getAllCars() {
        return carRepository.allCars();
    }

}
