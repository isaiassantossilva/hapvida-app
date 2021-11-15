package com.hapvida.hapvidaapp.application.usecase.appointment;

import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class FindAllAppointment {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> execute(){

        return appointmentRepository.findAll();
    }
}
