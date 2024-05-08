package io.takima.springdatabbl.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Getter
@Setter
@ToString
@FieldNameConstants
@Accessors(chain = true)
@Entity
@NamedQuery(
        name = "Barman.findByIdWithCocktails",
        query = "SELECT b FROM Barman b LEFT JOIN FETCH b.cocktails WHERE b.id = :id"
)
public class Barman {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "barman", fetch = FetchType.LAZY)
    private List<Cocktail> cocktails;
}
