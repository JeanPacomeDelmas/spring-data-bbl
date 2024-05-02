package io.takima.springdatabbl.service;

import io.takima.springdatabbl.dao.VehicleRepository;
import io.takima.springdatabbl.model.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Vehicle findById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow();
        log.info(vehicle.toString());
        return vehicle;
    }

    public Vehicle findVehicleById(Long vehicleId) {
        return vehicleRepository.findVehicleById(vehicleId).orElseThrow();
    }

    public Vehicle getReferenceById(Long id) {
        return vehicleRepository.getReferenceById(id);
    }

    @Transactional
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
