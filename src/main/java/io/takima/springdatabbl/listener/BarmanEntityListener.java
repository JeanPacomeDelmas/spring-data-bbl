package io.takima.springdatabbl.listener;

import io.takima.springdatabbl.model.Barman;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BarmanEntityListener {

    public static final String LOG_PREFIX = "[BarmanEntityListener]";

    @PrePersist
    public void prePersist(Barman barman) {
        // Business logic before persisting Barman entity
        log.info(LOG_PREFIX + "[PrePersist] Entity {}", barman);
    }

    @PostPersist
    public void postPersist(Barman barman) {
        // Business logic after persisting Barman entity
        log.info(LOG_PREFIX + "[PostPersist] Entity {}", barman);
    }

    @PreUpdate
    public void preUpdate(Barman barman) {
        // Business logic before updating Barman entity
        log.info(LOG_PREFIX + "[PreUpdate] Entity {}", barman);

    }

    @PostUpdate
    public void postUpdate(Barman barman) {
        // Business logic after updating Barman entity
        log.info(LOG_PREFIX + "[PostUpdate] Entity {}", barman);

    }

    // Other lifecycle callback methods...
    // - @PreRemove
    // - @PostRemove
    // - @PostLoad (JSR338)
}
