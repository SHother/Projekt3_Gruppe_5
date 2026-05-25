package org.example.projekt3_gruppe_5.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.projekt3_gruppe_5.exceptions.ResourceNotFoundException;
import org.example.projekt3_gruppe_5.exceptions.BadRequestException;
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

    
    public Map<String, Double> getStatusPrices(){
        // Tager listen af biler og tæller prisen sammen for hver status og total prisen
        // Giver et Map tilbage med Status og Price
        // Bruges til at lave cirkel diagrammer

        List<Car> cars = getAllCars();
        Map<String, Double> countPrices = new HashMap<>();
        countPrices.put("Total", 0.0);

        for (Car car : cars) {
            String status = car.getStatus();
            double price = car.getPrice();

            if (countPrices.containsKey(status)) {
                countPrices.put(status, countPrices.get(status) + price);
            } else {
                countPrices.put(status, price);
            }
            countPrices.put("Total", countPrices.get("Total") + price);
        }

        //udregner pris procenten der er de ledige biler
        double ledigPct = (countPrices.getOrDefault("Ledig",0.0) * 100.0) /
                countPrices.getOrDefault("Total",1.0);
        //udregner pris procenten der er de ledige biler + de udlejede biler
        double udlejetPct =
                ((countPrices.getOrDefault("Udlejet",0.0) + countPrices.getOrDefault("Ledig",0.0))
                * 100.0) / countPrices.getOrDefault("Total",1.0);

        countPrices.put("ledigPct", ledigPct);
        countPrices.put("udlejetPct", udlejetPct);

        return countPrices;
    }

    public List<Car> getAllCars() {
        return carRepository.allCars();
    }

    /*
    public void updateStatus(int carId, String status) {
        carRepository.updateCarStatus(carId, status);
    }
*/
    public void updateStatus(int carId, String status) {
        if (status == null || status.isEmpty()) {
            throw new BadRequestException("Status cannot be empty");
        }

        List<Car> cars = getAllCars();
        boolean carExists = cars.stream().anyMatch(c -> c.getCarId() == carId);

        if (!carExists) {
            throw new ResourceNotFoundException("Car with ID " + carId + " not found");
        }

        carRepository.updateCarStatus(carId, status);
    }
/*
    // NY SAVE CAR *** TEST ****
    public void saveCar(Car car) {
    carRepository.insertCar(car);
    }
*/
public void saveCar(Car car) {
    if (car == null) {
        throw new BadRequestException("Car object cannot be null");
    }

    if (car.getBrand() == null || car.getBrand().isEmpty()) {
        throw new BadRequestException("Car brand is required");
    }

    if (car.getModel() == null || car.getModel().isEmpty()) {
        throw new BadRequestException("Car model is required");
    }

    if (car.getPrice() <= 0) {
        throw new BadRequestException("Car price must be greater than 0");
    }

    carRepository.insertCar(car);
}

 /*   // NY DELETE CAR *** TEST ****
    public void deleteCar(int carId) {
    carRepository.deleteCar(carId);
    }
*/
 public void deleteCar(int carId) {
     if (carId <= 0) {
         throw new BadRequestException("Car ID must be a positive number");
     }

     List<Car> cars = getAllCars();
     boolean carExists = cars.stream().anyMatch(c -> c.getCarId() == carId);

     if (!carExists) {
         throw new ResourceNotFoundException("Car with ID " + carId + " not found");
     }

     carRepository.deleteCar(carId);
 }

    // NY cirkel diagram car status ****TESTING*****
    public Map<String, Integer> getStatusCounts() {
        return carRepository.getStatusCounts();
    }




}
