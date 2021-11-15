package com.hapvida.hapvidaapp.adapters.configuration;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    private final LocalDateTime timestamp;
    private final String message;
    private final String description;


    public ExceptionResponse(LocalDateTime timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
