package com.hapvida.hapvidaapp.application.usecase.animal;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateAnimalsByList {

    private final AnimalRepository animalRepository;

    public List<Animal> execute(List<Animal> animals){
        return animalRepository.saveAll(animals);
    }
}
