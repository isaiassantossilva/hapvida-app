package com.hapvida.hapvidaapp.application.repository;

import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.valueobject.TutorFilter;

import java.util.List;

public interface TutorRepository {
    Tutor save(Tutor tutor);
    Tutor findById(String id);
    Tutor findByUsername(String email);

     List<Tutor> searchByFilter(TutorFilter filter);

    void delete(String id);
}
