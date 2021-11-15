package com.hapvida.hapvidaapp.application.usecase.appointment;

import com.hapvida.hapvidaapp.application.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAppointment {

    private final AppointmentRepository appointmentRepository;

    public void execute(String id){
        appointmentRepository.delete(id);
    }
}
