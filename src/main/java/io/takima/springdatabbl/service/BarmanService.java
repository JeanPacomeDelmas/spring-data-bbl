package io.takima.springdatabbl.service;

import io.takima.springdatabbl.Monitored;
import io.takima.springdatabbl.dao.BarmanRepository;
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

    private final BarmanRepository barmanRepository;

    @Monitored
    public Barman findByIdWithoutCocktails(Long id) {
        Barman barman = barmanRepository.findById(id).orElseThrow();
        log.info("Barman by id: {}", barman);
        return barman;
    }

    @Monitored
    public Barman findByIdWithLazyCocktails(Long id) {
        Barman barman = barmanRepository.findById(id).orElseThrow();
        log.info("Barman by id: {}, with cocktails: {}", barman, barman.getCocktails());
        return barman;
    }

    @Monitored
    public Barman findByIdWithEagerCocktails(Long id) {
        Barman barman = barmanRepository.findByIdWithCocktails(id).orElseThrow();
        log.info("Barman by id: {}, with join fetch cocktails: {}", barman, barman.getCocktails());
        return barman;
    }

    @Monitored
    @Transactional(readOnly = false)
    public Barman save(Barman barman) {
        Barman saved = barmanRepository.save(barman);
        log.info("Saved barman: {}", barman);
        return saved;
    }
}
