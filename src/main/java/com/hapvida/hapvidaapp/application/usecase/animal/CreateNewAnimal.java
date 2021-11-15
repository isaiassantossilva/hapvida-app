package com.hapvida.hapvidaapp.application.usecase.animal;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import com.hapvida.hapvidaapp.application.repository.AnimalRepository;
import com.hapvida.hapvidaapp.application.usecase.tutor.FindTutorById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
@RequiredArgsConstructor
public class CreateNewAnimal {

    private final AnimalRepository animalRepository;
    private final FindTutorById findTutorById;

    public Animal execute(String name, Specie specie, String breed, String birthDate, String tutorId){
        var tutor = findTutorById.execute(tutorId);
        var formatBirthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var animalToSave = new Animal(name, specie, breed, formatBirthDate, tutor);
        var savedAnimal = animalRepository.save(animalToSave);
        tutor.addAnimal(savedAnimal);
        return savedAnimal;
    }
}
