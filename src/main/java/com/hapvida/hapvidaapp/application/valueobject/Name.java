package com.hapvida.hapvidaapp.application.valueobject;

import lombok.Getter;

@Getter
public class Name {
    private final String value;

    public Name(String name){
        if(name == null)
            throw new IllegalArgumentException("Name can not be null");
        if(name.isBlank())
            throw new IllegalArgumentException("Name can not be blank");
        this.value = name;
    }
}
