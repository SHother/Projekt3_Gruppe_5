package org.example.projekt3_gruppe_5.services;

import org.example.projekt3_gruppe_5.models.Damage;
import org.example.projekt3_gruppe_5.repositories.DamageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {
    //Søren

    private final DamageRepository damageRepository;

    public DamageService(DamageRepository damageRepository) {
        this.damageRepository = damageRepository;
    }

    public List<Damage> findAll() {
        return damageRepository.findAll();
    }
    public void insert(Damage damage) {
        damageRepository.insert(damage);
    }
    public Damage findById(int id) {
        return damageRepository.findById(id);
    }
    public void delete(Damage damage) {
        damageRepository.delete(damage);
    }
}