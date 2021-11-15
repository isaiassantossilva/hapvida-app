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
public class UpdateAnimal {

    private final AnimalRepository animalRepository;
    private final FindAnimalById findAnimalById;
    private final FindTutorById findTutorById;

    public Animal execute(String id, String name, Specie specie, String breed, String birthDate, String tutorId){
        var animalToUpdate = findAnimalById.execute(id);
        var formatBirthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var tutor = findTutorById.execute(tutorId);
        animalToUpdate.replace(name, specie, breed, formatBirthDate, tutor);
        return animalRepository.save(animalToUpdate);
    }
}
