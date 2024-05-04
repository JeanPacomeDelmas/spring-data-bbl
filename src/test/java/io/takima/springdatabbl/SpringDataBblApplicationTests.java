package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.model.Ingredient;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringDataBblApplicationTests {

    @Autowired
    BarmanService barmanService;

    @Autowired
    CocktailService cocktailService;

    @Autowired
    EntityManager entityManager;

    @BeforeAll
    void beforeAll() {
        Barman valentin = barmanService.save(new Barman().setName("Valentin"));
        Barman jp = barmanService.save(new Barman().setName("JP"));

        Cocktail mojito = cocktailService.save(
                new Cocktail()
                        .setName("mojito")
                        .setBarman(jp)
                        .setIngredients(List.of(Ingredient.MINT, Ingredient.WHITE_RHUM, Ingredient.LEMON, Ingredient.LEMONADE))
        );

        Cocktail sexOnTheBeach = cocktailService.save(
                new Cocktail()
                        .setName("sex on the beach")
                        .setBarman(valentin)
                        .setIngredients(List.of(Ingredient.VODKA, Ingredient.ORANGE_JUICE, Ingredient.CRANBERRY_JUICE))
        );

        Cocktail pinaColada = cocktailService.save(
                new Cocktail()
                        .setName("pina colada")
                        .setIngredients(List.of(Ingredient.WHITE_RHUM, Ingredient.AMBER_RHUM, Ingredient.PINEAPPLE_JUICE, Ingredient.COCONUT_MILK))
        );
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void findValentinById_withoutCocktails() {
        var valentin = barmanService.findByIdWithoutCocktails(1L);
        assertThat(valentin).extracting("name").isEqualTo("Valentin");
    }

    @Test
    void findValentinById_withLazyCocktails() {
        var valentin = barmanService.findByIdWithLazyCocktails(1L);
        assertThat(valentin).extracting("name").isEqualTo("Valentin");
    }

    @Test
    void findValentinById_findById_withEagerCocktails() {
        var valentin = barmanService.findByIdWithEagerCocktails(1L);
        assertThat(valentin).extracting("name").isEqualTo("Valentin");
    }

    @Test
    void updatePinaColada_withReference() {
        String newName = "Valentin le bg trop con";
        var valentin = barmanService.updateNameWithReference(1L, newName);
        assertThat(valentin).extracting("name").isEqualTo(newName);
    }

    @Test
    void updatePinaColada_withFind() {
        String newName = "Valentin le bg";
        var valentin = barmanService.updateNameWithFind(1L, newName);
        assertThat(valentin).extracting("name").isEqualTo(newName);
    }
}
