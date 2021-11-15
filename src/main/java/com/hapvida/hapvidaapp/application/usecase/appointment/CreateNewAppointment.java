package com.hapvida.hapvidaapp.application.usecase.appointment;

import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.entity.enums.AppointmentStatus;
import com.hapvida.hapvidaapp.application.repository.AppointmentRepository;
import com.hapvida.hapvidaapp.application.usecase.animal.FindAnimalById;
import com.hapvida.hapvidaapp.application.usecase.veterinary.FindVeterinaryById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Component
@RequiredArgsConstructor
public class CreateNewAppointment {

    private final AppointmentRepository appointmentRepository;
    private final FindVeterinaryById findVeterinaryById;
    private final FindAnimalById findAnimalById;

    public Appointment execute(String veterinaryId, String animalId, String date, String hour){
        var appointmentDate = formatDateTime(date, hour);
        checkIfTimeIsAvailable(appointmentDate);
        var veterinary = findVeterinaryById.execute(veterinaryId);
        var animal = findAnimalById.execute(animalId);
        var appointment = new Appointment(veterinary, appointmentDate, AppointmentStatus.OPENED, animal);
        return appointmentRepository.save(appointment);
    }

    private void checkIfTimeIsAvailable(LocalDateTime appointmentDate) {
        var apt = appointmentRepository.findByDateTime(appointmentDate);
        if(apt != null)
            throw new IllegalArgumentException("Unavailable hours");
    }

    private LocalDateTime formatDateTime(String date, String hour) {
        var formatDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var formatHour = LocalTime.parse(hour, DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(formatDate,formatHour);
    }
}
