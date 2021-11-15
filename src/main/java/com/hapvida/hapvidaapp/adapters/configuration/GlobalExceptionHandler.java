package com.hapvida.hapvidaapp.adapters.configuration;

import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, ResourceNotFound.class})
    public ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception exception, WebRequest request){
        var exceptionResponse = new ExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
