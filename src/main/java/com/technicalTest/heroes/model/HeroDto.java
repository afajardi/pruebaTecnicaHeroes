package com.technicalTest.heroes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter@Setter
@AllArgsConstructor
public class HeroDto {


    private Long heroId;

    private String heroName;

    private String power;

    private String realName; 

    
}
