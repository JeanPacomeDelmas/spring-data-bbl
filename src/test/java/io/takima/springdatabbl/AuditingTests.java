package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.service.BarmanService;
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

import java.time.Instant;

import static io.takima.springdatabbl.TestSetupUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
public class AuditingTests {

    @Autowired
    BarmanService barmanService;

    @Autowired
    CocktailService cocktailService;

    @InjectSoftAssertions
    SoftAssertions softly;

    Barman jp;

    Barman valentin;

    @BeforeEach
    void beforeEach() {
        valentin = barmanService.save(getValentin());
        jp = barmanService.save(getJp());

        cocktailService.save(getMojito().setBarman(jp));
        cocktailService.save(getSexOnTheBeach().setBarman(valentin));
        cocktailService.save(getDaiquiri().setBarman(valentin));
    }

    @Test
    void shouldAddCreatedDateAndTrackCreatedBy() {
        String expectedCreatedBy = "dummy";

        Instant barmanCreatedDate = jp.getCreatedDate();
        var barmanCreatedBy = jp.getCreatedBy();

        assertThat(barmanCreatedDate).isNotNull();
        assertThat(barmanCreatedBy).isEqualTo(expectedCreatedBy);
    }


}
