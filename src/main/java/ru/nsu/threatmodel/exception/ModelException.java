package ru.nsu.threatmodel.exception;

public class ModelException extends RuntimeException{
    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, Exception cause) {
        super(message, cause);
    }
}
