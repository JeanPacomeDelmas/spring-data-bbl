package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.takima.springdatabbl.SetupUtil.getMojito;
import static io.takima.springdatabbl.SetupUtil.getPinaColada;
import static io.takima.springdatabbl.SetupUtil.getSexOnTheBeach;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringDataBblApplicationTests {

    @Autowired
    BarmanService barmanService;

    @Autowired
    CocktailService cocktailService;

    @InjectSoftAssertions
    SoftAssertions softly;

    @BeforeAll
    void beforeAll() {
        Barman valentin = barmanService.save(new Barman().setName("Valentin"));
        Barman jp = barmanService.save(new Barman().setName("JP"));

        cocktailService.save(getMojito().setBarman(jp));
        cocktailService.save(getSexOnTheBeach().setBarman(valentin));
    }

    @AfterEach
    void tearDown() {
        cocktailService.deleteByName(getPinaColada().getName());
    }

    @Test
    @Order(1)
    void findValentinById_withoutCocktails() {
        var valentin = barmanService.findByIdWithoutCocktails(1L);
        softly.assertThat(valentin)
                .extracting("name")
                .isEqualTo("Valentin");
        softly.assertThatExceptionOfType(LazyInitializationException.class)
                .isThrownBy(() -> valentin.getCocktails().toString());
    }

    @Test
    @Order(2)
    void findValentinById_withLazyCocktails() {
        var valentin = barmanService.findByIdWithLazyCocktails(1L);
        softly.assertThat(valentin)
                .extracting("name")
                .isEqualTo("Valentin");
        softly.assertThat(valentin.getCocktails())
                .singleElement()
                .extracting("name")
                .isEqualTo("sex on the beach");
    }

    @Test
    @Order(3)
    void findValentinById_findById_withEagerCocktails() {
        var valentin = barmanService.findByIdWithEagerCocktails(1L);
        softly.assertThat(valentin)
                .extracting("name")
                .isEqualTo("Valentin");
        softly.assertThat(valentin.getCocktails())
                .singleElement()
                .extracting("name")
                .isEqualTo("sex on the beach");
    }

    @Test
    @Order(4)
    void updatePinaColada_withFind() {
        var pinaColada = cocktailService.saveWithBarmanByFind(getPinaColada(), 2L);
        assertThat(pinaColada).extracting("barman.name").isEqualTo("JP");
    }

    @Test
    @Order(5)
    void updatePinaColada_withReference() {
        var pinaColada = cocktailService.saveWithBarmanByReference(getPinaColada(), 2L);
        assertThat(pinaColada).extracting("barman.name").isEqualTo("JP");
    }
}
