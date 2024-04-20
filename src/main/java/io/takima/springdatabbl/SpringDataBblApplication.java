package io.takima.springdatabbl;

import io.takima.springdatabbl.dao.UserRepository;
import io.takima.springdatabbl.dao.VehiculeRepository;
import io.takima.springdatabbl.model.User;
import io.takima.springdatabbl.model.Vehicle;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootApplication
public class SpringDataBblApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataBblApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(VehiculeRepository vehiculeRepository, UserRepository userRepository) {
        return args -> {
            User user = new User().setName("Valentin");
            User savedUser = userRepository.save(user);

            Vehicle vehicle = new Vehicle()
                    .setBrand("Porshe")
                    .setModel("911")
                    .setUser(savedUser);

            Vehicle saved = vehiculeRepository.save(vehicle);
            log.info("saved: {}", saved);

            Vehicle vById = vehiculeRepository.findById(vehicle.getId()).orElseThrow();
            log.info("vById: {}", vById);

            Vehicle vByMarque = vehiculeRepository.findByBrand("Porshe").orElseThrow();
            log.info("vByMarque: {}", vByMarque);

            Vehicle vByModele = vehiculeRepository.findByModel("911").orElseThrow();
            log.info("vByModele: {}", vByModele);

            List<Vehicle> vAllByUtilisateur = vehiculeRepository.findAllByUser(user);
            log.info("vAllByUtilisateur: {}", vAllByUtilisateur);

            User uByVehiclesContaining = userRepository.findByVehiclesContaining(saved).orElseThrow();
            log.info("uByVehiclesContaining: {}", uByVehiclesContaining);

            User uByVehicle = userRepository.findByVehicle(saved).orElseThrow();
            log.info("uByVehicle: {}", uByVehicle);


            User uByVehiclesId = userRepository.findByVehiclesId(saved.getId()).orElseThrow();
            log.info("uByVehiclesId: {}", uByVehiclesId);
        };
    }
}
