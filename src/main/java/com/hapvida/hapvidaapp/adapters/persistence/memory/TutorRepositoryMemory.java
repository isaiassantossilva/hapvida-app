package com.hapvida.hapvidaapp.adapters.persistence.memory;

import com.hapvida.hapvidaapp.adapters.persistence.memory.db.DBMemory;
import com.hapvida.hapvidaapp.adapters.persistence.memory.entity.TutorData;
import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import com.hapvida.hapvidaapp.application.valueobject.TutorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class TutorRepositoryMemory implements TutorRepository {

    private final DBMemory dbMemory;

    @Override
    public Tutor save(Tutor tutor) {
        if(tutor.getId() != null)
            return update(tutor);
        return create(tutor);
    }

    private Tutor create(Tutor tutor) {
        tutor.setId(UUID.randomUUID());
        dbMemory.tutorData.put(tutor.getId().toString(), TutorData.create(tutor));
        return tutor;
    }

    private Tutor update(Tutor tutor) {
        dbMemory.tutor.put(tutor.getId().toString(), tutor);
        return tutor;
    }

    @Override
    public Tutor findById(String id) {
        var result = dbMemory.tutorData.get(id);
        if(result == null)
            return  null;
        var tutor = result.toTutor();
        var animals = dbMemory
                .animalData
                .values()
                .stream()
                .filter(a -> a.getTutorRef().equals(id))
                .map(ad -> {
                    var a = ad.toAnimal();
                    a.setTutor(tutor);
                    return a;
                })
                .collect(Collectors.toList());
//        tutor.addAllAnimals(animals);
        return tutor;
    }

    @Override
    public Tutor findByUsername(String email) {
        var result = dbMemory
                .tutorData
                .values()
                .stream()
                .filter(t -> t.getEmail().equals(email))
                .findFirst();
        if(result.isEmpty())
            return null;
        return result.get().toTutor();
    }

    @Override
    public List<Tutor> searchByFilter(TutorFilter filter) {
        return dbMemory
                .tutor
                .values()
                .stream()
                .filter(t -> !nonNull(filter.getName()) || t.getName().contains(filter.getName()))
                .map(this::createNewInstance)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {

    }

    private Tutor createNewInstance(Tutor tutor){
        var entity = new Tutor(
                tutor.getName(),
                tutor.getEmail(),
                tutor.getPhoneNumber()
        );
        entity.setId(tutor.getId());
        entity.addAllAnimals(tutor.getAnimals());
        return entity;
    }
}
