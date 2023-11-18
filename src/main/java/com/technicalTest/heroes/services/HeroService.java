package com.technicalTest.heroes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.technicalTest.heroes.model.Hero;
import com.technicalTest.heroes.model.HeroDto;
import com.technicalTest.heroes.repository.HeroRepository;

@Service
public class HeroService {
    
    
    private HeroRepository heroRepo;
    private final ModelMapper mapper;

   
    public HeroService(HeroRepository heroRepo) {
        this.heroRepo = heroRepo;
        mapper = new ModelMapper();
    }

    public ResponseEntity<String> createHero(HeroDto hero){
        Hero heroCreated = heroRepo.save(mapper.map(hero, Hero.class));
        return new ResponseEntity<>("Hero with id: " + heroCreated.getHeroId()+ " was created",HttpStatus.CREATED);

    }

    public HeroDto getHeroById(Long heroId){
        Hero hero = heroRepo.findByHeroId(heroId);
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
        heroRepo.deleteById(heroId);
        return new ResponseEntity<>("Hero with id: " + heroId + " was deleted", HttpStatus.OK);
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
