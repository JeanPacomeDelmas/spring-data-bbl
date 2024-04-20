package io.takima.springdatabbl;

import io.takima.springdatabbl.dao.BarmanRepository;
import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.model.Ingredient;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class SpringDataBblApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataBblApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CocktailService cocktailService, BarmanService barmanService,
                             BarmanRepository barmanRepository) {
        return args -> {
            Cocktail mojito = cocktailService.save(
                    new Cocktail()
                            .setName("mojito")
                            .setIngredients(Set.of(Ingredient.MINT, Ingredient.WHITE_RHUM, Ingredient.LEMON, Ingredient.LEMONADE))
            );

            Barman valentin = barmanService.save(new Barman().setName("Valentin").setCocktails(List.of(mojito)));
            Barman jp = barmanService.save(new Barman().setName("JP"));

            barmanService.findById(jp.getId());
            barmanService.findByIdWithCocktails(jp.getId());
        };
    }
}
