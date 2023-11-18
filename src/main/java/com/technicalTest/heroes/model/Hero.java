package com.technicalTest.heroes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long heroId;

    @Column
    private String heroName;

    @Column
    private String power;

    @Column
    private String realName;


}
