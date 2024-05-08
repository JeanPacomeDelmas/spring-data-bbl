package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.*;
import io.takima.springdatabbl.model.projection.BarmanProjection;
import io.takima.springdatabbl.model.projection.IBarmanProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BarmanRepository extends JpaRepository<Barman, Long> {

    // named query
    Optional<Barman> findByIdWithCocktails(Long id);

    // part tree query
    Optional<Barman> findByCocktailsContaining(Cocktail cocktail);

    Optional<Barman> findByCocktailsId(Long vehicleId);

    // string query (simple or native)
    @Query("SELECT b FROM Barman b JOIN FETCH b.cocktails c WHERE c = :cocktail")
    Optional<Barman> findByCocktail(Cocktail cocktail);

    List<Barman> findAllByCocktailsIngredientsContaining(Ingredient ingredient);

    Optional<IBarmanProjection> findBarmanById(Long id);

    @Query("SELECT b.name AS name FROM Barman b WHERE b.id = :id")
    Optional<IBarmanProjection> findIBarmanProjectionByIdWithStringQuery(Long id);

    @Query("SELECT NEW io.takima.springdatabbl.model.projection.BarmanProjection(b.name AS name, c.name AS cocktailName) FROM Barman b LEFT JOIN b.cocktails c WHERE b.id = :id")
    List<BarmanProjection> findBarmansProjectionByIdWithStringQuery(Long id);
}
