package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.*;
import io.takima.springdatabbl.model.projection.BarmanProjection;
import io.takima.springdatabbl.model.projection.IBarmanProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BarmanRepository extends JpaRepository<Barman, Long> {

    // fetch strategy
    @Query("SELECT b FROM Barman b LEFT JOIN FETCH b.cocktails WHERE b.id = :id")
    Optional<Barman> findByIdWithCocktails(Long id);

    // more complex query
    Optional<Barman> findByCocktailsContaining(Cocktail cocktail);
    Optional<Barman> findByCocktailsId(Long vehicleId);
    @Query("SELECT b FROM Barman b JOIN FETCH b.cocktails c WHERE c = :cocktail")
    Optional<Barman> findByCocktail(Cocktail cocktail);
    List<Barman> findAllByCocktailsIngredientsContaining(Ingredient ingredient);

    // projection
    Optional<IBarmanProjection> findBarmanById(Long id);
    @Query("SELECT b.name AS name FROM Barman b WHERE b.id = :id")
    Optional<IBarmanProjection> findIBarmanProjectionByIdWithStringQuery(Long id);
    @Query("SELECT NEW io.takima.springdatabbl.model.projection.BarmanProjection(b.name AS name, c.name AS cocktailName) FROM Barman b LEFT JOIN b.cocktails c WHERE b.id = :id")
    List<BarmanProjection> findBarmansProjectionByIdWithStringQuery(Long id);

    Optional<Barman> findByName(String name);
}
