package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.service.BarmanCachedService;
import io.takima.springdatabbl.service.CocktailService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import static io.takima.springdatabbl.TestSetupUtil.*;

@Slf4j
@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
class CachingTests {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    BarmanCachedService barmanCachedService;

    @Autowired
    CocktailService cocktailService;

    @InjectSoftAssertions
    SoftAssertions softly;

    Barman jp;

    Barman valentin;

    @BeforeEach
    void beforeEach() {
        valentin = barmanCachedService.save(getValentin());
        jp = barmanCachedService.save(getJp());

        cocktailService.save(getMojito().setBarman(jp));
        cocktailService.save(getSexOnTheBeach().setBarman(valentin));
        cocktailService.save(getDaiquiri().setBarman(valentin));
    }

    // TODO: 5/12/2024 remove
//    @Test
//    void shouldCacheCocktailsPerBarman() {
//        log.info("JP1 :: {}", cocktailService.getAllCocktailsByBarmanId(jp.getId()));
//        log.info("VD1 :: {}", cocktailService.getAllCocktailsByBarmanId(valentin.getId()));
//        log.info("JP2 :: {}", cocktailService.getAllCocktailsByBarmanId(jp.getId()));
//
//        System.out.println();
//    }

    // OU BIEN

    // TODO: 5/11/2024 should cache every cocktail with their ingredients
    @Test
    void shouldCacheEveryCocktailWithTheirIngredients() {
        log.info("1 jp) :: {}", barmanCachedService.getById(jp.getId()));
        log.info("1 valentin) :: {}", barmanCachedService.getById(valentin.getId()));

        log.info("2 jp) :: {}", barmanCachedService.getById(jp.getId()));
        log.info("2 valentin) :: {}", barmanCachedService.getById(valentin.getId()));

        printCacheContents("barmans");
    }

    // TODO: 5/11/2024 should evict cocktails on update, unless ??

    // TODO: 5/11/2024 should put("evict&update"?) = update cached cocktails entries upon any ingredient change
    // also check all ingredients are updated throughout "barmanCocktails" cache;

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