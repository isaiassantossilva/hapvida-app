package com.hapvida.hapvidaapp.adapters.persistence.memory.db;

import com.hapvida.hapvidaapp.adapters.persistence.memory.entity.AnimalData;
import com.hapvida.hapvidaapp.adapters.persistence.memory.entity.TutorData;
import com.hapvida.hapvidaapp.adapters.persistence.memory.entity.VeterinaryData;
import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.entity.Tutor;
import com.hapvida.hapvidaapp.application.entity.Veterinary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class DBMemory {
    private Integer id = 0;
    public final Map<String, Tutor> tutor = new HashMap<>();
    public final Map<String, Veterinary> veterinary = new HashMap<>();
    public final Map<String, Animal> animal = new HashMap<>();
    public final Map<String, Appointment> appointment = new HashMap<>();


    public final Map<String, TutorData> tutorData = new HashMap<>();
    public final Map<String, VeterinaryData> veterinaryData = new HashMap<>();
    public final Map<String, AnimalData> animalData = new HashMap<>();
    public final Map<String, Appointment> appointmentData = new HashMap<>();

    public Integer getId(){
        this.id = id + 1;
        return id;
    }

    public void populateDb(){
        populateTutor();
    }

    private void populateTutor(){
        var tutor01 = new Tutor("Isaias", "isaias@gmail.com", "12345678");
        tutor01.setId(UUID.fromString("0f68e22d-8ae8-4def-a232-91fb8b2a6ead"));
        tutor.put(tutor01.getId().toString(), tutor01);
        tutorData.put(tutor01.getId().toString(), TutorData.create(tutor01));

        var tutor02 = new Tutor("Santos", "santos@gmail.com", "12345678");
        tutor02.setId(UUID.fromString("c3b6f2d7-1a39-46d5-83e4-43a549dfa297"));
        tutor.put(tutor02.getId().toString(), tutor02);
        tutorData.put(tutor02.getId().toString(), TutorData.create(tutor02));
    }

    public <T> T save(T entity) {
        this.id++;
        try {
            var methods = entity.getClass().getMethod("setId", String.class);
            methods.invoke(entity, this.id.toString());
            addEntity(entity);
            return entity;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private <T> void addEntity(T entity) {
        if(entity instanceof Tutor)
            addTutor((Tutor) entity);
        if(entity instanceof Veterinary)
            addVeterinary((Veterinary) entity);
    }

    private void addVeterinary(Veterinary veterinary) {
        this.veterinary.put(veterinary.getId().toString(), veterinary);
    }

    private void addTutor(Tutor tutor) {
        this.tutor.put(tutor.getId().toString(), tutor);
    }
}
