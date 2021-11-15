package com.hapvida.hapvidaapp.application.usecase.tutor;

import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateTutor {

    private final TutorRepository tutorRepository;
    private final FindTutorById findTutorById;

    public Tutor execute(String id, String name, String phoneNumber, String email){
        var tutorToUpdate = findTutorById.execute(id);
        tutorToUpdate.replace(name, phoneNumber, email);
        var tutorFromDb = tutorRepository.findByUsername(tutorToUpdate.getEmail());
        if(tutorFromDb != null && !tutorFromDb.getId().equals(tutorToUpdate.getId()))
            throw new IllegalArgumentException("User with " + email + " already exists");
        return tutorRepository.save(tutorToUpdate);
    }
}
