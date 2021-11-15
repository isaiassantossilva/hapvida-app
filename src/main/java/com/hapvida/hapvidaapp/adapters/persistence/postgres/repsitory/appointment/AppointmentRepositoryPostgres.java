package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.appointment;

import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.AppointmentPGEntity;
import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryPostgres implements AppointmentRepository {

    private final SpringDataJpaAppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Appointment save(Appointment appointment) {
        var entity = AppointmentPGEntity.fromAppointment(appointment);
        var result = appointmentRepository.save(entity);
        return result.toAppointment();
    }

    @Override
    public Appointment findByDateTime(LocalDateTime localDateTime) {
        var entity = appointmentRepository.findByDate(localDateTime);
        return entity == null ? null : entity.toAppointment();
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository
                .findAll()
                .stream()
                .map(AppointmentPGEntity::toAppointment)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        try {
            appointmentRepository.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFound(e.getMessage());
        }
    }

    @Override
    public Appointment findById(String id) {
        var result = appointmentRepository.findById(UUID.fromString(id));
        return result.isEmpty() ? null : result.get().toAppointment();
    }
}
