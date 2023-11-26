package com.technicalTest.heroes.controller;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technicalTest.heroes.model.HeroDto;
import com.technicalTest.heroes.services.HeroService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/heroes")
public class HeroController {

    private HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/heroList")
    @ApiOperation("List all heroes")
    public List<HeroDto> getAllHeroes(){
        return heroService.getAllHeroes();
    }

    @GetMapping("/heroesWithFilter")
    @ApiOperation("List all heroes with filter")
    public List<HeroDto> getAllHeroesWithMan(@RequestParam("textFilter") String textFilter){

        List<HeroDto> allHeroes = heroService.getAllHeroes();

        return heroService.heroesWithTextFilter(textFilter,allHeroes);
    }

    @GetMapping("/getHeroById/{heroId}")
    @ApiOperation("Get hero by heroId")
    public HeroDto findHeroById (@PathVariable Long heroId){
        return heroService.getHeroById(heroId);

    }

    @PutMapping("/updateHero")
    @ApiOperation("Update data hero")
    public HeroDto updateHero(@RequestBody HeroDto hero){

        return heroService.updateHero(hero);

    }
    

    @DeleteMapping("/deleteHero/{heroId}")
    @ApiOperation("Delete hero of the indicated id")
    public ResponseEntity<String> deleteHero(@PathVariable Long heroId){
        return heroService.deleteHero(heroId);

    }

    @PostMapping("/addHero")
    @ApiOperation("Create a new hero")
    public ResponseEntity<String> createHero(@RequestBody HeroDto hero){
        
        return heroService.createHero(hero);
    }
    
}
