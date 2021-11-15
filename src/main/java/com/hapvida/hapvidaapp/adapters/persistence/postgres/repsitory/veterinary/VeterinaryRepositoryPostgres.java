package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.veterinary;

import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.VeterinaryPGEntity;
import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import com.hapvida.hapvidaapp.application.valueobject.VeterinaryFilter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Primary
@Repository
@RequiredArgsConstructor
public class VeterinaryRepositoryPostgres implements VeterinaryRepository {

    private final SpringDataJpaVeterinaryRepository veterinaryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Veterinary save(Veterinary veterinary) {
        if(veterinary.getId() != null)
            return update(veterinary);
        return create(veterinary);
    }

    private Veterinary create(Veterinary veterinary) {
        var entity = modelMapper.map(veterinary, VeterinaryPGEntity.class);
        var result = veterinaryRepository.save(entity);
        return modelMapper.map(result, Veterinary.class);
    }

    private Veterinary update(Veterinary veterinary) {
        var entityOptional = veterinaryRepository.findById(veterinary.getId());
        if(entityOptional.isEmpty())
            throw new ResourceNotFound("Veterinary with id " + veterinary.getId() + " does not exists");
        var entity = entityOptional.get();
        entity.setName(veterinary.getName());
        entity.setEmail(veterinary.getEmail());
        entity.setPhoneNumber(veterinary.getPhoneNumber());
        return modelMapper.map(veterinaryRepository.save(entity), Veterinary.class);
    }

    @Override
    public Veterinary findByUsername(String username) {
        var result = veterinaryRepository.findByEmail(username);
        return isNull(result) ? null : modelMapper.map(result, Veterinary.class);
    }

    @Override
    public List<Veterinary> containsStringInName(String name) {
        return null;
    }

    @Override
    public List<Veterinary> findByFilter(VeterinaryFilter filter) {
        if(filter.getName() == null)
            return veterinaryRepository.findAll().stream().map(v -> modelMapper.map(v, Veterinary.class)).collect(Collectors.toList());
        var result = veterinaryRepository.findByNameContains(filter.getName());
        return isNull(result) ? null : result.stream().map(v -> modelMapper.map(v, Veterinary.class)).collect(Collectors.toList());
    }

    @Override
    public Veterinary findById(String id) {
        var result = veterinaryRepository.findById(UUID.fromString(id));
        return result.isEmpty() ? null : modelMapper.map(result.get(), Veterinary.class);
    }

    @Override
    public void delete(String id) {
        try {
            veterinaryRepository.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFound(e.getMessage());
        }
    }
}
