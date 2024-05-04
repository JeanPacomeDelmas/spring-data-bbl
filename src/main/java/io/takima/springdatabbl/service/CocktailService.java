package io.takima.springdatabbl.service;

import io.takima.springdatabbl.dao.CocktailRepository;
import io.takima.springdatabbl.model.Cocktail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public Cocktail findById(Long id) {
        Cocktail cocktail = cocktailRepository.findById(id).orElseThrow();
        log.info("findById: {}", cocktail);
        return cocktail;
    }


    @Transactional
    public Cocktail save(Cocktail cocktail) {
        return cocktailRepository.save(cocktail);
    }
}
