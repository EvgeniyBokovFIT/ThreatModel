package ru.nsu.threatmodel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nsu.threatmodel.dto.AppError;
import ru.nsu.threatmodel.exception.AuthException;
import ru.nsu.threatmodel.exception.RegistrationException;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<AppError> registrationExceptionHandler(RegistrationException exception) {
        return ResponseEntity.badRequest().body(new AppError(exception.getMessage()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<AppError> authenticationExceptionHandler(AuthException exception) {
        return ResponseEntity.badRequest().body(new AppError(exception.getMessage()));
    }
}
