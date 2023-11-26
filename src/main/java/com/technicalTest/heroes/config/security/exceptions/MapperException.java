package com.technicalTest.heroes.config.security.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class MapperException extends IllegalArgumentException{

    private String code;
    private HttpStatus status;


    public MapperException(String code, HttpStatus status,String message){
        super(message);
        this.code = code;
        this.status = status;

    }
    
}
