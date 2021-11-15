package com.hapvida.hapvidaapp.application.entity;

import lombok.Getter;

@Getter
public class PhoneNumber {
    private final String value;

    public PhoneNumber(String phoneNumber) {
        if(phoneNumber == null)
            throw new IllegalArgumentException("Phone number can not be null");
        if(phoneNumber.isBlank())
            throw new IllegalArgumentException("Phone number can not be blank");
        this.value = phoneNumber;
    }
}
