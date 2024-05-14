package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

    void deleteByName(String name);

    // type of query
}
