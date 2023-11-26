package com.technicalTest.heroes.config.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.technicalTest.heroes.config.security.dto.ExceptionErrorDto;
import com.technicalTest.heroes.config.security.exceptions.EmptyDataAccesException;
import com.technicalTest.heroes.config.security.exceptions.MapperException;

@ControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(value = MapperException.class)
    public ResponseEntity<ExceptionErrorDto> handelExcepton(MapperException e) {

        ExceptionErrorDto error = ExceptionErrorDto.builder().codeError(e.getCode()).messageError(e.getMessage()).build();
        return new ResponseEntity<>(error,e.getStatus());

    }

    @ExceptionHandler(value = EmptyDataAccesException.class)
    public ResponseEntity<ExceptionErrorDto> handelExcepton(EmptyDataAccesException e) {

        ExceptionErrorDto error = ExceptionErrorDto.builder().codeError(e.getCode()).messageError(e.getMessage()).build();
        return new ResponseEntity<>(error,e.getStatus());

    }


    
}
