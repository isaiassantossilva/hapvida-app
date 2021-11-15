package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.animal;

import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.AnimalPGEntity;
import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.TutorPGEntity;
import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Repository
@RequiredArgsConstructor
public class AnimalRepositoryPostgres implements AnimalRepository {

    private final SpringDataJpaAnimalRepository animalRepository;
    private final ModelMapper modelMapper;


    @Override
    public Animal findById(String id) {
        var entity = animalRepository.findById(UUID.fromString(id));
        return entity.isEmpty() ? null : entity.get().toAnimalWithTutor();
    }

    @Override
    public Animal save(Animal animal) {
        if(animal.getId() != null)
            return update(animal);
        return create(animal);
    }

    private Animal create(Animal animal) {
        var entity = AnimalPGEntity.fromAnimal(animal);
        var result = animalRepository.save(entity);
        return result.toAnimalWithTutor();
    }

    private Animal update(Animal animal) {
        var entityOptional = animalRepository.findById(animal.getId());
        if(entityOptional.isEmpty())
            throw new ResourceNotFound("Animal with id " + animal.getId() + " does not exists");
        var entity = entityOptional.get();
        entity.setName(animal.getName());
        entity.setSpecie(animal.getSpecie());
        entity.setBreed(animal.getBreed());
        entity.setBirthDate(animal.getBirthDate());
        entity.setTutor(TutorPGEntity.fromTutor(animal.getTutor()));
        var result = animalRepository.save(entity);
        return result.toAnimalWithTutor();
    }

    @Override
    public List<Animal> saveAll(List<Animal> animals) {
        var result = animalRepository.saveAll(animals.stream().map(AnimalPGEntity::fromAnimal).collect(Collectors.toList()));
        return result.stream().map(AnimalPGEntity::toAnimal).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        try {
            animalRepository.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFound(e.getMessage());
        }
    }
}
