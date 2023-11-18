package com.technicalTest.heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technicalTest.heroes.model.Hero;



@Repository
public interface HeroRepository extends JpaRepository<Hero,Long>{

    public Hero findByHeroId(Long heroId);
    
}
