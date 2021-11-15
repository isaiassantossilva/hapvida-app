package com.hapvida.hapvidaapp.application.valueobject;

import lombok.Getter;

@Getter
public class Email {
    private final String value;

    public Email(String email) {
        if(email == null)
            throw new IllegalArgumentException("Email can not be null");
        if(email.isBlank())
            throw new IllegalArgumentException("Email can not be blank");
        this.value = email.trim();
    }
}
