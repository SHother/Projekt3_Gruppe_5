package org.example.projekt3_gruppe_5.services;

import java.util.List;

import org.example.projekt3_gruppe_5.exceptions.BadRequestException;
import org.example.projekt3_gruppe_5.exceptions.ResourceNotFoundException;
import org.example.projekt3_gruppe_5.models.Damage;
import org.example.projekt3_gruppe_5.repositories.DamageRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class DamageService {
// Micki
    private final DamageRepository damageRepository;

    public DamageService(DamageRepository damageRepository) {
        this.damageRepository = damageRepository;
    }

    public List<Damage> findAll() {
        return damageRepository.findAll();
    }

    public void insert(Damage damage) {
        if (damage == null) {                // Sikrer at der ikke sendes et tomt Damage-objekt videre til databasen.
            throw new BadRequestException("Damage object cannot be null");
        }

        if (damage.getCarId() <= 0) {        // Validerer at bilen har et gyldigt ID.
            throw new BadRequestException("Car ID must be a positive number");
        }

        if (damage.getDescription() == null || damage.getDescription().isEmpty()) {
            throw new BadRequestException("Damage description is required");
        }

        if (damage.getPrice() < 0) {          // Pris må ikke være negativ.
            throw new BadRequestException("Damage price cannot be negative");
        }

        if (damage.getStatus() == null || damage.getStatus().isEmpty()) {
            throw new BadRequestException("Damage status is required");
        }

        damageRepository.insert(damage);
    }

    public Damage findById(int id) {
        if (id <= 0) {
            throw new BadRequestException("Damage ID must be a positive number");
        }

        try {
            return damageRepository.findById(id);   // Håndterer tilfælde hvor skaden ikke findes i databasen.
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Damage with ID " + id + " not found");
        }
    }

    public void delete(Damage damage) {
        if (damage == null) {
            throw new BadRequestException("Damage object cannot be null");
        }
        damageRepository.delete(damage);
    }
}