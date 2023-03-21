package be.technobel.materialloc.controller;

import be.technobel.materialloc.exceptions.InvalidRefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidRefreshTokenException.class)
    public void handleForbidden(){}

}
