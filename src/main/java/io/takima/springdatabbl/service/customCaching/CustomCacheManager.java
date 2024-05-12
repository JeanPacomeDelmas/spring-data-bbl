package io.takima.springdatabbl.service.customCaching;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

public class CustomCacheManager extends SimpleCacheManager {

    @Override
    protected Collection<? extends Cache> loadCaches() {
        // Load caches as per your application requirements
        // For this example, we'll create a custom cache with a specific caching strategy
        Cache barmansCache = new CustomCache("barmans");

        setCaches(List.of(barmansCache));
        // Add more caches if needed
        
        return super.loadCaches();
    }
}
