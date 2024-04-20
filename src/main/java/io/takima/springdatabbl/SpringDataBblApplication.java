package io.takima.springdatabbl;

import io.takima.springdatabbl.dao.VehiculeRepository;
import io.takima.springdatabbl.model.Vehicule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataBblApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataBblApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(VehiculeRepository repository) {
        return args -> {
            Vehicule vehicule = new Vehicule();
            vehicule.setMarque("Porshe");

            repository.save(vehicule);
            Vehicule saved = repository.findById(vehicule.getId()).orElseThrow();
        };
    }
}
