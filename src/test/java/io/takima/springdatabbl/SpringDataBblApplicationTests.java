package io.takima.springdatabbl;

import io.takima.springdatabbl.dao.CocktailRepository;
import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.takima.springdatabbl.TestSetupUtil.*;

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

    @Autowired
    CocktailRepository cocktailRepository;

    @InjectSoftAssertions
    SoftAssertions softly;

    @BeforeAll
    void beforeAll() {
        Barman valentin = barmanService.save(getValentin());
        Barman jp = barmanService.save(getJp());

        cocktailService.save(getMojito().setBarman(jp));
        cocktailService.save(getSexOnTheBeach().setBarman(valentin));
        cocktailService.save(getDaiquiri().setBarman(valentin));
    }

    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class FetchStrategy {
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class FindVsReference {
        @AfterEach
        void tearDown() {
            cocktailService.deleteByName(getPinaColada().getName());
        }
    }

    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class MoreComplexQuery {
    }

    @Nested
    @Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Projection {
    }
}
