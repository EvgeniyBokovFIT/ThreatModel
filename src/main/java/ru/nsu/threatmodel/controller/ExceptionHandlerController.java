package ru.nsu.threatmodel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nsu.threatmodel.exception.RegistrationException;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<?> registrationExceptionHandler(RegistrationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
