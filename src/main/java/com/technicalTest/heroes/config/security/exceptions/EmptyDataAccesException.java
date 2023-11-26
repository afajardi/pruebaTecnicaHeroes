package com.technicalTest.heroes.config.security.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class EmptyDataAccesException extends EmptyResultDataAccessException{

    private String code;
    private HttpStatus status;

    public EmptyDataAccesException(String msg, int expectedSize, String code, HttpStatus status) {
        super(msg, expectedSize);
        this.code = code;
        this.status = status;
    }

    
    
}
