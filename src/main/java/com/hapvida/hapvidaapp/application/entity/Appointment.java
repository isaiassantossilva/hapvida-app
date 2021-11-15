package com.hapvida.hapvidaapp.application.entity;

import com.hapvida.hapvidaapp.application.entity.enums.AppointmentStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Appointment {
    private UUID id;
    private LocalDateTime date;
    private AppointmentStatus status;
    private Veterinary veterinary;
    private Animal animal;


    public Appointment(Veterinary veterinary, LocalDateTime date, AppointmentStatus status, Animal animal) {
        this.setDate(date);
        this.setStatus(status);
        this.setVeterinary(veterinary);
        this.setAnimal(animal);
    }

    public void setId(UUID id){
        if(id == null)
            throw new IllegalArgumentException("Id can not be null");
        this.id = id;
    }

    public UUID getId(){
        return this.id;
    }

    public void setVeterinary(Veterinary veterinary) {
        if(veterinary == null)
            throw new IllegalArgumentException("Veterinary can not be null");
        this.veterinary = veterinary;
    }

    public void setStatus(AppointmentStatus status){
        if(status == null)
            throw new IllegalArgumentException("Appointment status can not be null");
        this.status = status;
    }

    public void setDate(LocalDateTime date){
        if(date == null)
            throw new IllegalArgumentException("Date can not be null");
        this.date = date;
    }

    public void setAnimal(Animal animal){
        if(animal == null)
            throw new IllegalArgumentException("Animal can not be null");
        this.animal = animal;
    }

    public void replace(Veterinary veterinary, LocalDateTime appointmentDate, AppointmentStatus status, Animal animal) {
//        if(veterinary)
    }
}
