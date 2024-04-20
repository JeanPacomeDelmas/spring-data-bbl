package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

    @Query(value = "SELECT c FROM Cocktail c JOIN FETCH c.barmans b WHERE c.id = :id")
    Optional<Cocktail> findByIdWithBarmans(Long id);
}
