package com.hapvida.hapvidaapp.adapters.persistence.memory;

import com.hapvida.hapvidaapp.adapters.persistence.memory.db.DBMemory;
import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryMemory implements AppointmentRepository {

    private final DBMemory dbMemory;

    @Override
    public Appointment save(Appointment appointment) {
        appointment.setId(UUID.randomUUID());
        dbMemory.appointment.put(appointment.getId().toString(), appointment);
        return appointment;
    }

    @Override
    public Appointment findByDateTime(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public List<Appointment> findAll() {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Appointment findById(String id) {
        return null;
    }
}
