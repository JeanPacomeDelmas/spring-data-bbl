package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static io.takima.springdatabbl.SetupUtil.getMojito;
import static io.takima.springdatabbl.SetupUtil.getPinaColada;
import static io.takima.springdatabbl.SetupUtil.getSexOnTheBeach;
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
    JdbcTemplate jdbcTemplate;

    @BeforeAll
    void beforeAll() {
        Barman valentin = barmanService.save(new Barman().setName("Valentin"));
        Barman jp = barmanService.save(new Barman().setName("JP"));

        cocktailService.save(getMojito().setBarman(jp));
        cocktailService.save(getSexOnTheBeach().setBarman(valentin));
    }

    @AfterAll
    void afterAll() {
        jdbcTemplate.execute("TRUNCATE TABLE barman cascade");
        jdbcTemplate.execute("TRUNCATE TABLE cocktail cascade");
        jdbcTemplate.execute("TRUNCATE TABLE cocktail_ingredients cascade");
        jdbcTemplate.execute("ALTER SEQUENCE barman_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE cocktail_seq RESTART WITH 1");
    }

    @AfterEach
    void tearDown() {
        cocktailService.deleteById(3L);
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
        var pinaColada = cocktailService.saveWithBarmanByReference(getPinaColada(), 2L);
        assertThat(pinaColada).extracting("barman.name").isEqualTo("JP");
    }

    @Test
    void updatePinaColada_withFind() {
        var pinaColada = cocktailService.saveWithBarmanByFind(getPinaColada(), 2L);
        assertThat(pinaColada).extracting("barman.name").isEqualTo("JP");
    }
}
