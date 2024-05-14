package io.takima.springdatabbl.service;

import io.takima.springdatabbl.dao.BarmanRepository;
import io.takima.springdatabbl.model.Barman;
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
@Transactional
//@CacheConfig(cacheNames = "barmans")
public class BarmanCachedService {

    private final BarmanRepository barmanRepository;

    @Cacheable(cacheNames = "barmans", key = "#id"/*, unless = "#result == null or #result.name.length() <= 5"*/)
    public Barman getById(Long id) {
        Barman barman = barmanRepository.findById(id).orElseThrow();

        log.info("[GET] Barman with id: {}", barman);

        return barman;
    }

    @CacheEvict(cacheNames = "barmans", key = "#id")
    public void removeBarman(Long id) {
        barmanRepository.deleteById(id);

        log.info("[REMOVE] Barman removed with id: {}", id);
    }

    @CachePut(cacheNames = "barmans", key = "#result.id")
    public Barman updateBarman(Long id, Barman barman) {
        Barman existingBarman = barmanRepository.findById(id)
                .orElseThrow()
                .setName(barman.getName());

        Barman updatedBarman = barmanRepository.save(existingBarman);

        log.info("[UPDATE] Barman updated: {}", updatedBarman);

        return updatedBarman;
    }

    public Barman createBarman(Barman barman) {
        Barman newBarman = barmanRepository.save(barman);

        log.info("[CREATE] New barman created: {}", newBarman);

        return newBarman;
    }

}
