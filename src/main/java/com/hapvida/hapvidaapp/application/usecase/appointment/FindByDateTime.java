package com.hapvida.hapvidaapp.application.usecase.appointment;

import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FindByDateTime {

    private final AppointmentRepository appointmentRepository;

    public Appointment execute(LocalDateTime dateTime){
        var appointment = appointmentRepository.findByDateTime(dateTime);
        if(appointment == null)
            throw new ResourceNotFound("Can not find appointment");
        return appointment;
    }
}
