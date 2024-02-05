package com.modelo.consumption.application.domain.exception;

public class ConsumptionException extends RuntimeException{
    private String message;
    public ConsumptionException(String message){
        super(message);
    }
}
