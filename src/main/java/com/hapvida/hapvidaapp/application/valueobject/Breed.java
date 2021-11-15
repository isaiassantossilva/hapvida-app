package com.hapvida.hapvidaapp.application.valueobject;

import lombok.Getter;

@Getter
public class Breed {
    private final String value;

    public Breed(String breed){
        if(breed == null)
            throw new IllegalArgumentException("Breed can not be null");
        if(breed.isBlank())
            throw new IllegalArgumentException("Breed can not be blank");
        this.value = breed;
    }
}
