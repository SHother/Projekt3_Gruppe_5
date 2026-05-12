package org.example.projekt3_gruppe_5;

import org.example.projekt3_gruppe_5.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.projekt3_gruppe_5.repositories.CarRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@SpringBootApplication
public class Projekt3Gruppe5Application implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    public static void main(String[] args) {
        SpringApplication.run(Projekt3Gruppe5Application.class, args);

        System.out.println("Hello World!");
    }

    // for testing random stuff
    @Override
    public void run(String... args) {
    }
}
