package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.User;
import io.takima.springdatabbl.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByVehiclesContaining(Vehicle vehicle);

    Optional<User> findByVehiclesId(Long vehicleId);

    @Query("select u from users u left join vehicles v on u.id = v.user.id")
    Optional<User> findByVehicle(Vehicle vehicle);
}
