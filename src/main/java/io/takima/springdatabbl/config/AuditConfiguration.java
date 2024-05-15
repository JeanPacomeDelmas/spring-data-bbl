package io.takima.springdatabbl.config;

import io.takima.springdatabbl.service.auditing.SpringSecurityAuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfiguration {

    @Bean
    AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditorAwareImpl();
    }
}
