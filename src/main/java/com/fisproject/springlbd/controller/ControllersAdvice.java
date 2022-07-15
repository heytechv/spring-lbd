package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.component.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 *  Exception Handling */
@ControllerAdvice
public class ControllersAdvice {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardResponse> handleMissingParams(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(
                new StandardResponse(HttpStatus.BAD_REQUEST, "", "Missing param "+e.getParameterName()));
    }

}
