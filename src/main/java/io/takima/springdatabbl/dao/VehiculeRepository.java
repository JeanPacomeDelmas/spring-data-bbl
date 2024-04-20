package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.User;
import io.takima.springdatabbl.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT v FROM vehicles v WHERE v.brand = :brand")
    Optional<Vehicle> findByBrand(@Param("brand") String brand);

    Optional<Vehicle> findByModel(String model);

    List<Vehicle> findAllByUser(User user);
}
