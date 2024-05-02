package io.takima.springdatabbl.dao;

import io.takima.springdatabbl.model.User;
import io.takima.springdatabbl.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT u FROM users u LEFT JOIN FETCH vehicles v WHERE u.id = :id
            """)
    Optional<User> findUserById(Long id);

    Optional<User> findByVehiclesContaining(Vehicle vehicle); // explain analyse

    Optional<User> findByVehiclesId(Long vehicleId);

    @Query("""
            SELECT u FROM users u
                LEFT JOIN FETCH u.vehicles v
                WHERE u.id = v.user.id
    """)
    Optional<User> findByVehicle(Vehicle vehicle);
}
