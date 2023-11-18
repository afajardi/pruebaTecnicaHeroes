package com.technicalTest.heroes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    
}
