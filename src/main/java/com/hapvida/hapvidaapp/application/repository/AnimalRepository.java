package com.hapvida.hapvidaapp.application.repository;

import com.hapvida.hapvidaapp.application.entity.Animal;

import java.util.List;

public interface AnimalRepository {
    Animal findById(String id);
    Animal save(Animal animal);
    List<Animal> saveAll(List<Animal> animals);

    void delete(String id);
}
