package io.takima.springdatabbl.service;

import io.takima.springdatabbl.dao.BarmanRepository;
import io.takima.springdatabbl.dao.CocktailRepository;
import io.takima.springdatabbl.model.Barman;
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

    private final BarmanRepository barmanRepository;
    private final CocktailRepository cocktailRepository;

    public Cocktail findById(Long id) {
        Cocktail cocktail = cocktailRepository.findById(id).orElseThrow();
        log.info("Cocktail by id: {}", cocktail);
        return cocktail;
    }

    @Transactional
    public Cocktail saveWithBarmanByReference(Cocktail cocktail, Long barmanId) {
        Barman barman = barmanRepository.getReferenceById(barmanId);
        return save(cocktail.setBarman(barman));
    }

    @Transactional
    public Cocktail saveWithBarmanByFind(Cocktail cocktail, Long barmanId) {
        Barman barman = barmanRepository.findById(barmanId).orElseThrow();
        return save(cocktail.setBarman(barman));
    }

    @Transactional
    public Cocktail save(Cocktail cocktail) {
        Cocktail saved = cocktailRepository.save(cocktail);
        log.info("Saved cocktail: {}, with barman: {}", saved, saved.getBarman());
        return saved;
    }

    public void deleteById(Long id) {
        cocktailRepository.deleteById(id);
    }
}
