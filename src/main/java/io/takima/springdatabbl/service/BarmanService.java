package io.takima.springdatabbl.service;

import io.takima.springdatabbl.dao.BarmanRepository;
import io.takima.springdatabbl.model.Barman;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BarmanService {

    private final BarmanRepository barmanRepository;

    public Barman findById(Long id) {
        Barman barman = barmanRepository.findById(id).orElseThrow();
        log.info("findById: {}", barman);
        return barman;
    }

    public Barman findByIdWithCocktails(Long id) {
        Barman barman = barmanRepository.findByIdWithCocktails(id).orElseThrow();
        log.info("findByIdWithCocktails: {}", barman);
        return barman;
    }

    public Barman getReferenceById(Long id) {
        return barmanRepository.getReferenceById(id);
    }

    @Transactional
    public Barman save(Barman barman) {
        return barmanRepository.save(barman);
    }

    public List<Barman> findAll() {
        return barmanRepository.findAll();
    }
}
