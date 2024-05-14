package io.takima.springdatabbl.service;

import io.takima.springdatabbl.Monitored;
import io.takima.springdatabbl.dao.BarmanRepository;
import io.takima.springdatabbl.mapper.BarmanMapper;
import io.takima.springdatabbl.model.Barman;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BarmanService {

    private final BarmanMapper barmanMapper;
    private final BarmanRepository barmanRepository;

    @Monitored
    @Transactional
    public Barman save(Barman barman) {
        Barman saved = barmanRepository.save(barman);
        log.info("Saved barman: {}", barman);
        return saved;
    }

    // fetch strategy

    // more complex query

    // projection bsproj
}
