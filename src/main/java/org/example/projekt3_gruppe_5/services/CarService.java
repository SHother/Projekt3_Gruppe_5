package org.example.projekt3_gruppe_5.services;

import java.util.List;

import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

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


    // NY SAVE CAR *** TEST ****

    public void saveCar(Car car) {
    carRepository.insertCar(car);
    }

    //--------------------------------------------------------


    // NY DELETE CAR *** TEST ****

    public void deleteCar(int carId) {
    carRepository.deleteCar(carId);
    }

    //--------------------------------------------------------

}
