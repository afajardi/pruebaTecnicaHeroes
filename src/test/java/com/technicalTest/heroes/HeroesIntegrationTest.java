package com.technicalTest.heroes;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.technicalTest.heroes.model.HeroDto;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroesIntegrationTest {

    private final String  BASE_URL = "/heroes";
    
    @Autowired
    private MockMvc mock;

    @Test
    public void addHero() throws Exception{

        HeroDto hero = new HeroDto();
        hero.setHeroName("Spiderman");
        hero.setPower("telaraña");
        hero.setRealName("Peter Parker");

        mock.perform(post(BASE_URL + "/addHero")
            .content(asJsonString(hero))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.heroName").value(hero.getHeroName()));
            
    }

    @Test
    public void testGetAllHeroes() throws Exception{
        mock.perform(get(BASE_URL + "/heroList"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray());
    }


    private String asJsonString(HeroDto hero) {
        try {
            return new ObjectMapper().writeValueAsString(hero);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
