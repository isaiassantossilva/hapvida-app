package com.hapvida.hapvidaapp.application.usecase.animal;

import com.hapvida.hapvidaapp.application.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAnimal {

    private final AnimalRepository animalRepository;

    public void execute(String id){
        animalRepository.delete(id);
    }
}
