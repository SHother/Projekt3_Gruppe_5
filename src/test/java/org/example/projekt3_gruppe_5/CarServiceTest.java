package org.example.projekt3_gruppe_5;

import org.example.projekt3_gruppe_5.exceptions.BadRequestException;
import org.example.projekt3_gruppe_5.models.Car;
import org.example.projekt3_gruppe_5.repositories.CarRepository;
import org.example.projekt3_gruppe_5.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarServiceTest {
        //Ansvarlig: Micki
    private CarRepository carRepository;
    private CarService carService;
        // Denne metode bliver kørt før hver test.
        //Sætter et test miljø op ved at lave en "Mocker" gennem Mockito.
        //Gør at testen ikke forbinder til vores aktuelle Azure Database.
    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        carService = new CarService(carRepository);
    }

        //Vores Happy flow, hvor vi kører en test der har opsatte parametre
        //der er accepteret i vores system.
        //i disse test scenarier er det kun bilens pris vi tester.
    @Test
    @DisplayName("TC2.1: Opret gyldig bil (Happy Flow)")
    void testSaveCarHappyFlow() {
        Car car = new Car();
        car.setBrand("Tesla");
        car.setModel("Model 3");
        car.setPrice(4500);

        assertDoesNotThrow(() -> carService.saveCar(car));
        verify(carRepository, times(1)).insertCar(car);
    }

        //Vores Exception Flow, hvor en test bliver sat op med
        //parametre der er udenfor vores angivede prisramme i systemet
    @Test
    @DisplayName("TC2.3: Negativ pris (Exception Flow)")
    void testSaveCarNegativePrice() {
        Car car = new Car();
        car.setBrand("Tesla");
        car.setModel("Model 3");
        car.setPrice(-500);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> carService.saveCar(car));
        //Herunder bliver der kontrolleret at der bliver kastet den korrekte fejlkode.
        assertEquals("Car price must be greater than 0", exception.getMessage());
        verify(carRepository, never()).insertCar(any());
    }
}