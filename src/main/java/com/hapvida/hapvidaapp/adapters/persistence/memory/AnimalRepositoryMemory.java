package com.hapvida.hapvidaapp.adapters.persistence.memory;

import com.hapvida.hapvidaapp.adapters.persistence.memory.db.DBMemory;
import com.hapvida.hapvidaapp.adapters.persistence.memory.entity.AnimalData;
import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryMemory implements AnimalRepository {

    private final DBMemory dbMemory;

    @Override
    public Animal findById(String id) {
        var result = dbMemory.animalData.get(id);
        if(result == null)
            return null;
        var tutorData = dbMemory.tutorData.get(result.getTutorRef());
        if(tutorData == null)
            return null;
        var tutor = tutorData.toTutor();
        var animal = result.toAnimal();
        animal.setTutor(tutor);
        return animal;
    }

    @Override
    public Animal save(Animal animal) {
        animal.setId(UUID.randomUUID());
        dbMemory.animalData.put(animal.getId().toString(), AnimalData.create(animal));
        return animal;
    }

    @Override
    public List<Animal> saveAll(List<Animal> animals) {
        return animals.stream().peek(this::save).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {

    }
}
