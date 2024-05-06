package io.takima.springdatabbl.service;

import io.takima.springdatabbl.Monitored;
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

    @Monitored
    public Cocktail findById(Long id) {
        Cocktail cocktail = cocktailRepository.findById(id).orElseThrow();
        log.info("Cocktail by id: {}", cocktail);
        return cocktail;
    }

    @Monitored
    @Transactional
    public Cocktail saveWithBarmanByReference(Cocktail cocktail, Long barmanId) {
        Barman barman = barmanRepository.getReferenceById(barmanId);
        return cocktailRepository.save(cocktail.setBarman(barman));
    }

    @Monitored
    @Transactional
    public Cocktail saveWithBarmanByFind(Cocktail cocktail, Long barmanId) {
        Barman barman = barmanRepository.findById(barmanId).orElseThrow();
        return cocktailRepository.save(cocktail.setBarman(barman));
    }

    @Monitored
    @Transactional
    public Cocktail save(Cocktail cocktail) {
        return saveCocktail(cocktail);
    }

    private Cocktail saveCocktail(Cocktail cocktail) {
        Cocktail saved = cocktailRepository.save(cocktail);
        log.info("Saved cocktail: {}, with barman: {}", saved, saved.getBarman());
        return saved;
    }

    @Monitored
    @Transactional
    public void deleteByName(String name) {
        cocktailRepository.deleteByName(name);
    }
}
