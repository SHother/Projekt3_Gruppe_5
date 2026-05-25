package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.models.Damage;
import org.example.projekt3_gruppe_5.repositories.DamageRepository;
import org.springframework.stereotype.Service;
import org.example.projekt3_gruppe_5.exceptions.ResourceNotFoundException;
import org.example.projekt3_gruppe_5.exceptions.BadRequestException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@Service
public class DamageService {

    private final DamageRepository damageRepository;

    public DamageService(DamageRepository damageRepository) {
        this.damageRepository = damageRepository;
    }

    public List<Damage> findAll() {
        return damageRepository.findAll();
    }

    public void insert(Damage damage) {
        if (damage == null) {
            throw new BadRequestException("Damage object cannot be null");
        }

        if (damage.getCarId() <= 0) {
            throw new BadRequestException("Car ID must be a positive number");
        }

        if (damage.getDescription() == null || damage.getDescription().isEmpty()) {
            throw new BadRequestException("Damage description is required");
        }

        if (damage.getPrice() < 0) {
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
            return damageRepository.findById(id);
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