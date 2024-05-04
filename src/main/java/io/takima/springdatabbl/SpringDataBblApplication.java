package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.model.Ingredient;
import io.takima.springdatabbl.service.BarmanService;
import io.takima.springdatabbl.service.CocktailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringDataBblApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataBblApplication.class, args);
    }

//    @Bean
    CommandLineRunner runner(CocktailService cocktailService, BarmanService barmanService) {
        return args -> {
            Barman valentin = barmanService.save(new Barman().setName("Valentin")/*.setCocktails(List.of(sexOnTheBeach))*/);
            Barman jp = barmanService.save(new Barman().setName("JP")/*.setCocktails(List.of(mojito))*/);

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

            log.info("-----------------------");
            log.info("---------START---------");
            log.info("-----------------------");
            barmanService.findByIdWithLazyCocktails(jp.getId());
            barmanService.findByIdWithEagerCocktails(jp.getId());

            cocktailService.findById(mojito.getId());
        };
    }
}
