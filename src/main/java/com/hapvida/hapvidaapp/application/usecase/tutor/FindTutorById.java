package com.hapvida.hapvidaapp.application.usecase.tutor;

import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindTutorById {

    private final TutorRepository tutorRepository;

    public Tutor execute(String id){
        Tutor tutor = tutorRepository.findById(id);
        if(tutor == null)
            throw new ResourceNotFound("Not found tutor with id: " + id);
        return tutor;
    }
}
