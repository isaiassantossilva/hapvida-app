package com.hapvida.hapvidaapp.application.usecase.tutor;

import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteTutor {

    private final TutorRepository tutorRepository;

    public void execute(String id){
        tutorRepository.delete(id);
    }
}
