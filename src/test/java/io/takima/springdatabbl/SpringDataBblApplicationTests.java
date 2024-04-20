package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.model.Ingredient;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringDataBblApplicationTests {

    @Autowired
    BarmanService barmanService;

    @Autowired
    CocktailService cocktailService;

    @BeforeAll
    void beforeAll() {
        Barman valentin = barmanService.save(new Barman().setName("Valentin"));
        Barman jp = barmanService.save(new Barman().setName("JP"));

        Cocktail mojito = cocktailService.save(
                new Cocktail()
                        .setName("mojito")
                        .setIngredients(Set.of(Ingredient.MINT, Ingredient.WHITE_RHUM, Ingredient.LEMON, Ingredient.LEMONADE))
                        .setBarmans(List.of(valentin))
        );
    }

    @Test
    void contextLoads() {
        var barmans = barmanService.findAll();
        log.info(barmans.toString());
    }
}
