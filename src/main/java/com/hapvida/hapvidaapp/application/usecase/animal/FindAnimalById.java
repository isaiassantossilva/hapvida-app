package com.hapvida.hapvidaapp.application.usecase.animal;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAnimalById {

    private final AnimalRepository animalRepository;

    public Animal execute(String id){
        var animal = animalRepository.findById(id);
        if(animal == null)
            throw new ResourceNotFound("Animal not found");
        return animal;
    }
}
