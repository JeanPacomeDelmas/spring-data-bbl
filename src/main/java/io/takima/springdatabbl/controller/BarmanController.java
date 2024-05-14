package io.takima.springdatabbl.controller;

import io.takima.springdatabbl.Monitored;
import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.service.BarmanCachedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/barmans")
public class BarmanController {

    private final BarmanCachedService barmanCachedService;

    public BarmanController(BarmanCachedService barmanCachedService) {
        this.barmanCachedService = barmanCachedService;
    }

    @Monitored
    @PostMapping
    public ResponseEntity<Barman> createBarman(@RequestBody Barman barman) {
        Barman createdBarman = barmanCachedService.createBarman(barman);
        return ResponseEntity.ok(createdBarman);
    }

    @Monitored
    @GetMapping("/{id}")
    public Barman getBarmanById(@PathVariable Long id) {
        return barmanCachedService.getById(id);
    }

    @Monitored
    @DeleteMapping("/{id}")
    public void removeBarman(@PathVariable Long id) {
        barmanCachedService.removeBarman(id);
    }

    @Monitored
    @PutMapping("/{id}")
    public Barman updateBarman(@PathVariable Long id, @RequestBody Barman barman) {
        return barmanCachedService.updateBarman(id, barman);
    }

}