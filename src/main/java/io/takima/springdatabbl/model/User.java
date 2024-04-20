package io.takima.springdatabbl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class User {

    @Id
    private Long id;

    @OneToMany(mappedBy = "user")
    List<Vehicule> vehicules;
}
