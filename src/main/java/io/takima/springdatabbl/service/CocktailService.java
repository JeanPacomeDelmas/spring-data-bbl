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

    public Cocktail findById(Long vehicleId) {
        Cocktail cocktail = cocktailRepository.findById(vehicleId).orElseThrow();
        log.info("findById: {}", cocktail);
        return cocktail;
    }

    public Cocktail findByIdWithBarmans(Long vehicleId) {
        Cocktail cocktail = cocktailRepository.findByIdWithBarmans(vehicleId).orElseThrow();
        log.info("findByIdWithBarmans: {}", cocktail);
        return cocktail;
    }

//    public Cocktail getReferenceById(Long id) {
//        return cocktailRepository.getReferenceById(id);
//    }

    @Transactional
    public Cocktail save(Cocktail cocktail) {
        return cocktailRepository.save(cocktail);
    }
}
