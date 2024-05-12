package io.takima.springdatabbl.service;

import io.takima.springdatabbl.Monitored;
import io.takima.springdatabbl.dao.BarmanRepository;
import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.utils.SlowServiceSimulator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
//@CacheConfig(cacheNames = "barmans")
public class BarmanCachedService {

    private final BarmanRepository barmanRepository;

    @Monitored
    @Transactional
    public Barman save(Barman barman) {
        Barman saved = barmanRepository.save(barman);
        log.info("Saved barman: {}", barman);
        return saved;
    }

    @Cacheable(cacheNames = "barmans", key = "#id"/*, unless = "#result == null or #result.name.length() <= 5"*/)
    public Barman getById(Long id) {
        SlowServiceSimulator.simulateSlowThreeSecondsRequest();

        Barman barman = barmanRepository.findById(id).orElseThrow();
        log.info("Barman by id: {}", barman);

        return barman;
    }

    @Transactional
    @CacheEvict(cacheNames = "barmans", key = "#id")
    public void removeBarman(Long id) {
        SlowServiceSimulator.simulateSlowThreeSecondsRequest();

        barmanRepository.deleteById(id);
        log.info("Barman removed with id: {}", id);
    }

    @Transactional
    @CachePut(cacheNames = "barmans", key = "#result.id")
    public Barman updateBarman(Long id, Barman barman) {
        SlowServiceSimulator.simulateSlowThreeSecondsRequest();

        Barman existingBarman = barmanRepository.findById(id).orElseThrow();
        existingBarman.setName(barman.getName());
        // Update other properties as needed
        Barman updatedBarman = barmanRepository.save(existingBarman);
        log.info("Barman updated: {}", updatedBarman);
        return updatedBarman;
    }


}
