package com.technicalTest.heroes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.technicalTest.heroes.model.HeroDto;
import com.technicalTest.heroes.repository.HeroRepository;
import com.technicalTest.heroes.services.HeroService;

@ExtendWith(MockitoExtension.class)
public class HeroesServicesTest {

    @Mock
	private HeroRepository heroRepo;

	@InjectMocks
	private HeroService heroServ;


    @Test
	void heroNameWithMan() {

		String textFilter = "man";
		Long totalHeroesWithFilter = 2L;

		List<HeroDto> heroesList = new ArrayList<>();

		HeroDto h1 = new HeroDto(1L, "Spiderman", "telaraña", "Peter Parker");
		HeroDto h2 = new HeroDto(1L, "Capitan America", "fuerza", "Steve Rogers");
		HeroDto h3 = new HeroDto(1L, "BatMan", "fuerza", "Bruce Wayne");
		heroesList.add(h1);
		heroesList.add(h2);
		heroesList.add(h3);

		List<HeroDto> heroesListFilter = heroServ.heroesWithTextFilter(textFilter, heroesList);
		assertEquals(totalHeroesWithFilter, heroesListFilter.size());


	}

    @Test
	public void createHero(){
		HeroDto hero = new HeroDto();
		hero.setHeroName("Batman");
		hero.setPower("fuerza");
		hero.setRealName("Bruce Wayne");
		ResponseEntity<String> response =  heroServ.createHero(hero);

		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		
	}

    
}
