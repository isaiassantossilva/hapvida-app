package com.hapvida.hapvidaapp.application.entity;

import com.hapvida.hapvidaapp.application.valueobject.Email;
import com.hapvida.hapvidaapp.application.valueobject.Name;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class Veterinary {
    private UUID id;
    private Name name;
    private Email email;
    private PhoneNumber phoneNumber;
    private List<Appointment> appointments = new ArrayList<>();

    public Veterinary(String name, String email, String phoneNumber){
        this.name = new Name(name);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        if(id == null)
            throw new IllegalArgumentException("Id can not be null!");
        this.id = id;
    }

    public String getName() {
        return this.name.getValue();
    }

    public void setName(String name) {
        this.name = new Name(name);
    }

    public String getEmail(){
        return this.email.getValue();
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public String getPhoneNumber() {
        return this.phoneNumber.getValue();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public void replace(String name, String phoneNumber, String email){
        if(name != null)
            this.setName(name);
        if(phoneNumber != null)
            this.setPhoneNumber(phoneNumber);
        if(email != null)
            this.setEmail(email);
    }

}
