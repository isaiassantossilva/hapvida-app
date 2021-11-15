package com.hapvida.hapvidaapp.adapters.persistence.postgres.entity;

import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.entity.enums.AppointmentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_APPOINTMENT")
@NoArgsConstructor
public class AppointmentPGEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime date;
    private AppointmentStatus status;

    @ManyToOne
    private VeterinaryPGEntity veterinary;
    @ManyToOne
    private AnimalPGEntity animal;

    public static AppointmentPGEntity fromAppointment(Appointment appointment) {
        var entity = new AppointmentPGEntity();
        entity.setDate(appointment.getDate());
        entity.setStatus(appointment.getStatus());
        entity.setVeterinary(VeterinaryPGEntity.fromVeterinary(appointment.getVeterinary()));
        entity.setAnimal(AnimalPGEntity.fromAnimal(appointment.getAnimal()));
        return entity;
    }

    public Appointment toAppointment() {
        var entity = new Appointment(
                veterinary.toVeterinary(),
                date,
                status,
                animal.toAnimalWithTutor()
        );
        entity.setId(id);
        return entity;
    }
}
