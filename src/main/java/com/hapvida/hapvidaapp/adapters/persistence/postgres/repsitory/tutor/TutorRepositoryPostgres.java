package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.tutor;

import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.AnimalPGEntity;
import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.TutorPGEntity;
import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import com.hapvida.hapvidaapp.application.valueobject.TutorFilter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Repository
@RequiredArgsConstructor
public class TutorRepositoryPostgres implements TutorRepository {

    private final SpringDataJpaTutorRepository tutorRepository;
    private final ModelMapper modelMapper;

    @Override
    public Tutor save(Tutor tutor) {
        if(tutor.getId() != null)
            return update(tutor);
        return create(tutor);
    }

    private Tutor create(Tutor tutor) {
        var pgEntity = TutorPGEntity.fromTutor(tutor);
        var result = tutorRepository.save(pgEntity);
        return result.toTutor();
    }

    private Tutor update(Tutor tutor) {
        var entityOptional = tutorRepository.findById(tutor.getId());
        if(entityOptional.isEmpty())
            throw new ResourceNotFound("Tutor with id " + tutor.getId() + " does not exists");
        var entity = entityOptional.get();
        entity.setName(tutor.getName());
        entity.setPhoneNumber(tutor.getPhoneNumber());
        entity.setEmail(tutor.getEmail());
        return tutorRepository.save(entity).toTutor();
    }

    @Override
    public Tutor findById(String id) {
        var result = tutorRepository.findById(UUID.fromString(id));
        return result.isEmpty() ? null : result.get().toTutor();
    }

    @Override
    public Tutor findByUsername(String username) {
        var entity = tutorRepository.findByEmail(username);
        return entity == null ? null : entity.toTutor();
    }

    @Override
    public List<Tutor> searchByFilter(TutorFilter filter) {
        if(filter.getName() == null)
            return tutorRepository.findAll().stream().map(TutorPGEntity::toTutor).collect(Collectors.toList());
        return tutorRepository.findByNameContains(filter.getName()).stream().map(TutorPGEntity::toTutor).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        try {
            tutorRepository.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFound(e.getMessage());
        }
    }

}
