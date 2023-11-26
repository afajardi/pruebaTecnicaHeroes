package com.technicalTest.heroes.config.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionErrorDto {

    private String codeError;

    private String messageError;
    
}
