package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.component.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 *  Exception Handling */
@ControllerAdvice
public class ControllersAdvice {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardResponse> handleMissingParams(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(
                new StandardResponse(HttpStatus.BAD_REQUEST, "", "Missing param '"+e.getParameterName()+"'"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {

        return ResponseEntity.badRequest().body(
                new StandardResponse(HttpStatus.BAD_REQUEST, "", "Wrong param type for '"+e.getName()+"'"));
    }

}
