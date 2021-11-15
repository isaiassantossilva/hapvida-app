package com.hapvida.hapvidaapp.application.entity;

import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import com.hapvida.hapvidaapp.application.valueobject.Breed;
import com.hapvida.hapvidaapp.application.valueobject.Name;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Animal {
    private UUID id;
    private Name name;
    private Specie specie;
    private Breed breed;
    private LocalDate birthDate;
    private Tutor tutor;

    public Animal(String name, Specie specie, String breed, LocalDate birthDate, Tutor tutor){
        this.name = new Name(name);
        this.setSpecie(specie);
        this.breed = new Breed(breed);
        this.setBirthDate(birthDate);
        this.setTutor(tutor);
    }


    public Animal(String name, Specie specie, String breed, LocalDate birthDate){
        this.name = new Name(name);
        this.setSpecie(specie);
        this.breed = new Breed(breed);
        this.setBirthDate(birthDate);
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        if(id == null)
            throw new IllegalArgumentException("Id can not be null");
        this.id = id;
    }

    public String getName(){
        return this.name.getValue();
    }

    public String getBreed(){
        return this.breed.getValue();
    }

    public void setTutor(Tutor tutor) {
        if(specie == null)
            throw new IllegalArgumentException("Tutor can not be null");
        this.tutor = tutor;
    }

    private void setSpecie(Specie specie){
        if(specie == null)
            throw new IllegalArgumentException("Specie can not be null");
        this.specie = specie;
    }

    private void setBirthDate(LocalDate birthDate){
        if(birthDate == null)
            throw new IllegalArgumentException("Birthdate can not be null");
        this.birthDate = birthDate;
    }

    public void replace(String name, Specie specie, String breed, LocalDate formatBirthDate, Tutor tutor) {
        if(name != null)
            this.name = new Name(name);
        if(specie != null)
            this.specie = specie;
        if(breed != null)
            this.breed = new Breed(breed);
        if(formatBirthDate != null)
            this.birthDate = formatBirthDate;
        if(tutor != null)
            this.tutor = tutor;
    }
}
