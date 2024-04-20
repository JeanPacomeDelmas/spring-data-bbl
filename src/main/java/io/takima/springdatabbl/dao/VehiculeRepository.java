package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.User;
import io.takima.springdatabbl.model.Vehicule;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface VehiculeRepository extends Repository<Vehicule, Long> {

    Vehicule save(Vehicule vehicule);

    Optional<Vehicule> findById(Long id);

    Optional<Vehicule> findByMarque(String marque);

    List<Vehicule> findAllByUser(User user);
}
