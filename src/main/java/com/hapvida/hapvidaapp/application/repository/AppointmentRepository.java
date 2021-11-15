package com.hapvida.hapvidaapp.application.repository;

import com.hapvida.hapvidaapp.application.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    Appointment findByDateTime(LocalDateTime localDateTime);
    List<Appointment> findAll();

    void delete(String id);

    Appointment findById(String id);
}
