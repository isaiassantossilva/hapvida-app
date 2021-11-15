package com.hapvida.hapvidaapp.application.entity;

import com.hapvida.hapvidaapp.application.valueobject.Email;
import com.hapvida.hapvidaapp.application.valueobject.Name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Tutor {
    private UUID id;
    private Name name;
    private Email email;
    private PhoneNumber phoneNumber;
    private List<Animal> animals = new ArrayList<>();

    public Tutor(String name, String email, String phoneNumber){
        this.name = new Name(name);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        if(id == null)
            throw new IllegalArgumentException("Id can not be null");
        this.id = id;
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.animals);
    }

    public String getName() {
        return this.name.getValue();
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public String getEmail() {
        return email.getValue();
    }

    public void addAnimal(Animal animal){
        this.animals.add(animal);
    }

    public void addAllAnimals(List<Animal> animals){
        if(animals == null)
            throw new IllegalArgumentException("Animals can not be null");
        if(animals.isEmpty())
            throw new IllegalArgumentException("Animals can not be empty");
        this.animals = new ArrayList<>(animals);
    }

    public void replace(String name, String phoneNumber, String email){
        if(name != null)
            this.name = new Name(name);
        if(phoneNumber != null)
            this.phoneNumber = new PhoneNumber(phoneNumber);
        if(email != null)
            this.email = new Email(email);
    }
}
