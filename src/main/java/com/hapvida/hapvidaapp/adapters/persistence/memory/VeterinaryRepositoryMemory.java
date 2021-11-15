package com.hapvida.hapvidaapp.adapters.persistence.memory;

import com.hapvida.hapvidaapp.adapters.persistence.memory.db.DBMemory;
import com.hapvida.hapvidaapp.adapters.persistence.memory.entity.VeterinaryData;
import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import com.hapvida.hapvidaapp.application.valueobject.VeterinaryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class VeterinaryRepositoryMemory implements VeterinaryRepository {

    private final DBMemory dbMemory;

    @Override
    public Veterinary save(Veterinary veterinary) {
        if(veterinary.getId() != null)
            return update(veterinary);
        return create(veterinary);
    }

    private Veterinary create(Veterinary veterinary) {
        veterinary.setId(UUID.randomUUID());
      dbMemory.veterinaryData.put(veterinary.getId().toString(), VeterinaryData.create(veterinary));
        return veterinary;
    }

    private Veterinary update(Veterinary veterinary) {
        dbMemory.veterinaryData.put(veterinary.getId().toString(), VeterinaryData.create(veterinary));
        return veterinary;
    }

    @Override
    public Veterinary findByUsername(String username) {
        var result = dbMemory
                .veterinaryData
                .values()
                .stream()
                .filter(v -> v.getEmail().equals(username))
                .findFirst();
        if(result.isEmpty())
            return null;
        return result.get().toVeterinary();
    }

    @Override
    public List<Veterinary> containsStringInName(String name) {
        return dbMemory
                .veterinary
                .values()
                .stream()
                .filter(v -> v.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Veterinary> findByFilter(VeterinaryFilter filter) {
        return dbMemory
                .veterinaryData
                .values()
                .stream()
                .filter(v -> !nonNull(filter.getName()) || v.getName().contains(filter.getName()))
                .map(VeterinaryData::toVeterinary)
                .collect(Collectors.toList());
    }

    @Override
    public Veterinary findById(String id) {
        var result = dbMemory.veterinaryData.get(id);
        if(result == null)
            return null;
        var veterinary = result.toVeterinary();
        return veterinary;
    }

    @Override
    public void delete(String id) {

    }
}
