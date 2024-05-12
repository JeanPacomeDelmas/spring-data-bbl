package io.takima.springdatabbl.service;

import io.takima.springdatabbl.Monitored;
import io.takima.springdatabbl.dao.BarmanRepository;
import io.takima.springdatabbl.dao.CocktailRepository;
import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.utils.SlowServiceSimulator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CocktailService {

    private final BarmanRepository barmanRepository;
    private final CocktailRepository cocktailRepository;

    @Monitored
    @Transactional
    public Cocktail save(Cocktail cocktail) {
        Cocktail saved = cocktailRepository.save(cocktail);
        log.info("Saved cocktail: {}, with barman: {}", saved, saved.getBarman());
        return saved;
    }

    @Monitored
    @Transactional
    public void deleteByName(String name) {
        cocktailRepository.deleteByName(name);
    }

    @Monitored
    public Cocktail getReferenceById(Long id) {
        return cocktailRepository.getReferenceById(id);
    }

    @Monitored
    @Transactional
    public Cocktail saveWithBarmanByReference(Cocktail cocktail, Long barmanId) {
        Barman barman = barmanRepository.getReferenceById(barmanId);
        return saveCocktail(cocktail.setBarman(barman));
    }

    private Cocktail saveCocktail(Cocktail cocktail) {
        Cocktail saved = cocktailRepository.save(cocktail);
        log.info("Saved cocktail: {}", saved);
        return saved;
    }

    @Monitored
    @Transactional
    public Cocktail saveWithBarmanByFind(Cocktail cocktail, Long barmanId) {
        Barman barman = barmanRepository.findById(barmanId).orElseThrow();
        return saveCocktail(cocktail.setBarman(barman));
    }



    @Monitored
    @Cacheable("barmanCocktails")
    public List<Cocktail> getAllCocktailsByBarmanId(Long barmanId) {
        SlowServiceSimulator.simulateSlowThreeSecondsRequest();
        var barman = cocktailRepository.findByBarmanId(barmanId);
        printCacheContents("barmanCocktails");
        return barman;
    }

    @Monitored
    @Transactional
    @CachePut(value = "barmanCocktails", key = "#barmanId")
    public Cocktail updateCocktailName(Long cocktailId, String newName, Long barmanId) {
        Cocktail cocktail = cocktailRepository.findById(cocktailId).orElseThrow();
        cocktail.setName(newName);
        cocktailRepository.save(cocktail);
        return cocktail;
    }

    @Monitored
    @Transactional
    @CachePut(value = "barmanCocktails", key = "#cocktail.id")
    public Cocktail updateCocktail(Cocktail cocktail) {
        Cocktail updated = cocktailRepository.save(cocktail);
        log.info("Updated cocktail: {}", updated);
        return updated;
    }

//    @Monitored
//    @Transactional
//    @CachePut(value = "barmanCocktails", key = "#barmanId")
//    public Cocktail updateCocktailIngredients(Long cocktailId, List<Ingredient> newIngredients, Long barmanId) {
//        Cocktail cocktail = cocktailRepository.findById(cocktailId).orElseThrow();
//        cocktail.setIngredients(newIngredients);
//        cocktailRepository.save(cocktail);
//        return cocktail;
//    }

    @Autowired
    CacheManager cacheManager;

    public void printCacheContents(String cacheName) {
        Cache barmanCocktailsCache = cacheManager.getCache(cacheName);
        if (barmanCocktailsCache != null) {
            ConcurrentMapCache concurrentMapCache = (ConcurrentMapCache) barmanCocktailsCache;
            ConcurrentMap<Object, Object> cacheMap = concurrentMapCache.getNativeCache();

            for (Map.Entry<Object, Object> entry : cacheMap.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        } else {
            System.out.println("Cache not found");
        }
    }
}
