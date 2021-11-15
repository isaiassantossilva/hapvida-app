package com.hapvida.hapvidaapp.application.usecase.tutor;

import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import com.hapvida.hapvidaapp.application.valueobject.TutorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchTutorByFilter {

    private final TutorRepository tutorRepository;

    public List<Tutor> execute(TutorFilter filter){
        return tutorRepository.searchByFilter(filter);
    }
}
