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
public class UpdateAppointment {

    private final AppointmentRepository appointmentRepository;
    private final FindAppointmentById findAppointmentById;
    private final FindVeterinaryById findVeterinaryById;
    private final FindAnimalById findAnimalById;

    public Appointment execute(String id, String veterinaryId, String animalId, String date, String hour, AppointmentStatus status){
        var appointmentToUpdate = findAppointmentById.execute(id);
        if(veterinaryId != null)
            appointmentToUpdate.setVeterinary(findVeterinaryById.execute(veterinaryId));
        if(animalId != null)
            appointmentToUpdate.setAnimal(findAnimalById.execute(animalId));
        if(status != null)
            appointmentToUpdate.setStatus(status);
        if(date != null && hour != null){
            var formatDateTime = formatDateTime(date, hour);
            checkIfTimeIsAvailable(formatDateTime);
            appointmentToUpdate.setDate(formatDateTime);
        }
        return appointmentRepository.save(appointmentToUpdate);
    }

    private LocalDateTime formatDateTime(String date, String hour) {
        var formatDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var formatHour = LocalTime.parse(hour, DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(formatDate,formatHour);
    }

    private void checkIfTimeIsAvailable(LocalDateTime appointmentDate) {
        var apt = appointmentRepository.findByDateTime(appointmentDate);
        if(apt != null)
            throw new IllegalArgumentException("Unavailable hours");
    }
}
