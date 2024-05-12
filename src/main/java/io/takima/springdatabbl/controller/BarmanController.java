package io.takima.springdatabbl.controller;

import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/barmans")
public class BarmanController {
    @Autowired
    private CocktailService cocktailService;

    // TODO: 5/11/2024 move to Barman controller
    @GetMapping("/{barmanId}/cocktails")
    public List<Cocktail> getAllCocktailsForBarman(@PathVariable Long barmanId) {
        return cocktailService.getAllCocktailsByBarmanId(barmanId);
    }

    // TODO: 5/11/2024 update cocktail name
}