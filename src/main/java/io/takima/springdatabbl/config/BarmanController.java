package io.takima.springdatabbl.config;

import io.takima.springdatabbl.Monitored;
import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.service.CocktailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/barmans")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    // TODO: 5/15/2024 create new cocktail

    @Monitored
    @PutMapping("/{barmanId}/cocktails")
    public Cocktail updateCocktail(@PathVariable Long barmanId, @RequestBody Cocktail cocktail) {
        cocktailService.saveWithBarmanByFind(cocktail, barmanId);
        return cocktailService.updateCocktail(barmanId, cocktail);
    }

}