package io.takima.springdatabbl.service;

import io.takima.springdatabbl.dao.UserRepository;
import io.takima.springdatabbl.model.User;
import io.takima.springdatabbl.model.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        log.info("findById: {}", user);
        return user;
    }

    public User findUserById(Long id) {
        User user = userRepository.findUserById(id).orElseThrow();
        log.info("findUserById: {}", user);
        return user;
    }

    public User getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByVehicle(Vehicle vehicle) {
        User user = userRepository.findByVehiclesContaining(vehicle).orElseThrow();
        log.info("findByVehicle: {}", user);
        return user;
    }

    public User findByVehiclesId(Vehicle vehicle) {
        User user = userRepository.findByVehiclesId(vehicle.getId()).orElseThrow();
        log.info("findByVehiclesId: {}", user);
        return user;
    }

    public User findUserByVehicle(Vehicle vehicle) {
        User user = userRepository.findByVehicle(vehicle).orElseThrow();
        log.info("findUserByVehicle: {}", user);
        return user;
    }
}
