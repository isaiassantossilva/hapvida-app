package com.hapvida.hapvidaapp.application.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message){
        super(message);
    }
}
