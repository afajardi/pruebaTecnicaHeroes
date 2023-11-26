package com.technicalTest.heroes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.technicalTest.heroes.config.security.exceptions.EmptyDataAccesException;
import com.technicalTest.heroes.config.security.exceptions.MapperException;
import com.technicalTest.heroes.model.Hero;
import com.technicalTest.heroes.model.HeroDto;
import com.technicalTest.heroes.repository.HeroRepository;

@Service
public class HeroService {
    
    
    private HeroRepository heroRepo;
    private final ModelMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(HeroService.class);

   
    public HeroService(HeroRepository heroRepo) {
        this.heroRepo = heroRepo;
        mapper = new ModelMapper();
    }

    public ResponseEntity<String> createHero(HeroDto hero){
        Hero heroCreated = heroRepo.save(mapper.map(hero, Hero.class));
        LOGGER.info("Hero created: " + heroCreated);
        return new ResponseEntity<>("Hero with id: " + heroCreated.getHeroId()+ " was created",HttpStatus.CREATED);

    }

    public HeroDto getHeroById(Long heroId){
        Hero hero = heroRepo.findByHeroId(heroId);
        if(hero == null){
            LOGGER.error("Hero with id: " + heroId + "nout found");
            throw new MapperException("E-401", HttpStatus.BAD_REQUEST ,"Hero with id " + heroId + " nout found");
        }
        return mapper.map(hero, HeroDto.class);
    }

  
    public HeroDto updateHero(HeroDto heroDto){
        
        Hero hero = heroRepo.findByHeroId(heroDto.getHeroId());

        hero.setHeroName(heroDto.getHeroName());
        hero.setPower(heroDto.getPower());
        hero.setRealName(heroDto.getRealName());
        heroRepo.save(hero);

        return mapper.map(hero, HeroDto.class);
    }

      public List<HeroDto> getAllHeroes(){

       return mapperHeros(heroRepo.findAll());
        
    }

     public ResponseEntity<String> deleteHero(Long heroId){

        try {
            heroRepo.deleteById(heroId);
             return new ResponseEntity<>("Hero with id: " + heroId + " was deleted", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
              
            LOGGER.error("Hero entity with "+ heroId +" does not exist",e);
            throw new EmptyDataAccesException("Hero entity with "+ heroId +" does not exist", 0, "E-405" ,HttpStatus.INTERNAL_SERVER_ERROR );
        }
        
       
    }


    public  List<HeroDto> heroesWithTextFilter(String parameterFilter, List<HeroDto> heroesList){

        return heroesList.stream()
                .filter(h -> h.getHeroName().toLowerCase().contains(parameterFilter.toLowerCase()))
                .collect(Collectors.toList());

    }

    private List<HeroDto> mapperHeros(List<Hero> heroesEntities){
       
        return heroRepo.findAll().stream()
                .map(hero -> mapper.map(hero, HeroDto.class))
                .collect(Collectors.toList());
    }
}
