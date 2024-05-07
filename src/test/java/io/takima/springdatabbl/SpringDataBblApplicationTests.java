package io.takima.springdatabbl;

import io.takima.springdatabbl.model.*;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static io.takima.springdatabbl.TestSetupUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class SpringDataBblApplicationTests {

    @Autowired
    BarmanService barmanService;

    @Autowired
    CocktailService cocktailService;

    @InjectSoftAssertions
    SoftAssertions softly;

    @BeforeAll
    void beforeAll() {
        Barman valentin = barmanService.save(getValentin());
        Barman jp = barmanService.save(getJp());

        cocktailService.save(getMOJITO().setBarman(jp));
        cocktailService.save(getSexOnTheBeach().setBarman(valentin));
        cocktailService.save(getDaiquiri().setBarman(valentin));
    }

    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NPlus1Problem {
        @Test
        @Order(1)
        void findValentinById_withoutCocktails() {
            var valentin = barmanService.findByIdWithoutCocktails(1L);
            softly.assertThat(valentin)
                    .extracting(Barman.Fields.name)
                    .isEqualTo(VALENTIN);
            softly.assertThatExceptionOfType(LazyInitializationException.class)
                    .isThrownBy(() -> valentin.getCocktails().toString());
        }

        @Test
        @Order(2)
        void findValentinById_withLazyCocktails() {
            var valentin = barmanService.findByIdWithLazyCocktails(1L);
            softly.assertThat(valentin)
                    .extracting(Barman.Fields.name)
                    .isEqualTo(VALENTIN);
            softly.assertThat(valentin.getCocktails())
                    .hasSize(2)
                    .extracting(Cocktail.Fields.name)
                    .isEqualTo(List.of(SEX_ON_THE_BEACH, DAIQUIRI));
        }

        @Test
        @Order(3)
        void findValentinById_withEagerCocktails() {
            var valentin = barmanService.findByIdWithEagerCocktails(1L);
            softly.assertThat(valentin)
                    .extracting(Barman.Fields.name)
                    .isEqualTo(VALENTIN);
            softly.assertThat(valentin.getCocktails())
                    .hasSize(2)
                    .extracting(Cocktail.Fields.name)
                    .isEqualTo(List.of(SEX_ON_THE_BEACH, DAIQUIRI));
        }
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class FindVsReference {
        @AfterEach
        void tearDown() {
            cocktailService.deleteByName(getPinaColada().getName());
        }

        @Test
        @Order(4)
        void updatePinaColada_withFind() {
            var pinaColada = cocktailService.saveWithBarmanByFind(getPinaColada(), 2L);
            softly.assertThat(pinaColada.getName()).isEqualTo(PINA_COLADA);
            softly.assertThat(pinaColada).extracting("barman.name").isEqualTo(JP);
        }

        @Test
        @Order(5)
        void updatePinaColada_withReference() {
            var pinaColada = cocktailService.saveWithBarmanByReference(getPinaColada(), 2L);
            softly.assertThat(pinaColada.getName()).isEqualTo(PINA_COLADA);
            softly.assertThatExceptionOfType(LazyInitializationException.class)
                    .isThrownBy(() -> pinaColada.getBarman().getName());
        }
    }

    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class BadNamedQuery {
        @Test
        @Order(6)
        void findJPByCocktail_containing() {
            var mojito = cocktailService.getReferenceById(1L);
            var jp = barmanService.findByCocktailsContaining(mojito);
            assertThat(jp)
                    .extracting(Barman.Fields.name)
                    .isEqualTo(JP);
        }

        @Test
        @Order(7)
        void findJPByCocktailId() {
            var jp = barmanService.findByCocktailsId(1L);
            assertThat(jp)
                    .extracting(Barman.Fields.name)
                    .isEqualTo(JP);
        }

        @Test
        @Order(8)
        void findJPByCocktail() {
            var mojito = cocktailService.getReferenceById(1L);
            var jp = barmanService.findByCocktail(mojito);
            assertThat(jp)
                    .extracting(Barman.Fields.name)
                    .isEqualTo(JP);
        }

        @Test
        @Order(9)
        void findAllByCocktailsIngredientsContaining() {
            var barmans = barmanService.findAllByCocktailsIngredientsContaining(Ingredient.WHITE_RHUM);
            assertThat(barmans)
                    .hasSize(2)
                    .extracting(Barman.Fields.name)
                    .isEqualTo(List.of(JP, VALENTIN));
        }
    }
}
