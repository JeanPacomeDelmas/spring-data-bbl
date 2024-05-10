package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

    void deleteByName(String name);

    // type of query
    Optional<Cocktail> findByNameByNamedQuery(String name);
    @Query("SELECT c FROM Cocktail c WHERE c.name = :name")
    Optional<Cocktail> findByNameByJpqlStringQuery(String name);
    @Query(value = "SELECT c1_0.id, c1_0.name FROM cocktail c1_0 WHERE c1_0.name = ?1", nativeQuery = true)
    Optional<Cocktail> findByNameByNativeStringQuery(String name);

    Optional<Cocktail> findByName(String name);

    // more efficiente query
    boolean existsByName(String name);
    Cocktail getReferenceByName(String name);

//    https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html
}
