package com.hapvida.hapvidaapp.application.usecase.appointment;

import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAppointmentById {

    private final AppointmentRepository appointmentRepository;

    public Appointment execute(String id){
        var appointment = appointmentRepository.findById(id);
        if(appointment == null)
            throw new ResourceNotFound("Appointment with id " + id + " not found");
        return appointment;
    }
}
