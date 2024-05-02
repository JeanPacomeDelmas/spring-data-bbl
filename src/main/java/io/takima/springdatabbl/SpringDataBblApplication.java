package io.takima.springdatabbl;

import io.takima.springdatabbl.model.User;
import io.takima.springdatabbl.model.Vehicle;
import io.takima.springdatabbl.service.UserService;
import io.takima.springdatabbl.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringDataBblApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataBblApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(VehicleService vehicleService, UserService userService) {
        return args -> {
            User user = new User().setName("Valentin");
            User savedUser = userService.save(user);

            Vehicle vehicle = new Vehicle()
                    .setBrand("Porshe")
                    .setModel("911")
                    .setUser(savedUser);

            Vehicle saved = vehicleService.save(vehicle);
            log.info("saved: {}", saved);

            Vehicle vById = vehicleService.findById(vehicle.getId());
//            log.info("vById: {}", vById);

//            User uById = userService.findById(savedUser.getId());
//            log.info("uById: {}", uById);

            User uUById = userService.findUserById(savedUser.getId());
//            log.info("uById: {}", uUById);



//            Vehicle vByIdRepo = vehicleRepository.findById(vehicle.getId()).orElseThrow();
//            log.info("vByIdRepo: {}", vByIdRepo);

//            Vehicle vVById = vehicleService.findVehicleById(vehicle.getId());
//            log.info("vVById: {}", vVById);

//            Vehicle vByRefId = vehicleService.getReferenceById(vehicle.getId());
//            log.info("vByRefId: {}", vByRefId);

//            Vehicle vByMarque = vehiculeService.findByBrand("Porshe").orElseThrow();
//            log.info("vByMarque: {}", vByMarque);
//
//            Vehicle vByModele = vehiculeService.findByModel("911").orElseThrow();
//            log.info("vByModele: {}", vByModele);
//
//            List<Vehicle> vAllByUtilisateur = vehiculeService.findAllByUser(user);
//            log.info("vAllByUtilisateur: {}", vAllByUtilisateur);
//
            User uByVehiclesContaining = userService.findByVehicle(saved);
//            log.info("uByVehiclesContaining: {}", uByVehiclesContaining);

//            User uByVehicle = userService.findByVehicle(saved).orElseThrow();
//            log.info("uByVehicle: {}", uByVehicle);


            User uByVehiclesId = userService.findByVehiclesId(saved);
//            log.info("uByVehiclesId: {}", uByVehiclesId);

            User uUByVehicle = userService.findUserByVehicle(saved);
        };
    }
}
