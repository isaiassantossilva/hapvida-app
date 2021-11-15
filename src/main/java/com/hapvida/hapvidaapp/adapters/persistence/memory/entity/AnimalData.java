package com.hapvida.hapvidaapp.adapters.persistence.memory.entity;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class AnimalData {
    private String id;
    private String name;
    private Specie specie;
    private String breed;
    private LocalDate birthDate;
    private String tutorRef;

    public static AnimalData create(Animal animal){
        return AnimalData.builder()
                    .id(animal.getId().toString())
                    .name(animal.getName())
                    .specie(animal.getSpecie())
                    .breed(animal.getBreed())
                    .birthDate(animal.getBirthDate())
                    .tutorRef(animal.getTutor().getId().toString())
                .build();
    }

    public Animal toAnimal(){
        var entity = new Animal(name, specie, breed, birthDate);
        entity.setId(UUID.fromString(id));
        return entity;
    }
}
