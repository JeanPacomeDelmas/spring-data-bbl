package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BarmanRepository extends JpaRepository<Barman, Long> {

    @Query("SELECT b FROM Barman b LEFT JOIN FETCH b.cocktails WHERE b.id = :id")
    Optional<Barman> findByIdWithCocktails(Long id);

    Optional<Barman> findByCocktailsContaining(Cocktail cocktail);

    Optional<Barman> findByCocktailsId(Long vehicleId);

    @Query("SELECT b FROM Barman b JOIN FETCH b.cocktails c WHERE c = :cocktail")
    Optional<Barman> findByCocktail(Cocktail cocktail);

    List<Barman> findAllByCocktailsIngredientsContaining(Ingredient ingredient);
}
