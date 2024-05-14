package io.takima.springdatabbl.controller;

import io.takima.springdatabbl.utils.CachePrinter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/caches")
public class CacheController {

    private final CachePrinter cachePrinter;

    public CacheController(CachePrinter cachePrinter) {
        this.cachePrinter = cachePrinter;
    }

    @GetMapping("/{name}")
    public List<String> getChacheByName(@PathVariable String name) {
        return cachePrinter.printCacheContentLineByLine(name);
    }
}