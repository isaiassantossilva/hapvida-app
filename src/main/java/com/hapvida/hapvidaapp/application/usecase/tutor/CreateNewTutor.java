package com.hapvida.hapvidaapp.application.usecase.tutor;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import com.hapvida.hapvidaapp.application.usecase.animal.CreateAnimalsByList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateNewTutor {

    private final TutorRepository tutorRepository;
    private final CreateAnimalsByList createAnimalsByList;

    public Tutor execute(String name, String email, String phoneNumber, List<Animal> animals){
        var tutorToSave = new Tutor(name, email, phoneNumber);
        var tutorFromDb = tutorRepository.findByUsername(tutorToSave.getEmail());
        if(tutorFromDb != null)
            throw new IllegalArgumentException("User with " + email + " already exists");
        var savedTutor = tutorRepository.save(tutorToSave);
        animals.forEach(a -> a.setTutor(savedTutor));
        var savedAnimals = createAnimalsByList.execute(animals);
        savedTutor.addAllAnimals(savedAnimals);
        return savedTutor;
    }
}
