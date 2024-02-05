package com.modelo.consumption.application.domain.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ConsumptionExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ConsumptionException.class)
    public ErrorDTO handler(ConsumptionException ex){
        return new ErrorDTO(ex.getMessage());
    }

}
